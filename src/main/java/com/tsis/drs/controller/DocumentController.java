package com.tsis.drs.controller;

import com.github.pagehelper.PageHelper;
import com.tsis.drs.dto.*;
import com.tsis.drs.service.DocumentService;
import com.tsis.drs.service.ItemService;
import com.tsis.drs.service.RequestItemsService;
import com.tsis.drs.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/document")
@Slf4j
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    RequestItemsService requestItemsService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "num = 페이징 번호, id= 사번을 통한 Document 결과 조회 (drafted_user_id : 이름)", response = List.class)
    @GetMapping("/{num}/{user_id}")
    public ResponseEntity<List<Document>> selectDocument(@PathVariable String num, @PathVariable String user_id) throws Exception {
        User user = userService.selectOne(user_id);
        int perPage = 8;
        if (user.getRole()==2) {
            PageHelper.startPage(Integer.parseInt(num), perPage);
            return new ResponseEntity<List<Document>>(documentService.selectAll(), HttpStatus.OK);
        } else {
            PageHelper.startPage(Integer.parseInt(num), perPage);
            return new ResponseEntity<List<Document>>(documentService.showUserD(user_id), HttpStatus.OK);
        }
    }

//    @ApiOperation(value = "하나의 결재 문서를 반환한다.", response = Document.class)
//    @GetMapping("{id}")
//    public ResponseEntity<Document> selectOne(@PathVariable String id) throws Exception {
//        return new ResponseEntity<Document>(documentService.selectOne(id), HttpStatus.OK);
//    }

    @ApiOperation(value = "결재문서 승인시) 단말기 상태가 '예약' -> '대여불가,예약불가'로 변경, 아니라면 '대여중,예약가능'으로 변경")
    @PutMapping("approval/{document_id}")
    public void documentApproval(@PathVariable String document_id) throws Exception {
        log.info("documentApproval >>>" + document_id);
        documentService.documentApproval(document_id);
    }

    /**
     * 기안문 최초 생성
     * 예약인지 대여인지 파라메터 필요!!
     */
    @ApiOperation(value = "결재문서 기안문 최초저장", response = Document.class)
    @PostMapping("save/{d_id}/{r_id}")
    public void insertItem(@PathVariable String d_id, @PathVariable String r_id, @RequestParam List<String> ids) {
        Date d = new Date();
        Document document = new Document();
        UUID id = UUID.randomUUID();
        document.setDocument_id(id.toString());
        document.setDrafted_user_id(d_id);
        document.setReviewed_user_id(r_id);
        document.setDocument_status("검토중");
        document.setRequestdate(d);
        document.getDocument_status();
        documentService.insertDocument(document);

        for (String iid : ids) {
            Requestitems requestitems = new Requestitems();
            requestitems.setDocument_id(id.toString());
            requestitems.setItem_id(iid);
            requestitems.setRental_date(document.getRentaldate());
            requestitems.setReturn_date(document.getRentaldate());
            requestItemsService.insetRequestitems(requestitems);
        }

    }

    /**
     * 관리자 승인
     */
    @ApiOperation(value = "결재문서 검토자, 결재자 상태 업데이트")
    @PutMapping("update/{user_id}/{document_id}/{status}")
    public void documentUpdate(@PathVariable String user_id, @PathVariable String document_id, @PathVariable String status) throws Exception {
        log.info("documentUpdate user_id >>>" + user_id + ", document_id >> " + document_id + ", status >> " + status);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("document_id", document_id);
        map.put("status", status);
        documentService.documentUpdate(map);
    }

}
