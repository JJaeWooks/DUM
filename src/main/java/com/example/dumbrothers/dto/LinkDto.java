package com.example.dumbrothers.dto;

import com.example.dumbrothers.entity.Link;
import com.example.dumbrothers.entity.Member;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor
@Setter
@Builder
public class LinkDto {
    private Long id;
    private String link;
    private String firstTag;
    private String secondTag;
    private String thirdTag;
    private Member userId;
    private Long folderId;
    private String title;
    private String image;
    private String description;


//    public static LinkDto toDto(Dum dum){
//        return new LinkDto(
//                dum.getId(),
//                dum.getLink(),
//                dum.getFirstTag(),
//                dum.getSecondTag(),
//                dum.getThirdTag(),
//                dum.getUserId(),
//                dum.getFolder().getFolderId(),
//                dum.getTitle(),
//                dum.getImage(),
//                dum.getDescription()
//        );
//

    public static LinkDto toDto(Link link){
        return LinkDto.builder()
                .id(link.getId())
                .link(link.getLink())
                .firstTag(link.getFirstTag())
                .secondTag(link.getSecondTag())
                .thirdTag(link.getThirdTag())
                .userId(link.getUserId())
                .folderId(link.getFolder().getFolderId())
                .title(link.getTitle())
                .image(link.getImage())
                .description(link.getDescription())
                .build();
    }


}


