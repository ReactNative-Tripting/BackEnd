package org.example.tripting.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// 사용자 정보를 나타내는 모델 클래스
@Document(collection = "User")
@Getter
@Setter
public class User {

    @Id
    private String id; //ID

    private String userId; // 사용자 아이디

    private String username; // 사용자 이름
    private String password; // 사용자 비밀번호
    private Integer phoneNum; // 사용자 전화번호
    private String email; // 사용자 이메일
    private String sex;// 성별
}