package org.example.tripting.Stroage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    // 스토리지에 항목 추가 API
    @PostMapping("/add")
    public String addItemToStorage(@RequestBody Storage storage) {
        return storageService.addItemToStorage(storage);
    }

}
