package com.astradev_back.astradev_back.core.service;

import com.astradev_back.astradev_back.core.mapper.UsersMapper;
import com.astradev_back.astradev_back.core.mapper.Users_KeyWordsMapper;
import com.astradev_back.astradev_back.core.model.KeyWordsDto;
import com.astradev_back.astradev_back.core.model.UsersDto;
import com.astradev_back.astradev_back.core.model.Users_KeyWordsDto;
import com.astradev_back.astradev_back.db.entity.KeyWords;
import com.astradev_back.astradev_back.db.entity.Users_KeyWords;
import com.astradev_back.astradev_back.db.repository.KeyWordsRepository;
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

    @Autowired
    KeyWordsRepository keyWordsRepository;

    public void add(Long word, Long user){
        users_keyWordsRepository.add(user, word);
    }

//    public UsersDto updateUser(UsersDto user){
//        Users existingUser = usersRepository.getByTgID(user.getId_telegram());
//        BeanUtils.copyProperties(user, existingUser, NullProperties.getNullPropertyNames(user));
//        return usersMapper.map(usersRepository.saveAndFlush(existingUser), UsersDto.class);
//    }

//    public List<UsersDto> getAll(){
//        return usersMapper.mapAsList(usersRepository.findAll(), UsersDto.class);
//    }
//

    public List<String> getWordsByUser(Long user){
        List<Users_KeyWords> words = users_keyWordsRepository.getWordsByUser(user);
        List<String> result = new ArrayList<>();
        for (Users_KeyWords word:words)
            result.add(word.getWord().getWord());
        return result;
    }

}