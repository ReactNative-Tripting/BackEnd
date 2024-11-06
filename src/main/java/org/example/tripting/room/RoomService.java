package org.example.tripting.room;


public interface RoomService {

    Room createRoom(String roomName);

    void deleteRoom(String inviteCode);

    Room joinRoom(String inviteCode, String userId);

    void exitRoom(String inviteCode, String userId);
}
