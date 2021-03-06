package com.tsis.drs.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsis.drs.dto.Item;
import com.tsis.drs.dto.ItemLog;
import com.tsis.drs.service.ItemService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @Autowired
    ItemService itemService;

    @ApiOperation(value = "모든 단말기 정보를 반환한다.")
    @GetMapping("paging/{num}")
    public PageInfo<Item> selectAll(@PathVariable int num) throws Exception {
        int perPage = 10;
        PageHelper.startPage(num,perPage);
        List<Item> items = itemService.selectAll();
        PageInfo<Item> itemPageInfo = new PageInfo<Item>(items);
        return itemPageInfo;
    }

    @ApiOperation(value = "해당 단말기의 모든 로그 정보를 반환한다.")
    @GetMapping("logpaging/{num}/{item_id}")
    public PageInfo<ItemLog> selectAllLog(@PathVariable int num, @PathVariable String item_id) throws Exception {
        int perPage = 10;
        PageHelper.startPage(num,perPage);
        List<ItemLog> itemLogs = itemService.selectAllLog(item_id);
        PageInfo<ItemLog> itemLogsPageInfo = new PageInfo<ItemLog>(itemLogs);
        return itemLogsPageInfo;
    }

    @ApiOperation(value = "하나의 단말기 정보를 반환한다.", response = Item.class)
    @GetMapping("/{id}")
    public ResponseEntity<Item> selectOne(@PathVariable String id) throws Exception {
        log.info("item Controller: "+ id);
        return new ResponseEntity<Item>(itemService.selectOne(id), HttpStatus.OK);
    }

    @ApiOperation(value = "하나의 단말기 정보를 저장한다 있을 시 update", response = Item.class)
    @PostMapping
    public void insertItem(@RequestBody Item item){
        log.info(item.toString());
        itemService.insertItem(item);
    }

    /**
     * 반납화면 설정 ( 반납 시 예약 문서가 있으면 대여불가, 예약가능 예약문서가 없으면 대여가능 )
     */
    @ApiOperation(value = "반납화면 설정 ( 반납 시 예약 문서가 있으면 대여불가, 예약가능 예약문서가 없으면 대여가능 )")
    @PutMapping("up/{user_id}/{requestitems_id}/{item_id}")
    public void updateItem(@PathVariable String user_id, @PathVariable String requestitems_id, @PathVariable String item_id){
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("requestitems_id", requestitems_id);
        map.put("item_id", item_id);
        itemService.updateItem(map);
    }

    @ApiOperation(value = "연장하기 버튼 눌렀을 때 연장이 가능한 ITEM 리스트 조회 (대여가능일경우만)", response = Item.class)
    @GetMapping("extend/{document_id}")
    public ResponseEntity<List<Item>> selectExtendItems(@PathVariable String document_id) throws Exception {
        return new ResponseEntity<List<Item>>(itemService.selectExtendItems(document_id), HttpStatus.OK);
    }

}
