package org.example.tripting.point;

import lombok.Getter;
import lombok.Setter;
import org.example.tripting.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

// 사용자의 포인트 정보를 나타내는 모델 클래스
@Document(collection = "Point")
@Getter
@Setter
public class Point {

    @Id
    private String id; // ID

    private  String userId;

    private Integer point; // 사용자 포인트
}
