package com.example.dumbrothers.controller;


import com.example.dumbrothers.dto.*;
import com.example.dumbrothers.entity.Member;
import com.example.dumbrothers.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody SignInDto memberSigninDto) {
        String memberId = memberSigninDto.getUsername();
        String password = memberSigninDto.getPassword();
        TokenInfo tokenInfo = memberService.login(memberId, password);
        return tokenInfo;
    }

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody SignUpDto memberSignUpDto){
        Member newMember =memberService.signUp(memberSignUpDto);
        return ResponseEntity.status(HttpStatus.OK).body(newMember);
    }


    @GetMapping("/mypage")
    public void getMyQuestionsTitle (Principal principal) {

        System.out.println(principal.getName());
    }
}