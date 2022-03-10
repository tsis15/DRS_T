package com.tsis.drs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import java.util.*;

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

    @Autowired
    ItemService itemService;

    @ApiOperation(value = "num = 페이징 번호, id= 사번을 통한 Document 결과 조회 (drafted_user_id : 이름)", response = List.class)
    @GetMapping("/{num}/{user_id}")
    public PageInfo<DocumentResponse> selectDocument(@PathVariable int num, @PathVariable String user_id) throws Exception {
        User user = userService.selectOne(user_id);
        int perPage = 10;
        List<DocumentResponse> drlist = new ArrayList<>();
        List<Document> list;
        PageHelper.startPage(num, perPage);
        if(user.getRole() == 2)
            list = documentService.selectAll();
        else
            list = documentService.showUserD(user.getUser_id());

        for (Document doc : list) {
            log.info(doc.toString());
            String drname = userService.selectOne(doc.getDrafted_user_id()).getName();
            String rvname = userService.selectOne(doc.getReviewed_user_id()).getName();
            String apname=null;
            if (doc.getApproval_user_id()!=null) {
                apname = userService.selectOne(doc.getApproval_user_id()).getName();
            }
            String document_id = doc.getDocument_id();
            List<String> itemIds = documentService.documentItemIds(document_id);
            List<String> itemNames = new ArrayList<>();
            for(String itemId : itemIds) {
                itemNames.add(itemService.selectOne(itemId).getName());
            }
            log.info(doc.toString());
            drlist.add(new DocumentResponse(doc, drname, rvname,apname,itemNames));
        }
        PageInfo<DocumentResponse> of = new PageInfo<DocumentResponse>(drlist);
        log.info(String.valueOf(of.getList()) + " >>>>" + num);
        return of;
    }

    /**
     * 기안문 최초 생성
     * 예약인지 대여인지 파라메터 필요!!
     */

    @ApiOperation(value = "결재문서 기안문 최초저장", response = Document.class)
    @PostMapping("save/{d_id}/{r_id}/{serialnum}/{document_status}")
    public void insertItem(@PathVariable String d_id, @PathVariable String r_id,@PathVariable String serialnum ,@PathVariable String document_status,@RequestBody List<String> ids) {
        Date d = new Date();
        Document document = new Document();
        UUID id = UUID.randomUUID();
        document.setDocument_id(id.toString());
        document.setDrafted_user_id(d_id);
        document.setReviewed_user_id(r_id);
        document.setTitle("단말기 대여 신청서");
        document.setRequestdate(d);
        document.setDocument_status(document_status);
        document.setSerialnum(serialnum);
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
    @PostMapping("/{document_id}")
    public DocumentResponse selectOne(@PathVariable String document_id){
        Document document = documentService.selectOne(document_id);

        String drname = userService.selectOne(document.getDrafted_user_id()).getName();
        String rvname = userService.selectOne(document.getReviewed_user_id()).getName();
        String apname=null;
        if (document.getApproval_user_id()!=null) {
            apname = userService.selectOne(document.getApproval_user_id()).getName();
        }

        return new DocumentResponse(document,drname,rvname,apname);
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
