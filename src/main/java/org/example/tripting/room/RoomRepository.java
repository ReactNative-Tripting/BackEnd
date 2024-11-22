package org.example.tripting.room;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
    Room findByInviteCode(String inviteCode);  // 초대 코드로 방 찾기
}
