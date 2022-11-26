package com.astradev_back.astradev_back.api.controller;

import com.astradev_back.astradev_back.core.model.KeyWordsDto;
import com.astradev_back.astradev_back.core.model.UsersDto;
import com.astradev_back.astradev_back.core.service.KeyWordsService;
import com.astradev_back.astradev_back.core.service.ParseTask;
import com.astradev_back.astradev_back.core.service.UsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
public class KeyWordsController {

    @Autowired
    private KeyWordsService keyWordsService;

    @Autowired
    private ParseTask parseTask;


    @ApiOperation(
            value = "Парсинг хабра"
    )
    @GetMapping
    public void getData(){
        parseTask.parseProducts("quasar vue");
    }

    @ApiOperation(
            value = "Добавление ключевого слова"
    )
    @PostMapping
    public void addKeyWord(@RequestParam String word, @RequestParam String userName){
        keyWordsService.addKeyWord(word, userName);
    }

    @ApiOperation(
            value = "Парсинг хабра"
    )
    @RequestMapping(value= "/hh")
    public void getDataHh(){
        keyWordsService.getHh();
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