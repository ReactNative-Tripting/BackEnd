package org.example.tripting.point;

public interface PointService {

    // 특정 userID를 가진 사용자 조회
    Point getUserByUserId(String userId); // 이 메서드는 PointServiceImpl에서 구현해야 함

    // 특정 userID를 가진 사용자 포인트 조회
    int getUserPointsByUserId(String userId); // 포인트 숫자만 반환할 메서드 추가

    // 사용자 포인트 적립 및 사용 (새로 추가된 메서드)
    Point modifyPoints(Point point, boolean isEarn); // 적립 여부를 확인하는 메서드
}
