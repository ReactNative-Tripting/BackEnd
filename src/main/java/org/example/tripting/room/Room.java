package org.example.tripting.room;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Room")
@Getter
@Setter
public class Room {

    @Id
    private String id;

    private String roomName;
    private String inviteCode;
    private List<String> members = new ArrayList<>();  // 방 멤버 목록
}
