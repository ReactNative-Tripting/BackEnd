package org.example.tripting.Stroage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

// 사용자의 포인트 정보를 나타내는 모델 클래스
@Document(collection = "Storage")
@Getter
@Setter
public class Storage {

    @Id
    private String id; // ID

    private  String userId;

    private ArrayList<StorageDTO> items = new ArrayList<>();
}
