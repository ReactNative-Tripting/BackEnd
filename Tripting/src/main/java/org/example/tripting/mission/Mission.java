package org.example.tripting.mission;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Mission")
@Getter
@Setter
public class Mission {

    private String ms_type; //미션 타입
    private Integer ms_difficult; //미션 난이도
    private Integer point_per_ms; //미션당 포인트
}
