package org.example.tripting.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Autowired: Spring이 자동으로 필요한 의존성을 주입합니다.
    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 사용자 회원가입 메서드
    @Override
    public User signUp(User user) {
        // 사용자 비밀번호 해싱
        String hashedPassword = bCryptPasswordEncoder.encode(user.getUserPw());
        user.setUserPw(hashedPassword);
        // 해싱된 비밀번호를 가진 사용자 객체를 데이터베이스에 저장
        return userRepository.save(user);
    }

    // 사용자 아이디로 사용자 정보를 가져오는 메서드
    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    // 사용자 로그인 메서드
    @Override
    public User login(String userId, String password) {
        User user = userRepository.findByUserId(userId);
        // 사용자가 존재하고 비밀번호가 일치하는지 확인
        if (user != null && bCryptPasswordEncoder.matches(password, user.getUserPw())) {
            return user; // 비밀번호가 일치하면 사용자 객체 반환
        }
        return null; // 아이디나 비밀번호가 일치하지 않는 경우 null 반환
    }

    // 사용자 아이디가 존재하는지 확인하는 메서드
    @Override
    public boolean isUserIdExist(String userId) {
        User user = userRepository.findByUserId(userId);
        return user != null; // 사용자가 존재하면 true, 아니면 false 반환
    }
}
