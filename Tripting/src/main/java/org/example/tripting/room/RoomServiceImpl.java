package org.example.tripting.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createRoom(String roomName) {
        Room room = new Room();
        room.setRoomName(roomName);

        String inviteCode;
        do {
            inviteCode = generateInviteCode(); // 초대 코드 생성
        } while (isInviteCodeExists(inviteCode)); // 초대 코드 중복 확인

        room.setInviteCode(inviteCode); // 중복이 없는 초대 코드 설정
        return roomRepository.save(room); // 방 저장
    }

    @Override
    public void deleteRoom(String inviteCode) {
        Room room = roomRepository.findByInviteCode(inviteCode);
        if (room != null) {
            roomRepository.delete(room); // 방 삭제
        } else {
            throw new RuntimeException("초대 코드가 유효하지 않습니다. 방을 찾을 수 없습니다.");
        }
    }

    @Override
    public Room joinRoom(String inviteCode, String userId) {
        Room room = roomRepository.findByInviteCode(inviteCode);
        if (room != null) {
            room.getMembers().add(userId);
            roomRepository.save(room);
            return room;
        } else {
            throw new RuntimeException("초대 코드가 유효하지 않습니다.");
        }
    }

    @Override
    public void exitRoom(String inviteCode, String userId) {
        Room room = roomRepository.findByInviteCode(inviteCode);
        if (room != null) {
            room.getMembers().remove(userId); // 사용자 제거
            roomRepository.save(room); // 변경 사항 저장
        } else {
            throw new RuntimeException("초대 코드가 유효하지 않습니다. 방을 찾을 수 없습니다.");
        }
    }

    public String generateInviteCode() {
        return UUID.randomUUID().toString().substring(0, 8); // 8자리 코드 생성
    }

    private boolean isInviteCodeExists(String inviteCode) {
        return roomRepository.findByInviteCode(inviteCode) != null;
    }
}
