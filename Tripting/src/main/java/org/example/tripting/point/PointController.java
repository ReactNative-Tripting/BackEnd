    package org.example.tripting.point;

    import org.example.tripting.user.User;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    // 사용자 관련 HTTP 요청을 처리하는 컨트롤러
    @RestController
    @RequestMapping("/point")
    public class PointController {

        private final PointService pointService;

        @Autowired
        public PointController(PointService pointService) {
            this.pointService = pointService;
        }

        // 특정 아이디를 가진 사용자 조회
        @GetMapping("/userid/{userId}")
        public Point getUserByUserId(@PathVariable String userId) {
            return pointService.getUserByUserId(userId);
        }


        // 사용자 포인트 적립
        @PostMapping("/earn")
        public ResponseEntity<?> pointEarn(@RequestBody Point point) {
            try {
                Point updatedPoint = pointService.pointEarn(point, point);
                return new ResponseEntity<>(updatedPoint, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        }

        // 사용자 포인트 사용
        @PostMapping("/use")
        public ResponseEntity<Object> usePoints(@RequestBody Point point) {
            try {
                Point updatedPoint = pointService.pointUse(point);
                return new ResponseEntity<>(updatedPoint, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

    }