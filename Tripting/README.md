# API Documentation

이 문서는 **User API**, **Point API**, **Place API**, 및 **Room API**에 대한 엔드포인트와 사용 예제를 제공합니다.

## User API

### 회원가입

- **POST** `http://localhost:8080/users`
- **Content-Type:** `application/json`

```json
{
  "userId": "test123",
  "username": "HongGildong",
  "password": "test123!!",
  "phoneNum": 01012341234,
  "email": "test123@gmail.com",
  "sex": "Male"
}
```
### 사용자 조회

- **GET** `http://localhost:8080/users/userid/test123`
- **Content-Type:** `application/json`

```json
{
"userId": "test123",
"password": "test123!!"
}
```
### 로그인
- **POST** `http://localhost:8080/login`
- **Content-Type:** `application/json`

```json
{
  "userId": "test123",
  "password": "test123!!"
}
```

### 존재여부 확인
- **GET** `http://localhost:8080/users/userid/test123/exists`

## Point API

### 유저 조회
- **GET** `http://localhost:8080/point/userid/test123`

### 포인트 적립
- **POST** `http://localhost:8080/point/earn`
- **Content-Type:** `application/json`

```json
{
  "user": {
    "userId": "test123"
  },
  "point": 100
}
```

### 포인트 사용
- **POST** `http://localhost:8080/point/use`
- **Content-Type:** `application/json`

```json
{
  "userId": "user123",
  "point": 5000
}
```

## Place API

### 장소 등록
- **POST** `http://localhost:8080/place/add`
- **Content-Type:** `application/json`

```json
{
  "placeName": "천안종합운동장",
  "placeImgUrl": "",
  "address": "충청남도 천안 서북구 백석동 255-1",
  "explain": "종합운동장",
  "type": "activity"
}
```

### 주소 조회
- **GET** `http://localhost:8080/place/placename/천안종합운동장`

## Room API

### 방 생성
- **POST** `http://localhost:8080/rooms/create`
- **Content-Type:** `application/json`

```json
{
  "roomName": "Test Room"
}
```

### 방 입장
- **POST** `http://localhost:8080/rooms/join`
- **Content-Type:** `application/json`

```json
{
  "inviteCode": "6a868692",
  "userId": "test123"
}
```
### 방 삭제
- **DELETE** `http://localhost:8080/rooms/delete?inviteCode=6a868692`
### 방 나가기
- **POST** `http://localhost:8080/rooms/exit?inviteCode=6a868692&userId=test123`


## 참고사항
- **위의 API 호출은 모두 로컬 서버에서 실행된다고 가정합니다.**
- **JSON 요청 본문에 포함된 데이터 형식은 정확해야 하며, 적절한 인증 및 권한 부여가 필요할 수 있습니다.**