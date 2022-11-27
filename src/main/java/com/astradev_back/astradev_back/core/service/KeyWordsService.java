package com.astradev_back.astradev_back.core.service;

import com.astradev_back.astradev_back.core.mapper.KeyWordsMapper;
import com.astradev_back.astradev_back.core.model.HHModel;
import com.astradev_back.astradev_back.db.entity.Users;
import com.astradev_back.astradev_back.db.repository.KeyWordsRepository;
import com.astradev_back.astradev_back.db.repository.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public HHModel getHh(String word) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(httpHh +
                        "?text="+ word + "&only_with_salary=true" +
                        "&order_by=salary_desc",
                HttpMethod.GET,
                requestEntity,
                String.class);

        ResponseEntity<String> responseEntityMinSal = restTemplate.exchange(httpHh +
                        "?text="+ word + "&only_with_salary=true" +
                        "&order_by=salary_asc",
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
            if (Objects.equals(elem, elems[1]))
                maxSalary = salary;
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
            if (Objects.equals(elem, elems[1]))
                maxSalary = salary;
            sumMin+=salary;
        }
        System.out.printf("fnds %s  ms %s  mns %s  avgs %s", founds, maxSalary, minSalary, (sumMax+sumMin)/40);
        return new HHModel(word, founds, maxSalary, minSalary, (sumMax+sumMin)/40);
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
