package org.example.tripting.Stroage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

    private final StorageRepository storageRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    public String addItemToStorage(Storage storage) {
        // userId로 기존 Storage 객체 찾기
        Storage existingStorage = storageRepository.findByUserId(storage.getUserId());

        if (existingStorage != null) {
            // 기존 저장소에 아이템 추가
            existingStorage.getItems().addAll(storage.getItems());
            storageRepository.save(existingStorage);  // 저장소 업데이트
        } else {
            // 기존 저장소가 없으면 새로운 저장소 객체 생성
            storageRepository.save(storage);  // 새로 저장소 추가
        }

        return "Item added to storage successfully";
    }
}

