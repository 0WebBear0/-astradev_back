package com.astradev_back.astradev_back.core.service;

import com.astradev_back.astradev_back.core.mapper.UsersMapper;
import com.astradev_back.astradev_back.core.model.UsersDto;
import com.astradev_back.astradev_back.db.entity.Users;
import com.astradev_back.astradev_back.db.repository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersMapper usersMapper;


    public UsersDto signUp(UsersDto user){
        usersRepository.add(
                user.getName(), user.getMail(),
                user.getPassword()
        );
        return usersMapper.map(usersRepository.getByName(user.getName()), UsersDto.class);
    }

//    public UsersDto updateUser(UsersDto user){
//        Users existingUser = usersRepository.getByTgID(user.getId_telegram());
//        BeanUtils.copyProperties(user, existingUser, NullProperties.getNullPropertyNames(user));
//        return usersMapper.map(usersRepository.saveAndFlush(existingUser), UsersDto.class);
//    }

    public List<UsersDto> getAll(){
        return usersMapper.mapAsList(usersRepository.findAll(), UsersDto.class);
    }

    public UsersDto getByName(String name){
        return usersMapper.map(usersRepository.getByName(name), UsersDto.class);
    }

}
