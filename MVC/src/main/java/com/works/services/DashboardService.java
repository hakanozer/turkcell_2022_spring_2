package com.works.services;

import com.works.props.Currency;
import com.works.props.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {


    public User[] allProduct() {
        String url = "https://jsonplaceholder.typicode.com/users";
        RestTemplate restTemplate = new RestTemplate();
        User[] users = restTemplate.getForObject(url, User[].class);
        return users;
    }

    public List<Currency> xml() {
        List<Currency> ls = new ArrayList<>();
        try {
            String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
            String  stData = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString();
            Document doc = Jsoup.parse( stData, Parser.xmlParser() );
            Elements elements = doc.getElementsByTag("Currency");
            for (Element item : elements) {
                String currencyName = item.getElementsByTag("CurrencyName").text();
                String forexBuying = item.getElementsByTag("ForexBuying").text();
                String forexSelling = item.getElementsByTag("ForexSelling").text();

                Currency currency = new Currency(currencyName, forexBuying, forexSelling);
                ls.add(currency);

            }
        }catch (Exception ex) {
            System.err.println("xml Error : " + ex);
        }
        return ls;
    }


}
