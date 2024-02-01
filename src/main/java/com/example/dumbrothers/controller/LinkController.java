package com.example.dumbrothers.controller;


import com.example.dumbrothers.dto.LinkDto;
import com.example.dumbrothers.entity.Link;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.dumbrothers.service.DumService;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
public class LinkController {

    @Autowired
    private DumService dumService;

    //모든 url 출력
    @GetMapping("/dum")
    public ResponseEntity<List<LinkDto>> show() {
        return ResponseEntity.status(HttpStatus.OK).body(dumService.show());
    }

    //모든 tag 출력 (중복제거)
//    @GetMapping("/dum/tags")
//    public List<String> tags() {
//        return dumService.tags();
//    }
//
//    //특정 tag를 가지는 url 출력
//    @GetMapping("/dum/tags/{tags}")
//    public List<Link> tags(@PathVariable String tags) {
//        return dumService.tagsearch(tags);
//    }

    //folder id 기준으로 folder 가 가진 url 모두 출력
//    @GetMapping("/dum/folder/{id}")
//    public List<Link> inshow(@PathVariable Long id) {
//        return dumService.inshow(id);
//    }

    //url 입력
    @PostMapping("dum/input")
    public ResponseEntity<LinkDto> create(@RequestBody LinkDto dto) {

        //서비스에게 위임
        LinkDto createdDto = dumService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    //url id 기준으로 folder id 변경
    @PatchMapping("dum/{id}")
    public ResponseEntity<Link> update(@PathVariable Long id, @RequestBody LinkDto dto) {

        Link updatedDto = dumService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    //url id 기준으로 url 삭제
    @DeleteMapping("dum/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Link deleted = dumService.delete(id);

        if (deleted != null) {
            return ResponseEntity.status(HttpStatus.OK).body("ID값이 " + deleted.getId() + "인 url 삭제에 성공하였습니다.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID값이 " + id + "인 url 삭제에 실패하였습니다");
    }
}
