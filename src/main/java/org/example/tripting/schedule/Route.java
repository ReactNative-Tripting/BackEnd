package org.example.tripting.schedule;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Route")
@Getter
@Setter
public class Route {

    @Id
    private String id;

    private String routeName;
    private ArrayList<String> rorute;
}
