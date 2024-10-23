package org.example.tripting.point;

import org.example.tripting.user.User;
import org.example.tripting.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final UserService userService; // UserService 주입

    @Autowired
    public PointServiceImpl(PointRepository pointRepository, UserService userService) {
        this.pointRepository = pointRepository;
        this.userService = userService; // UserService 초기화
    }

    @Override
    public Point pointEarn(Point point) {
        // 사용자 객체가 null인지 확인
        if (point.getUser() == null) {
            throw new IllegalArgumentException("User not found");
        }

        // User 객체의 ID로 기존 포인트 조회
        Point existingPoint = pointRepository.findByUserId(point.getUser().getId());

        if (existingPoint != null) {
            // 기존 포인트에 새로운 포인트 추가
            existingPoint.setPoint(existingPoint.getPoint() + point.getPoint());
            return pointRepository.save(existingPoint); // 업데이트된 포인트 저장
        } else {
            // 새 포인트 객체 생성 후 userId 설정
            point.setUserId(point.getUser().getUserId()); // userId 설정
            return pointRepository.save(point); // 새 포인트 저장
        }
    }


    // 사용자 아이디로 사용자 정보를 가져오는 메서드
    @Override
    public Point getUserByUserId(String userId) {
        return pointRepository.findByUserId(userId);
    }

    // 사용자 포인트 사용
    @Override
    public Point pointUse(Point point) {
        // 유효성 검사
        if (point.getPoint() <= 0) {
            throw new IllegalArgumentException("Point value must be greater than 0");
        }

        // userId로 기존 사용자 정보 조회
        // 유저 확인 로직 제거
        Point existingPoint = pointRepository.findByUserId(point.getUser().getUserId());

        if (existingPoint != null) {
            // 포인트가 충분한지 확인
            if (existingPoint.getPoint() < point.getPoint()) {
                throw new IllegalArgumentException("Insufficient points");
            }

            // 기존 포인트에서 사용한 포인트 차감
            existingPoint.setPoint(existingPoint.getPoint() - point.getPoint());
            return pointRepository.save(existingPoint); // 업데이트된 포인트 저장
        } else {
            // 사용자가 없으면 IllegalArgumentException 던짐
            throw new IllegalArgumentException("User not found with ID: " + point.getUser().getUserId());
        }
    }
}