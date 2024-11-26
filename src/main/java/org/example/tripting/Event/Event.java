package org.example.tripting.Event;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Event")
@Getter
@Setter
public class Event {
    private List<EventFormat> eventList;
}