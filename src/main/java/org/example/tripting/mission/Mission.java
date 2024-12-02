package org.example.tripting.mission;

import lombok.Getter;
import lombok.Setter;
import org.example.tripting.place.Place;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "Misson")
@Getter
@Setter
public class Mission {
    private String placename;
    private String type;
    private String title;
    private String description;
    private String area;
}
