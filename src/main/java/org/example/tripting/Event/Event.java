package org.example.tripting.Event;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Event")
@Getter
@Setter
public class Event {

    private List<String> eventstartdate = new ArrayList<>(); //행사시작일
    private List<String> eventenddate = new ArrayList<>();  //행사종료일

    private List<String> firstimage = new ArrayList<>();  //대표이미지(원본)

    private List<String> mapx = new ArrayList<>();  //GPS X좌표
    private List<String> mpay = new ArrayList<>();  //GPS Y좌표

    private List<String> tel = new ArrayList<>();  //전화번호*/

    private List<String> title = new ArrayList<>();  //제목
}