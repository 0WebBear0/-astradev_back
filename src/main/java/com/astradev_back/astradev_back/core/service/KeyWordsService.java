package com.astradev_back.astradev_back.core.service;

import com.astradev_back.astradev_back.core.mapper.KeyWordsMapper;
import com.astradev_back.astradev_back.core.mapper.UsersMapper;
import com.astradev_back.astradev_back.core.model.KeyWordsDto;
import com.astradev_back.astradev_back.core.model.UsersDto;
import com.astradev_back.astradev_back.db.entity.KeyWords;
import com.astradev_back.astradev_back.db.repository.KeyWordsRepository;
import com.astradev_back.astradev_back.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class KeyWordsService {

    static RestTemplate restTemplate = new RestTemplate();
    static String http = "https://api.hh.ru/";

    @Autowired
    private KeyWordsRepository keyWordsRepository;

    @Autowired
    private Users_KeyWordsService users_keyWordsService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private KeyWordsMapper keyWordsMapper;


    public void addKeyWord(String word, String name){
        Long userId = usersRepository.getByName(name).getId();
        if (keyWordsRepository.getByWord(word) == null) {
            keyWordsRepository.add(word);
        }
        Long wordId = keyWordsRepository.getByWord(word).getId();
        if (!users_keyWordsService.getWordsByUser(userId).contains(word)) {
            users_keyWordsService.add(wordId, userId);
        }
    }


//    public void getHh(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(http + "books?search=",
//                HttpMethod.GET,
//                requestEntity,
//                String.class);
//
//        String body = responseEntity.getBody();
//        body = body.substring(body.indexOf("results") + 10,
//                body.length() - 2);
//    }

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
