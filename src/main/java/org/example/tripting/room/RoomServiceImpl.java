package org.example.tripting.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createRoom(String roomName, String userId) {
        Room room = new Room();
        room.setRoomName(roomName);

        // 초대 코드 생성
        String inviteCode;
        do {
            inviteCode = generateInviteCode();
        } while (isInviteCodeExists(inviteCode)); // 초대 코드 중복 확인

        room.setInviteCode(inviteCode);

        // 최초 멤버로 유저 아이디 추가
        room.getMembers().add(userId);

        return roomRepository.save(room);  // 방 저장
    }

    @Override
    public void deleteRoom(String inviteCode) {
        Room room = roomRepository.findByInviteCode(inviteCode);
        if (room != null) {
            roomRepository.delete(room);  // 방 삭제
        } else {
            throw new RuntimeException("초대 코드가 유효하지 않습니다. 방을 찾을 수 없습니다.");
        }
    }

    @Override
    public Room joinRoom(String inviteCode, String userId) {
        Room room = roomRepository.findByInviteCode(inviteCode);
        if (room != null) {
            room.getMembers().add(userId);  // 멤버 추가
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
            room.getMembers().remove(userId);  // 사용자 제거

            // 멤버가 없으면 방 삭제
            if (room.getMembers().isEmpty()) {
                roomRepository.delete(room);  // 방 삭제
            } else {
                roomRepository.save(room);  // 변경 사항 저장
            }
        } else {
            throw new RuntimeException("초대 코드가 유효하지 않습  니다. 방을 찾을 수 없습니다.");
        }
    }


    @Override
    public List<String> getMembersByInviteCode(String inviteCode) {
        Room room = roomRepository.findByInviteCode(inviteCode);
        if (room != null) {
            return room.getMembers();  // 해당 방의 멤버 목록 반환
        } else {
            throw new RuntimeException("초대 코드가 유효하지 않습니다.");
        }
    }

    private String generateInviteCode() {
        return UUID.randomUUID().toString().substring(0, 8);  // 8자리 초대 코드 생성
    }

    private boolean isInviteCodeExists(String inviteCode) {
        return roomRepository.findByInviteCode(inviteCode) != null;  // 초대 코드 중복 체크
    }
}
