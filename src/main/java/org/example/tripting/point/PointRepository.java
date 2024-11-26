package org.example.tripting.point;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends MongoRepository<Point, String> {
    Point findByUserId(String userId); // userId로 포인트 조회 메서드
}
