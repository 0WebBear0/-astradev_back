package com.astradev_back.astradev_back.api.controller;

import com.astradev_back.astradev_back.core.model.HabrCardDto;
import com.astradev_back.astradev_back.core.model.KeyWordsDto;
import com.astradev_back.astradev_back.core.model.UsersDto;
import com.astradev_back.astradev_back.core.model.Users_KeyWordsDto;
import com.astradev_back.astradev_back.core.service.KeyWordsService;
import com.astradev_back.astradev_back.core.service.ParseTask;
import com.astradev_back.astradev_back.core.service.UsersService;
import com.astradev_back.astradev_back.core.service.Users_KeyWordsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/keywords")
public class KeyWordsController {

    @Autowired
    private KeyWordsService keyWordsService;

    @Autowired
    private ParseTask parseTask;

    @Autowired
    private Users_KeyWordsService users_keyWordsService;

    @ApiOperation(
            value = "Парсинг хабра"
    )@GetMapping("/{id}")
    public List<HabrCardDto> getData(@PathVariable Long id){
        List<String> keywords = users_keyWordsService.getWordsByUser(id);
//        List<String> keywordsTest = new ArrayList<>();
//        keywordsTest.add("vue");
//        keywordsTest.add("java");
//        keywordsTest.add("css");
//        keywords
    Test.add("quasar");
//        keywordsTest.add("angular");
//        keywordsTest.add("less");
//        keywordsTest.add("python");
//        keywordsTest.add("keras");
//        keywordsTest.add("tensorflow");
//        keywordsTest.add("machine learning");
        return parseTask.parseProducts(keywords);
    }

    @GetMapping("/all")
    public List<Users_KeyWordsDto> getAll() {
        return users_keyWordsService.getAll();
    }

    @ApiOperation(
            value = "Добавление ключевого слова"
    )
    @PostMapping
    public void addKeyWord(@RequestParam String word, @RequestParam String userName){
        keyWordsService.addKeyWord(word, userName);
    }

//    @ApiOperation(
//            value = "Парсинг hh"
//    )
//    @RequestMapping(value= "/hh")
//    public void getDataHh(){
//        keyWordsService.getHh();
//    }



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