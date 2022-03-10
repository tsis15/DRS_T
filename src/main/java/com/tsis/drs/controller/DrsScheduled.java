package com.tsis.drs.controller;

import com.tsis.drs.dto.Delinquent;
import com.tsis.drs.service.EmailUtil;
import com.tsis.drs.service.ItemService;
import com.tsis.drs.service.RequestItemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/sendEmail")
@Slf4j
public class DrsScheduled {
    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private RequestItemsService requestItemsService;

    @Autowired
    private ItemService itemService;

//   @Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "0 0 14 * * *")
    @GetMapping
    public void changeDate() {
        log.info("변하기전 데이터 >>>"+requestItemsService.delinqunet());
        itemService.callAlwaysUpdate();
        log.info("변하고난 데이터 >>>"+requestItemsService.delinqunet());

        List<Delinquent> list = requestItemsService.delinqunet();

        log.info("연체자 데이터>>>"+list);
        for (Delinquent child: list) {
            String data=child.getName()+"님 "+child.getOverdue()+"일 연체되었습니다. 빠른시일내에 반납하여주세요.";
            emailUtil.sendEmail(child.getEmail(), "TSIS 단말기 대여 연체되었습니다.", data);
            log.info("잘돌아가요>>>"+data);
        }
        //emailUtil.sendEmail("dlwnsdud3737@naver.com", "스프링을 이용한 메일 전송", "되는지 테스트 하는 거예요");
    }
}
