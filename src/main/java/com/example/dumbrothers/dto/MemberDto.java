package com.example.dumbrothers.dto;


import com.example.dumbrothers.entity.Member;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    private Long id;
    private String userId;
    private String nickname;
    private String address;
    private String phone;
    private String profileImg;

    static public MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .userId(member.getUsername())
                .nickname(member.getNickname())
                .address(member.getAddress())
                .phone(member.getPhone())
                .profileImg(member.getProfileImg()).build();
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(userId)
                .nickname(nickname)
                .address(address)
                .phone(phone)
                .profileImg(profileImg).build();
    }
}