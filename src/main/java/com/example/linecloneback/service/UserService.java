package com.example.linecloneback.service;

import com.example.linecloneback.dto.LoginRequestDto;
import com.example.linecloneback.dto.MsgResponseDto;
import com.example.linecloneback.dto.SignupRequestDto;
import com.example.linecloneback.repository.UserRepository;
import com.example.linecloneback.util.error.CustomException;
import com.example.linecloneback.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import com.example.linecloneback.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

import static com.example.linecloneback.util.error.ErrorCode.*;

@Service
//@Validated          //추가
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;  //의존성 주입(DI) --> jwtUtil.class 에서 @Component 로 빈을 등록했기때문에 가능
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public void signup(@Valid SignupRequestDto signupRequestDto) {      //@Valid 추가 주의!
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword()); //저장하기 전에 password 를 Encoder 한다
        String nickname = signupRequestDto.getNickname();

        Optional<User> found = userRepository.findByUsername(username);  //userRepository 에 구현하기
        if (found.isPresent()) {
            throw new CustomException(DUPLICATED_USERNAME);
        }

        User user = new User(username, password, nickname);
        userRepository.save(user);
    }

    //로그인 구현
    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(NOT_FOUND_USER)
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(NOT_MATCH_INFORMATION);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
    }

    public MsgResponseDto idCheck(SignupRequestDto dto) {
        String username = dto.getUsername();

        if (userRepository.existsByUsername(username)) {
            return new MsgResponseDto("아이디 중복", 409);
        } else {
            return new MsgResponseDto("아이디 사용 가능", HttpStatus.OK.value());
        }
    }

    // 닉네임 중복 확인
    public MsgResponseDto nickCheck(SignupRequestDto dto) {
        String nickname = dto.getNickname();

        if (userRepository.existsByNickname(nickname)) {
            return new MsgResponseDto("닉네임 중복", 409);
        } else {
            return new MsgResponseDto("닉네임 사용 가능", HttpStatus.OK.value());
        }
    }
}
