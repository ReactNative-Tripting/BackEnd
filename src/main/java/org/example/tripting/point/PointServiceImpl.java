package org.example.tripting.point;

import org.example.tripting.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final UserService userService;

    @Autowired
    public PointServiceImpl(PointRepository pointRepository, UserService userService) {
        this.pointRepository = pointRepository;
        this.userService = userService;
    }

    // 사용자 아이디로 사용자 정보를 가져오는 메서드
    @Override
    public Point getUserByUserId(String userId) {
        return pointRepository.findByUserId(userId);
    }

    // 사용자 아이디로 사용자 포인트 정보를 가져오는 메서드
    @Override
    public int getUserPointsByUserId(String userId) {
        Point point = pointRepository.findByUserId(userId);
        return point != null ? point.getPoint() : 0;  // 포인트 숫자만 반환
    }

    // 포인트 적립 및 사용
    @Override
    public Point modifyPoints(Point point, boolean isEarn) {
        // 사용자의 현재 포인트 정보를 가져옴
        Point existingPoint = pointRepository.findByUserId(point.getUser().getUserId());

        if (existingPoint == null) {
            // 기존 포인트 정보가 없으면 새로 생성
            existingPoint = new Point();
            existingPoint.setUser(userService.getUserByUserId(point.getUser().getUserId())); // 유저 설정
            existingPoint.setUserId(point.getUser().getUserId()); // userId 설정
            existingPoint.setPoint(0); // 초기 포인트 설정
        } else {
            // existingPoint가 있을 경우 user 설정
            existingPoint.setUser(userService.getUserByUserId(point.getUser().getUserId())); // 유저 설정
        }

        // 포인트를 적립할 경우
        if (isEarn) {
            int newTotal = existingPoint.getPoint() + point.getPoint();
            existingPoint.setPoint(newTotal);
        } else { // 포인트를 사용할 경우
            int newTotal = existingPoint.getPoint() - point.getPoint();
            if (newTotal < 0) {
                throw new IllegalArgumentException("포인트가 부족합니다.");
            }
            existingPoint.setPoint(newTotal);
        }

        // 업데이트된 포인트 정보를 저장
        return pointRepository.save(existingPoint);
    }
}
