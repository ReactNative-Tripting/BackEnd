package org.example.tripting.item;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Item")
@Getter
@Setter
public class Item {

    @Id
    private String id; //ID

    private String itemId; //상품 ID

    private String itemName; //상품명
    private String itemValue; //상품 가격
    private String itemImgUrl;//아이템 사진 URL
}
