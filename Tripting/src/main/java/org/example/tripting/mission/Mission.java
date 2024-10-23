package org.example.tripting.mission;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Mission")
@Getter
@Setter
public class Mission {

    @Id
    private String id; //ID

    private String msType; //미션 타입
    private Integer msDifficult; //미션 난이도
    private Integer pointPerMs; //미션당 포인트
}
