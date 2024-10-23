package org.example.tripting.point;

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

    @GetMapping("/userid/{userId}")
    public Point getUserByUserId(@PathVariable String userId) {
        return pointService.getUserByUserId(userId);
    }

    // 포인트 적립
    @PostMapping("/earn")
    public ResponseEntity<?> pointEarn(@RequestBody Point point) {
        try {
            return new ResponseEntity<>(pointService.modifyPoints(point, true), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 포인트 사용
    @PostMapping("/use")
    public ResponseEntity<Object> usePoints(@RequestBody Point point) {
        try {
            return new ResponseEntity<>(pointService.modifyPoints(point, false), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}