package com.astradev_back.astradev_back.api.controller;

import com.astradev_back.astradev_back.core.model.HHModel;
import com.astradev_back.astradev_back.core.model.HabrCardDto;
import com.astradev_back.astradev_back.core.service.KeyWordsService;
import com.astradev_back.astradev_back.core.service.Users_KeyWordsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
public class KeyWordsController {

    @Autowired
    private KeyWordsService keyWordsService;

//    @Autowired
//    private ParseTask parseTask;

    @Autowired
    private Users_KeyWordsService users_keyWordsService;

    @ApiOperation(
            value = "обращение к habr.com"
    )@GetMapping("/habr")
    @CrossOrigin
    public List<HabrCardDto> getData(@RequestParam String name){
        return keyWordsService.getHabrByUsr(name);
    }

    @CrossOrigin
    @ApiOperation(
            value = "Добавление ключевого слова"
    )
    @PostMapping
    public void addKeyWord(@RequestParam String[] word, @RequestParam String userName){
        keyWordsService.addKeyWord(word, userName);
    }

    @ApiOperation(
            value = "Обращение к hh.ru"
    )
    @CrossOrigin
    @RequestMapping(value= "/hh", method = RequestMethod.GET)
    public List<HHModel> getDataHh(@RequestParam String name) throws JsonProcessingException {
        return keyWordsService.getHHByUsr(name);
    }



//    @ApiOperation(
//            value = "Получение всех ключевых слов"
//    )
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public List<KeyWordsDto> getAll(){
//        return keyWordsService.getAll();
//    }
//
//    @ApiOperation(
//            value = "Получение keyWords по пользователю"
//    )
//    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
//    public List<UsersDto> getByName(@PathVariable String name){
//        return keyWordsService.getByName(name);
//    }
}