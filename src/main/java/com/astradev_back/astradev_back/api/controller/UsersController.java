package com.astradev_back.astradev_back.api.controller;

import com.astradev_back.astradev_back.core.model.UsersDto;
import com.astradev_back.astradev_back.core.service.UsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

//
//    @ApiOperation(
//            value = "Изменение пользователя"
//    )
//    @RequestMapping(value = "/update", method = RequestMethod.PUT)
//    public UsersDto updateUser(@RequestBody UsersDto user){
//        return usersService.updateUser(user);
//    }

    @ApiOperation(
            value = "Добавление пользователя"
    )
    @CrossOrigin
    @PostMapping
    public UsersDto signUp(@RequestBody UsersDto user){
        return usersService.signUp(user);
    }

    @ApiOperation(
            value = "Получение всех пользователей"
    )
    @CrossOrigin
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UsersDto> getAll(){
        return usersService.getAll();
    }

    @ApiOperation(
            value = "Получение пользователя по name"
    )
    @CrossOrigin
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public UsersDto getByName(@PathVariable String name){
        return usersService.getByName(name);
    }


}