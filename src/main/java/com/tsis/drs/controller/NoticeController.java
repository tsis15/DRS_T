package com.tsis.drs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.tsis.drs.dto.Notice;
import com.tsis.drs.dto.User;
import com.tsis.drs.service.NoticeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/notice")
@Slf4j
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @ApiOperation(value = "게시글 생성 및 수정", response = String.class)
    @PostMapping
    public ResponseEntity<String> createNotice(@RequestBody Notice notice) {
        if (noticeService.createNotice(notice) == 1) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
    }
//    public PageInfo<Item> selectAll(@PathVariable int num) throws Exception {
//        int perPage = 10;
//        PageHelper.startPage(num,perPage);
//
////        List<Item> p = itemService.selectAll();
////        log.info("2 page: {}"+ p);
//
//        return PageInfo.of(itemService.selectAll());
//    }

    @ApiOperation(value = "검색 단어, 페이지 번호를 통해 모든 게시글를 반환한다.", response = List.class)
    @GetMapping("/{input}/{num}")
    public PageInfo<Notice> noticeSelectcondition(@PathVariable String input, @PathVariable String num) throws Exception {
        int perPage = 10;
        PageHelper.startPage(Integer.parseInt(num), perPage);
        return new PageInfo<Notice>(noticeService.noticeSelectcondition(input));
    }

    @ApiOperation(value = "페이지 번호를 통해 모든 게시글를 반환한다. (검색단어 없을시)", response = List.class)
    @GetMapping("/{num}")
    public PageInfo<Notice> noticeSelectAll(@PathVariable String num) throws Exception {
        int perPage = 10;
        PageHelper.startPage(Integer.parseInt(num), perPage);
        return new PageInfo<Notice>(noticeService.noticeSelectAll());
    }

    @ApiOperation(value = "게시글 삭제", response = String.class)
    @DeleteMapping("/{notice_id}")
    public ResponseEntity<String> deleteNotice(@PathVariable String notice_id) {

        if (noticeService.deleteNotice(notice_id) == 1) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
    }

}
