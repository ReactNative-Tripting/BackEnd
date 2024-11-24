package org.example.tripting.Event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public Event getEventInfo() {
        Event event = new Event();

        StringBuilder result = new StringBuilder();
        try{
            String API = "https://apis.data.go.kr/B551011/KorService1/searchFestival1?serviceKey=aXSIJfvKqktsqhBoDMkR3vaqK4OS9eZZ01IOh6UmhGk%2FCUFPKs4LlyafnOAw6zh7%2FsBDMiZyDEXO4nArceMIww%3D%3D&MobileApp=AppTest&MobileOS=ETC&pageNo=1&numOfRows=10&eventStartDate=20240101&listYN=Y&arrange=D&areaCode=34&sigunguCode=9&_type=json";

            URL url = new URL(API);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

            String returnLine;

            while((returnLine= bufferedReader.readLine()) != null) {
                result.append(returnLine).append("\n");
            }
            urlConnection.disconnect();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(result.toString());

            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

            List<String> titles = new ArrayList<>();
            List<String> eventstartdates = new ArrayList<>();
            List<String> eventenddates = new ArrayList<>();
            List<String> firstimages = new ArrayList<>();
            List<String> mapxes = new ArrayList<>();
            List<String> mapyes = new ArrayList<>();
            List<String> teles = new ArrayList<>();

            for(JsonNode item: itemsNode) {
                titles.add(item.path("title").asText());
                eventstartdates.add(item.path("eventstartdate").asText());
                eventenddates.add(item.path("eventenddate").asText());
                firstimages.add(item.path("firstimage").asText());
                mapxes.add(item.path("mapx").asText());
                mapyes.add(item.path("mapy").asText());
                teles.add(item.path("tel").asText());
            }

            event.setTitle(titles);
            event.setEventstartdate(eventstartdates);
            event.setEventenddate(eventenddates);
            event.setFirstimage(firstimages);
            event.setMapx(mapxes);
            event.setMpay(mapyes);
            event.setTel(teles);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("test");


        return event;
    }
}