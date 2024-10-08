package org.example.tripting.point;

import org.example.tripting.user.User;

import java.util.List;

// 사용자 관리 서비스 인터페이스
public interface PointService {

    // 특정 userID를 가진 사용자 조회
    Point getUserByUserId(String userId);

    // 사용자 포인트 적립
    Point pointEarn(Point point, Point user);

    // 사용자 포인트 사용
    Point pointUse(Point point);

}
