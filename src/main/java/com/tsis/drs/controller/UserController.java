package com.tsis.drs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsis.drs.dto.Item;
import com.tsis.drs.dto.Notice;
import com.tsis.drs.dto.User;
import com.tsis.drs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

//    @ApiOperation(value = "모든 회원 정보를 반환한다.", response = List.class)
//    @GetMapping("paging/{num}")
//    public PageInfo<User> selectAll(@PathVariable int num) throws Exception {
//        int perPage = 10;
//        PageHelper.startPage(num,perPage);
//        PageInfo<User> of = PageInfo.of(userService.selectAll(),num);
//
//        log.info(String.valueOf(of.getList()));
//
//        return of;
//    }

    @ApiOperation(value = "검색 단어, 페이지 번호를 통해 모든 회원 목록을 반환한다.", response = List.class)
    @GetMapping("/{input}/{num}")
    public PageInfo<User> Selectcondition(@PathVariable String input, @PathVariable String num) throws Exception {
        int perPage = 10;
        PageHelper.startPage(Integer.parseInt(num), perPage);
        return new PageInfo<User>(userService.Selectcondition(input));
    }

    @ApiOperation(value = "페이지 번호를 통해 모든 회원 목록 반환한다. (검색단어 없을시)", response = List.class)
    @GetMapping("/{num}")
    public PageInfo<User> SelectAll(@PathVariable String num) throws Exception {
        int perPage = 10;
        PageHelper.startPage(Integer.parseInt(num), perPage);
        List<User> users = userService.selectAll();
        log.info(users.toString());
        PageInfo<User> userPageInfo = new PageInfo<User>(users);
        log.info("게시글 정보 >>>>"+ userPageInfo);
        return userPageInfo;
    }

    @ApiOperation(value = "하나의 회원 정보를 반환한다", response = User.class)
    @GetMapping("get/{id}")
    public ResponseEntity<User> selectOne(@PathVariable String id) throws Exception {
        User user = userService.selectOne(id); // 로그인 한 아이디
        return new ResponseEntity<User>(userService.selectOne(id), HttpStatus.OK);
    }

    @ApiOperation(value = "하나의 회원을 관리자로 만든다")
    @PutMapping("up/{id}/{role}")
    public void update(@PathVariable String id, @PathVariable int role) throws Exception {
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("user_id",id);
        map.put("role", role);
        userService.update(map);
    }

    @ApiOperation(value = "해당 ID의 검토자들을 반환한다", response = List.class)
    @GetMapping("reviewer/{id}")
    public ResponseEntity<List<User>> selectAllReviewer(@PathVariable String id) throws Exception {
        return new ResponseEntity<List<User>>(userService.selectAllReviewer(id), HttpStatus.OK);
    }

}
