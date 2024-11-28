package org.example.tripting.Event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Value("${TourAPIKEY}")
    private String apiKey;

    @Override
    public Event getEventInfo() {
        Event event = new Event();
        List<EventFormat> eventList = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();

        LocalDate date30DaysAfter = currentDate.plusDays(7);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedToday = currentDate.format(formatter);
        String formattedAfterDate = date30DaysAfter.format(formatter);

        String[] sigunguCodeV = {"9", "12"};
        try{
            for(int i = 0; i < 2; i++) {
                StringBuilder result = new StringBuilder();
                String API = "https://apis.data.go.kr/B551011/KorService1/searchFestival1?serviceKey=" + apiKey +
                        "&MobileApp=AppTest&MobileOS=ETC&pageNo=1&numOfRows=10" +
                        "&eventStartDate=" + "20241101" + "&eventEndDate=" + formattedAfterDate +
                        "&listYN=Y&arrange=D&areaCode=34" +
                        "&sigunguCode=" + sigunguCodeV[i] + "&_type=json";

                URL url = new URL(API);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

                String returnLine;

                while ((returnLine = bufferedReader.readLine()) != null) {
                    result.append(returnLine).append("\n");
                }
                urlConnection.disconnect();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(result.toString());

                JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

                if (itemsNode.isArray()) {
                    for (JsonNode item : itemsNode) {
                        EventFormat newEvent = new EventFormat();

                        newEvent.setTitle(item.path("title").asText());
                        newEvent.setEventstartdate(item.path("eventstartdate").asText());
                        newEvent.setEventenddate(item.path("eventenddate").asText());
                        newEvent.setFirstimage(item.path("firstimage").asText());
                        newEvent.setMapx(item.path("mapx").asText());
                        newEvent.setMpay(item.path("mapy").asText());
                        newEvent.setTel(item.path("tel").asText());

                        eventList.add(newEvent);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        event.setEventList(eventList);

        return event;
    }
}