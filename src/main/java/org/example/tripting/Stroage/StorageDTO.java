package org.example.tripting.Stroage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StorageDTO {
    private String id;
    private String image;
    private String name;
    private String point;
    private String date;
}
