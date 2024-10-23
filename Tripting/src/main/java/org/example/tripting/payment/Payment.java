package org.example.tripting.payment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Document(collection = "Payment")
@Getter
@Setter
public class Payment {

    @DocumentReference
    private String userId; // 사용자 ID

    private String itemName; // 상품명
    private Integer itemValue; // 상품 가격
    private Date buyDate; //결제일
}
