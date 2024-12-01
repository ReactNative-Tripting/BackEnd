package org.example.tripting.route;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Document(collection = "Route")
@Getter
@Setter
public class Route {
    private String type;
    private ArrayList<RouteDTO> routes = new ArrayList<>();
}
