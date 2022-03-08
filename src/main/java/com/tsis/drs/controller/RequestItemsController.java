package com.tsis.drs.controller;

import com.tsis.drs.dto.Document;
import com.tsis.drs.dto.Item;
import com.tsis.drs.dto.Reqitemsresponse;
import com.tsis.drs.dto.Requestitems;
import com.tsis.drs.service.DocumentService;
import com.tsis.drs.service.RequestItemsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/request")
public class RequestItemsController {

    @Autowired
    RequestItemsService requestItemsService;

    @ApiOperation(value = "모든 결재 문서를 반환한다.", response = List.class)
    @GetMapping
    public ResponseEntity<List<Requestitems>> selectAll() throws Exception {
        System.out.println("selectAll");
        return new ResponseEntity<List<Requestitems>>(requestItemsService.selectAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "하나의 결재 문서를 반환한다.", response = Requestitems.class)
    @GetMapping("{id}")
    public ResponseEntity<Requestitems> selectOne(@PathVariable String id) throws Exception {
        return new ResponseEntity<Requestitems>(requestItemsService.selectOne(id), HttpStatus.OK);
    }

    @ApiOperation(value = "결재 문서, 핸드폰 종류 반환해주는 테이블", response = Requestitems.class)
    @PostMapping
    public void insertItem(@RequestBody Requestitems requestitems){
        requestItemsService.insetRequestitems(requestitems);
    }

    @ApiOperation(value = "관련된 결재문서와 담당자를 불러온다.", response = List.class)
    @GetMapping("req")
    public ResponseEntity<List<Reqitemsresponse>> selectReq() throws Exception {
        return new ResponseEntity<List<Reqitemsresponse>>(requestItemsService.selectReq(), HttpStatus.OK);
    }

}
