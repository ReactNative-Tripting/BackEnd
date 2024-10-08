package org.example.tripting.point;

import org.example.tripting.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Autowired: Spring이 자동으로 필요한 의존성을 주입합니다.
    @Autowired
    public PointServiceImpl(PointRepository pointRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.pointRepository = pointRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 사용자 포인트 적립
    @Override
    public Point pointEarn(Point point, Point user) {
        // userId로 기존 사용자 정보 조회
        Point existingPoint = pointRepository.findByUserId(user.getUserId());

        if (existingPoint != null) {
            // 기존 포인트에 새로운 포인트 추가
            existingPoint.setPoint(existingPoint.getPoint() + point.getPoint());
            return pointRepository.save(existingPoint); // 업데이트된 포인트 저장
        } else {
            // 사용자가 없으면 에러 반환
            throw new IllegalArgumentException("User not found");
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
        Point existingPoint = pointRepository.findByUserId(point.getUserId());

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
            throw new IllegalArgumentException("User not found with ID: " + point.getUserId());
        }
    }
}
