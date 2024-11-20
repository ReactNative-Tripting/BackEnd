package org.example.tripting.place;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Place")
@Getter
@Setter
public class Place {

    @Id
    private String ID; //ID

    private String placeName; // 장소 이름
    private String placeImgUrl; //장소 이미지 URL
    private String address; // 장소 주소
    private String explain; // 장소 설명
    private String type; //장소 타입
    private String latitude; // 위도
    private String longitude; // 경도
}