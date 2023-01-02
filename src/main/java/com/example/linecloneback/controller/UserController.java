package com.example.linecloneback.controller;

import com.example.linecloneback.dto.LoginRequestDto;
import com.example.linecloneback.dto.MsgResponseDto;
import com.example.linecloneback.dto.SignupRequestDto;
import com.example.linecloneback.service.UserService;
import com.example.linecloneback.util.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<MsgResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {   //(@RequestBody @Valid SignupRequestDto signupRequestDto)?
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(new MsgResponseDto("회원가입 완료", HttpStatus.OK.value()));
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<MsgResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.ok(new MsgResponseDto("로그인 완료",HttpStatus.OK.value()));
    }

    // 아이디 중복 확인
    @PostMapping("/idDupleCheck")
    public ResponseEntity<MsgResponseDto> idCheck(@RequestBody @Valid SignupRequestDto dto) {
        return ResponseEntity.ok(userService.idCheck(dto));
    }

    // 닉네임 중복 확인
    @PostMapping("/nickDupleCheck")
    public ResponseEntity<MsgResponseDto> nickCheck(@RequestBody SignupRequestDto dto) {
        return ResponseEntity.ok(userService.nickCheck(dto));
    }
}
