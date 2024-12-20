package org.example.tripting.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // 방 생성 (유저 아이디도 함께 받아서 멤버 목록에 추가)
    @PostMapping("/create")
    public Room createRoom(@RequestBody Map<String, String> request) {
        String roomName = request.get("roomName");
        String userId = request.get("userId");  // 유저 아이디 추가
        return roomService.createRoom(roomName, userId);  // 유저 아이디를 서비스로 전달
    }

    // 방 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRoom(@RequestParam String inviteCode) {
        try {
            roomService.deleteRoom(inviteCode);
            return ResponseEntity.noContent().build(); // 성공적으로 삭제되면 204 No Content 응답
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // 유효하지 않은 초대 코드면 400 Bad Request 응답
        }
    }

    // 방에 참여
    @PostMapping("/join")
    public ResponseEntity<Room> joinRoom(@RequestBody Map<String, String> request) {
        String inviteCode = request.get("inviteCode");
        String userId = request.get("userId");
        try {
            Room room = roomService.joinRoom(inviteCode, userId);
            return ResponseEntity.ok(room); // 방 참여 성공 시 200 OK 응답과 함께 방 정보 반환
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // 초대 코드가 유효하지 않으면 400 Bad Request 응답
        }
    }

    // 방에서 나가기
    @DeleteMapping("/exit")
    public ResponseEntity<Void> exitRoom(@RequestParam String inviteCode, @RequestParam String userId) {
        try {
            roomService.exitRoom(inviteCode, userId);
            return ResponseEntity.noContent().build(); // 성공적으로 나가면 204 No Content 응답
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // 유효하지 않은 초대 코드면 400 Bad Request 응답
        }
    }

    // 방 멤버 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<List<String>> getMembers(@RequestParam String inviteCode) {
        try {
            List<String> members = roomService.getMembersByInviteCode(inviteCode);
            return ResponseEntity.ok(members); // 멤버 목록 반환
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // 초대 코드 유효하지 않으면 400 Bad Request 응답
        }
    }
}
