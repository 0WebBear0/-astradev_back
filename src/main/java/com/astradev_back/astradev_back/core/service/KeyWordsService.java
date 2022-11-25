package com.astradev_back.astradev_back.core.service;

import com.astradev_back.astradev_back.core.mapper.KeyWordsMapper;
import com.astradev_back.astradev_back.core.mapper.UsersMapper;
import com.astradev_back.astradev_back.core.model.KeyWordsDto;
import com.astradev_back.astradev_back.core.model.UsersDto;
import com.astradev_back.astradev_back.db.entity.KeyWords;
import com.astradev_back.astradev_back.db.repository.KeyWordsRepository;
import com.astradev_back.astradev_back.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyWordsService {

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
        if (keyWordsRepository.getByWord(word) == null)
            keyWordsRepository.add(word);
        Long wordId = keyWordsRepository.getByWord(word).getId();
        if (!users_keyWordsService.getWordsByUser(userId).contains(word))
            users_keyWordsService.add(wordId, userId);
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
