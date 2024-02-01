package com.example.dumbrothers.dto;

import com.example.dumbrothers.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {

    private String memberId;
    private String password;
    private String nickname;
    private String address;
    private String phone;
    private String profileImg;
    private List<String> roles = new ArrayList<>();

    public Member toEntity(String encodedPassword, List<String> roles) {

        return Member.builder()
                .username(memberId)
                .password(encodedPassword)
                .nickname(nickname)
                .address(address)
                .phone(phone)
                .profileImg(profileImg)
                .roles(roles)
                .build();
    }
}