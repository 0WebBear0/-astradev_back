package com.astradev_back.astradev_back.core.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParseTask {

    public String parseProducts(String request){
        try {
            String url = "https://habr.com/ru/search/?target_type=posts&order=relevance&q=" + request;
            Document doc = Jsoup.connect(url).get();

            Elements s = doc.select("article");

//            for (Element el: s) {
//                System.out.println(el);
//            }

            System.out.println( s );
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
            return doc.toString();

        } catch (IOException e) {
            System.out.println("dfjevjkrf");
        }
        return "er";
    }
}
