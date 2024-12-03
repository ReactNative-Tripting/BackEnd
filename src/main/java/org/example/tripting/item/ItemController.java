package org.example.tripting.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){ this.itemService = itemService; }

    @PostMapping("/add")
    public Item ItemAdd(Item item){
        return itemService.add(item);
    }
}
