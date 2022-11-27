package com.astradev_back.astradev_back.core.service;

import com.astradev_back.astradev_back.core.mapper.UsersMapper;
import com.astradev_back.astradev_back.core.mapper.Users_KeyWordsMapper;
import com.astradev_back.astradev_back.core.model.KeyWordsDto;
import com.astradev_back.astradev_back.core.model.UsersDto;
import com.astradev_back.astradev_back.core.model.Users_KeyWordsDto;
import com.astradev_back.astradev_back.db.entity.KeyWords;
import com.astradev_back.astradev_back.db.repository.UsersRepository;
import com.astradev_back.astradev_back.db.repository.Users_KeyWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Users_KeyWordsService {

    @Autowired
    Users_KeyWordsRepository users_keyWordsRepository;

    @Autowired
    Users_KeyWordsMapper users_keyWordsMapper;

    public void add(Long user, Long word){
        users_keyWordsRepository.add(user, word);
    }

//    public UsersDto updateUser(UsersDto user){
//        Users existingUser = usersRepository.getByTgID(user.getId_telegram());
//        BeanUtils.copyProperties(user, existingUser, NullProperties.getNullPropertyNames(user));
//        return usersMapper.map(usersRepository.saveAndFlush(existingUser), UsersDto.class);
//    }

    public List<Users_KeyWordsDto> getAll(){
        return users_keyWordsMapper.mapAsList(users_keyWordsRepository.findAll(), Users_KeyWordsDto.class);
    }
//
    public List<String> getWordsByUser(Long userId){
        List<KeyWords> words = users_keyWordsRepository.getWordsByUser(userId);
        List<String> result = new ArrayList<>();
        for (KeyWords word : words)
            result.add(word.getWord());
        return result;
    }

}