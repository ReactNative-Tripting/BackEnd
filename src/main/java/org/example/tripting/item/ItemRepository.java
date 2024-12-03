package org.example.tripting.item;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {

    Item findByItemId(String itemId);  // 아이템 아이디로 방 찾기
}
