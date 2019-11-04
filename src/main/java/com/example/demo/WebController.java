package com.example.demo;

import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;

@RestController
public class WebController {

    @RequestMapping("/calendar/events")
    public Calendar getICal() throws Exception {

        int month = 11;
        int year = 2019;
        Elements activeTD = getCalendarFromWeeia(year, month);
        Calendar calendar = new Calendar();
        calendar.getProperties()
                .add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties()
                .add(Version.VERSION_2_0);
        calendar.getProperties()
                .add(CalScale.GREGORIAN);
        java.util.Calendar calc = java.util.Calendar.getInstance();

        activeTD.forEach(td -> {
            Element divElement = td.select("div.InnerBox")
                                   .first();
            Element aElement = td.select("a.active")
                                 .first();
            UidGenerator ug = null;
            try {
                ug = new UidGenerator("1");
            } catch (SocketException e) {
                e.printStackTrace();
            }
            calc.set(java.util.Calendar.YEAR, year);
            calc.set(java.util.Calendar.MONTH, month - 1);
            calc.set(java.util.Calendar.DAY_OF_MONTH, Integer.valueOf(aElement.ownText()));
            VEvent event = new VEvent(new Date(calc.getTime()), divElement.text());
            event.getProperties()
                 .add(ug.generateUid());
            calendar.getComponents()
                    .add(event);
        });
        calendar.validate();
        return calendar;

    }

    private Elements getCalendarFromWeeia(Integer year, Integer month) {
        String content = null;
        URLConnection connection = null;
        StringBuilder urlString = new StringBuilder("http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?");
        urlString.append("rok=")
                 .append(year.toString())
                 .append("&miesiac=")
                 .append(month.toString())
                 .append("&lang=1");

        try {
            connection = new URL(urlString.toString()).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Document doc = Jsoup.parse(content, "UTF-8");

        Elements activeTD = doc.select("td.active");

        return activeTD;
    }
}
