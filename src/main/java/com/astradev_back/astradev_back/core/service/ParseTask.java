package com.astradev_back.astradev_back.core.service;

import com.astradev_back.astradev_back.core.model.HabrCardDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParseTask {

    public List<HabrCardDto> parseProducts(List<String> requestList){
        try {
            List<HabrCardDto> habrCardDtos = new ArrayList<>();
            for (String request : requestList) {
                String url = "https://habr.com/ru/search/?target_type=posts&order=relevance&q=" + request;
                Document doc = Jsoup.connect(url).get();

                Element title = doc.select("h2.tm-article-snippet__title").first();
                String articleUrl = title.select("a[href].tm-article-snippet__title-link").attr("href");
                Element body = doc.select("div.article-formatted-body").first();

                HabrCardDto cardDto = new HabrCardDto();
                cardDto.setBody(body.toString());
                cardDto.setUrl("habr.com" + articleUrl);
                cardDto.setTitle(title.text());

                habrCardDtos.add(cardDto);
//            for (Element el: s) {
//                System.out.println(el);
//            }

//            for (Element headline : newsHeadlines) {
//                log("%s\n\t%s",
//                        headline.attr("title"), headline.absUrl("href"));
//            }
//            Elements products = doc.select("h4.sU");
//            Elements products = doc.select(".sU");
//            StringBuilder res = new StringBuilder();
//            for (Element el : products){
//                res.append(el.ownText());
//                System.out.println("ve");
//                System.out.println(el.ownText());
//            }
            }

            return habrCardDtos;

        } catch (IOException e) {
            System.out.println("dfjevjkrf");
        }
        return null;
    }
}
