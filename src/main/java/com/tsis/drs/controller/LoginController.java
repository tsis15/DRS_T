package com.tsis.drs.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.tsis.drs.HttpConnection;
import com.tsis.drs.dto.*;
import com.tsis.drs.service.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("*")
@RestController
@Slf4j
public class LoginController
{
    @Autowired
    UserServiceImpl userService;


    @PostMapping(value = "loginOk.do" , produces = "application/text; charset=utf8")
    public @ResponseBody String loginOk(Model model,
                          @RequestBody String data,
                          HttpServletRequest request,
                          HttpServletResponse response)
    {

        System.out.println(">>>>>> loginOk.do");
        Gson gson = new Gson();

        JSONObject req_jsonObj = new JSONObject(data);



        String input = "CPCODE="+req_jsonObj.get("CPCODE")+"&ID="+req_jsonObj.get("ID")+"&PWD="+req_jsonObj.get("PWD");

        String login_result = HttpConnection.PostData("https://www.t-ammo.com/IGWS/loginLegacy_.json",input);
        JSONObject jsonObj = new JSONObject(login_result);
        // 로그인 결과가 없을시 실패 json을 반환
        if(!jsonObj.getString("RESULT").equals("SUCCESS"))
            return jsonObj.toString();

        //Json으로 받은 것을 user DTO로 변환
        User user = userService.setUserDao(jsonObj);

        // DB에 CREATE or UPDATE
        userService.login(user);

        log.info("User 데이터"+ user);

        //이거머임?? 저장되어있던 정보를 불러오는데 필요?? WHY?
        User user1 = userService.selectOne(user.getUser_id());
        jsonObj.put("ROLE",user1.getRole());
        log.info("User 데이터>>>>>>>"+ jsonObj);

        /**
         * 세션 생성
         */
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.LOGIN_MEMBER,user1);
        User loginMember = (User) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        log.info("세션이 저장되었어요 >>>"+loginMember);
        //성공했다는 정보를 반환
        return jsonObj.toString();
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        //세션 종료를 해주었는데 안될시 강제로 날림
        if(session != null){
            session.invalidate();
        }

        return "세션종료 완료";
    }
}
