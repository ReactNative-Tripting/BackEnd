package org.example.tripting.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public Item addItem(Item item){
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(String itemId) {
        Item item = itemRepository.findByItemId(itemId);
        if (item != null) {
            itemRepository.delete(item);  // 방 삭제
        } else {
            throw new RuntimeException("아이템 아이디가 유효하지 않습니다. 아이템을 찾을 수 없습니다.");
        }
    }

}
