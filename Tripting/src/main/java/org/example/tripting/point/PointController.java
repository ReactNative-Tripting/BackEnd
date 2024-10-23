package org.example.tripting.point;

import org.example.tripting.user.User;
import org.example.tripting.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 사용자 관련 HTTP 요청을 처리하는 컨트롤러
@RestController
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;
    private final UserService userService;

    @Autowired
    public PointController(PointService pointService, UserService userService) {
        this.pointService = pointService;
        this.userService = userService;
    }

    // 포인트 적립 (PUT 메서드 사용)
    @PostMapping("/earn")
    public ResponseEntity<?> pointEarn(@RequestBody Point point) {
        try {
            // userId를 사용하여 사용자 존재 여부를 확인
            if (point.getUser() == null || point.getUser().getUserId() == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            // userId로 기존 사용자 정보 조회
            User user = userService.getUserByUserId(point.getUser().getUserId());
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            Point updatedPoint = pointService.pointEarn(point);
            return new ResponseEntity<>(updatedPoint, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 포인트 사용 (PUT 메서드 사용)
    @PutMapping("/use")
    public ResponseEntity<Object> usePoints(@RequestBody Point point) {
        try {
            Point updatedPoint = pointService.pointUse(point);
            return new ResponseEntity<>(updatedPoint, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}