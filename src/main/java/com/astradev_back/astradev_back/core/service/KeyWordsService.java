package com.astradev_back.astradev_back.core.service;

import com.astradev_back.astradev_back.core.mapper.KeyWordsMapper;
import com.astradev_back.astradev_back.core.model.HHModel;
import com.astradev_back.astradev_back.core.model.HabrCardDto;
import com.astradev_back.astradev_back.db.entity.Users;
import com.astradev_back.astradev_back.db.repository.KeyWordsRepository;
import com.astradev_back.astradev_back.db.repository.UsersRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class KeyWordsService {

    static RestTemplate restTemplate = new RestTemplate();
    static String httpHh = "https://api.hh.ru/vacancies/";
    static String httpCur = "https://open.er-api.com/v6/latest/";

    @Autowired
    private KeyWordsRepository keyWordsRepository;

    @Autowired
    private Users_KeyWordsService users_keyWordsService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private KeyWordsMapper keyWordsMapper;

    public void addKeyWord(String[] words, String name){
        for (String word:words)
            addKeyWord(word, name);
    }

    public void addKeyWord(String word, String name){
        Long userId = usersRepository.getByName(name).getId();
        if (keyWordsRepository.getByWord(word) == null)
            keyWordsRepository.add(word);
        Long wordId = keyWordsRepository.getByWord(word).getId();
        if (!users_keyWordsService.getWordsByUser(userId).contains(word))
            users_keyWordsService.add(wordId, userId);
    }

    public List<HHModel> getHHByUsr(String name) {
        Users user = usersRepository.getByName(name);
        List<String> words = users_keyWordsService.getWordsByUser(user.getId());
        List<HHModel> result = new ArrayList<>();
        for (String word:words)
            result.add(getHh(word));
        return result;
    }

    public List<HabrCardDto> getHabrByUsr(String name) {
        Users user = usersRepository.getByName(name);
        List<String> words = users_keyWordsService.getWordsByUser(user.getId());
        List<HabrCardDto> habrCardDtos = new ArrayList<>();
        for (String request : words) {
            String url = "https://habr.com/ru/search/?target_type=posts&order=relevance&q=" + request;
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                return null;
            }

            Element title = doc.select("h2.tm-article-snippet__title").first();
            String articleUrl = title.select("a[href].tm-article-snippet__title-link").attr("href");
            Element body = doc.select("div.article-formatted-body").first();

            HabrCardDto cardDto = new HabrCardDto();
            cardDto.setWord(request);
            cardDto.setBody(body.text());
            cardDto.setUrl("habr.com" + articleUrl);
            cardDto.setTitle(title.text());

            habrCardDtos.add(cardDto);
        }

        return habrCardDtos;
    }

    public HHModel getHh(String word) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        boolean point = false;

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(httpHh +
                        "?text="+ word + "&only_with_salary=true" +
                        "&order_by=salary_desc",
                HttpMethod.GET,
                requestEntity,
                String.class);

        ResponseEntity<String> responseEntityMinSal = restTemplate.exchange(httpHh +
                        "?text="+ word + "&only_with_salary=true" +
                        "&order_by=salary_asc"+"&salary=15000&currency=RUR",
                HttpMethod.GET,
                requestEntity,
                String.class);

        String body = responseEntity.getBody();
        body.replaceAll("\"working_days\":.*],", "");

        //Founds
        int founds = Integer.parseInt(body.substring(
                body.indexOf("\"found\":")+8,
                body.indexOf("\"pages\":")-1
        ));

        String[] elems = body.split("[,\\[]\\{\"id\":");
        double maxSalary = 0;
        double sumMax = 0;

        for (String elem : Arrays.copyOfRange(elems, 1, elems.length)) {
            double salary = Double.parseDouble(elem.substring(elem.indexOf("\"salary\":{\"from\":")+17,
                elem.indexOf("\"to\":")-1));

            String currency = elem.substring(elem.indexOf("\"currency\":")+12,
                    elem.indexOf("\"gross\":")-2);
            String bodyCur = "1";

            if (!currency.equals("RUR")){
                ResponseEntity<String> responseEntityCur = restTemplate.exchange(httpCur + currency,
                        HttpMethod.GET,
                        requestEntity,
                        String.class);

                bodyCur = responseEntityCur.getBody();
                bodyCur = bodyCur.substring(bodyCur.indexOf("RUB")+5, bodyCur.indexOf("RUB")+10);
            }
            salary *= Double.parseDouble(bodyCur);
            if (!point)
                maxSalary = salary;
            point = true;
            sumMax+=salary;
        }

        double minSalary = 0;
        double sumMin = 0;

        body = responseEntityMinSal.getBody();
        body = body.replaceAll("\"working_days\":.*]", "");
        elems = body.split("[,\\[]\\{\"id\":");
        System.out.println(body);
        for (String elem : Arrays.copyOfRange(elems, 1, elems.length)) {
            System.out.println(elem);
            String from = elem.substring(elem.indexOf("\"salary\":{\"from\":") + 17, elem.indexOf("\"to\":") - 1);
            if (!from.equals("null")){
                System.out.println("warn");
                double salary = Double.parseDouble(from);

                String currency = elem.substring(elem.indexOf("\"currency\":")+12,
                        elem.indexOf("\"gross\":")-2);
                String bodyCur = "1";

                if (!currency.equals("RUR")){
                    ResponseEntity<String> responseEntityCur = restTemplate.exchange(httpCur + currency,
                            HttpMethod.GET,
                            requestEntity,
                            String.class);

                    bodyCur = responseEntityCur.getBody();
                    bodyCur = bodyCur.substring(bodyCur.indexOf("RUB")+5, bodyCur.indexOf("RUB")+10);
                }
                salary *= Double.parseDouble(bodyCur);
                if (point){
                    minSalary = salary;
                    point = false;
                }
                sumMin+=salary;
            }
            sumMin+=13000;
        }
        return new HHModel(word, founds, maxSalary, minSalary==0?13000:minSalary, (sumMax+sumMin)/40);
    }

//    public UsersDto updateUser(UsersDto user){
//        Users existingUser = usersRepository.getByTgID(user.getId_telegram());
//        BeanUtils.copyProperties(user, existingUser, NullProperties.getNullPropertyNames(user));
//        return usersMapper.map(usersRepository.saveAndFlush(existingUser), UsersDto.class);
//    }
//
//    public List<UsersDto> getAll(){
//        return usersMapper.mapAsList(usersRepository.findAll(), UsersDto.class);
//    }
//
//    public UsersDto getByName(String name){
//        return usersMapper.map(usersRepository.getByName(name), UsersDto.class);
//    }

}
