package org.example.tripting.Stroage;

import java.util.ArrayList;

public interface StorageService {
    String addItemToStorage(Storage storage);

    ArrayList<StorageDTO> selectItemFromStorage(Storage storage);
}

