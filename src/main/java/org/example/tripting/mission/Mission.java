package org.example.tripting.mission;

import lombok.Getter;
import lombok.Setter;
import org.example.tripting.place.Place;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "Mission")
@Getter
@Setter
public class Mission {

    @Id
    private String id; //ID

    @DocumentReference
    private Place adress;

    private Integer msDifficult; //미션 난이도
    private Integer point; //미션당 포인트
}
