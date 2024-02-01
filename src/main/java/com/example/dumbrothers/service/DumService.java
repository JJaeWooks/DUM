package com.example.dumbrothers.service;

import com.example.dumbrothers.connect.LinkScrap;
import com.example.dumbrothers.dto.LinkDto;
import com.example.dumbrothers.entity.Link;
import com.example.dumbrothers.entity.Folder;
import com.example.dumbrothers.repository.DumRepository;
import com.example.dumbrothers.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class DumService {

    @Autowired
    private DumRepository dumRepository;

    @Autowired
    private FolderRepository folderRepository;
    private ChatService chatService;
    public DumService(ChatService chatService) {
        this.chatService = chatService;
    }

    public LinkDto create(LinkDto dto) {
        Long dumNum=dto.getFolderId();
        if (dumNum==null) {
            dumNum=1L;
        }
        String url = dto.getLink();

        try {
            Map<String, String> ogTag = LinkScrap.handleSendText(url);
            System.out.println("########"+ogTag.get("title")+"#######" + ogTag.get("image")+ogTag.get("description")+ogTag.get("head"));
            if(ogTag.get("title")==null)
                dto.setTitle(ogTag.get("head"));
            else
                dto.setTitle(ogTag.get("title"));
            dto.setImage(ogTag.get("image"));
            dto.setDescription(ogTag.get("description"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String parameterValue = dto.getDescription() + dto.getTitle();
//        String input = chatService.getTags(parameterValue);
//        input = input.substring(2, input.length() - 2);
//        String[] tags = input.split("\",\"");


//        if(tags[0].length() < 8) {
//            dto.setFirstTag(tags[0]);
//        } else { dto.setFirstTag("기타");}
//
//        if(tags[1].length() < 8) {
//            dto.setSecondTag(tags[1]);
//        } else { dto.setSecondTag("기타");}
//
//        if(tags[2].length() < 8) {
//            dto.setThirdTag(tags[2]);
//        } else { dto.setThirdTag("기타");}

        Folder folder = folderRepository.findById(dumNum)
                .orElseThrow(()->new IllegalArgumentException("주소 생성 실패 대상 폴더가 없습니다"));

        Link link = Link.createDum(dto,folder);
        Link created=dumRepository.save(link);

        return LinkDto.toDto(created);
    }

    public List<Link> inshow(Long id) {
        List<Link> linkList;

        try {
            linkList = (List<Link>) show().stream()
                    .filter(dum -> dum.showFolderId().equals(id))
                    .toList();
        } catch (Exception e) {
            // 로깅, 오류 추적 및 처리를 수행하십시오.
            linkList = Collections.emptyList();
        }
        return linkList;
    }

    public List<LinkDto> show() {

        List<Link> links=dumRepository.findAllOrderByDescId();
        return links.stream()
                .map(LinkDto::toDto)
                .collect(Collectors.toList());
    }

//    public Dum create(DumForm dto) {
//        Dum dum=dto.toEntity();
//        if(dum.getId()!=null)
//            return null;
//        return dumRepository.save(dum);
//    }

    public Link delete(Long id) {
        //대상찾기
        Link target=dumRepository.findById(id).orElse(null);
        //잘못된 요청처리
        if (target == null){
            return null;
        }

        //대상 삭제
        dumRepository.delete(target);
        return target;
    }

//    public List<String> tags() {
//
//        List<String> tagList =  show().stream()
//                .flatMap(dum -> dum.showTags().stream())
//                .distinct()
//                .collect(Collectors.toList());
//
//        Collections.sort(tagList);
//        return tagList;
//    }

//    public List<Link> tagsearch(String tags) {
//        List<Link> linkList;
//
//        try {
//            linkList = (List<Link>) show().stream()
//                    .filter(dum -> dum.showTags().contains(tags))
//                    .toList();
//        } catch (Exception e) {
//            // 로깅, 오류 추적 및 처리를 수행하십시오.
//            linkList = Collections.emptyList();
//        }
//        return linkList;
//    }

    public Link update(Long id, LinkDto dto) {
        //댓글 조회 및 예외 발생
        Link target=  dumRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 수정실패 대상 댓글이 없습니다")
                );

        Long a=dto.getFolderId();
        Folder folder =folderRepository.findById(a)
                .orElseThrow(()->new IllegalArgumentException("폴더가 없어요")
                );

        target.setFolder(folder);
        Link b = dumRepository.save(target);

        return target;
    }

}
