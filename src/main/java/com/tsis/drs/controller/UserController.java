package com.tsis.drs.controller;

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

    @ApiOperation(value = "모든 회원 정보를 반환한다.", response = List.class)
    @GetMapping
    public ResponseEntity<List<User>> selectAll() throws Exception {
        return new ResponseEntity<List<User>>(userService.selectAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "하나의 회원 정보를 반환한다", response = User.class)
    @GetMapping("/{id}")
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
