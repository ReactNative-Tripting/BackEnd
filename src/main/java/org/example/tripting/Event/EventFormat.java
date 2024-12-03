package org.example.tripting.Event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EventFormat")
@Getter
@Setter
public class EventFormat {
    private String addr1;
    private String eventstartdate; //행사시작일
    private String eventenddate;  //행사종료일

    private String firstimage;  //대표이미지(원본)

    private String mapx;  //GPS X좌표
    private String mpay;  //GPS Y좌표

    private String tel;  //전화번호*/

    private String title;  //제목
}