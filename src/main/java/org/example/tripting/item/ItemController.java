package org.example.tripting.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public Item ItemAdd(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> ItemDelete(@PathVariable String itemId) {
        try {
            itemService.deleteItem(itemId);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
    }
}
