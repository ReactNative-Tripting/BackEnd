package org.example.tripting.room;

import java.util.List;

public interface RoomService {

    Room createRoom(String roomName);

    void deleteRoom(String inviteCode);

    Room joinRoom(String inviteCode, String userId);

    void exitRoom(String inviteCode, String userId);

    List<String> getMembersByInviteCode(String inviteCode); // 멤버 목록을 반환하는 메서드 추가
}
