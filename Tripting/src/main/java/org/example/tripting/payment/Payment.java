package org.example.tripting.payment;

import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Payment")
@Getter
@Setter
public class Payment {

    @Id
    private String user_id; // 사용자 ID

    private String  item_name; // 상품명
    private Integer item_value; // 상품 가격
    private Date buy_date; //결제일
}
