package com.example.dumbrothers.service;

import com.example.dumbrothers.dto.SignUpDto;
import com.example.dumbrothers.dto.TokenInfo;
import com.example.dumbrothers.entity.Member;
import com.example.dumbrothers.jwt.JwtTokenProvider;
import com.example.dumbrothers.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.Collections;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenInfo login(String username, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }
@Transactional
    public Member signUp(SignUpDto memberSignUpDto) {
        if(memberRepository.findByUsername(memberSignUpDto.getMemberId()).orElse(null)!=null){
            throw new RuntimeException("이미 가입되어있는 유저입니다");
        }
        Member member=memberSignUpDto.toEntity(memberSignUpDto.getPassword(), Collections.singletonList("USER"));
        return memberRepository.save(member);
    }
}