package org.example.tripting.point;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// 사용자 정보에 대한 MongoDB 저장소를 다루는 리포지토리 인터페이스
@Repository
public interface PointRepository extends MongoRepository<Point, String> {
    Point findByUserId(String userId);
}
