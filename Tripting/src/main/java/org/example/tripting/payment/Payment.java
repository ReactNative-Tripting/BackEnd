package org.example.tripting.payment;

import lombok.Getter;
import lombok.Setter;
import org.example.tripting.item.Item;
import org.example.tripting.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Document(collection = "Payment")
@Getter
@Setter
public class Payment {

    @Id
    private String id; //ID

    @DocumentReference
    private User userId; // 사용자 ID

    @DocumentReference
    private Item itemId; // 상품명
    private Integer itemValue; // 상품 가격

    @CreatedDate
    private Date buyDate; //결제일
}
