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
    private String item_id; //상품 ID

    private String item_name; //상품명
    private String item_value; //상품 가격
    private String item_img_url;//아이템 사진 URL
}
