package org.example.tripting.point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    // 포인트 적립
    @PatchMapping("/earn")
    public ResponseEntity<?> pointEarn(@RequestBody Point point) {
        try {
            return new ResponseEntity<>(pointService.modifyPoints(point, true), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 포인트 사용
    @PatchMapping("/use")
    public ResponseEntity<Object> usePoints(@RequestBody Point point) {
        try {
            return new ResponseEntity<>(pointService.modifyPoints(point, false), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
