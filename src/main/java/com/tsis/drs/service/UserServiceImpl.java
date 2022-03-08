package com.tsis.drs.service;

import com.tsis.drs.dao.UserDao;
import com.tsis.drs.dto.Deptdivsion;
import com.tsis.drs.dto.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    public UserDao userDao;

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public User selectOne(String id) {
        return userDao.selectOne(id);
    }
    /**
     * Loggin Controller 에서 받은 json 데이터를 User에 정보 set
     */
    public User setUserDao(JSONObject data){
        User user = new User();
        user.setUser_id(data.getString("ID"));
        user.setName(data.getString("NAME"));
        user.setEmail(data.getString("EMAIL"));
        user.setPhone(data.getString("PHONE"));
        user.setDept_name(data.getString("DIVISION"));

        int n = -1;
        String x= data.getString("LEVEL");
        try{
            n = Deptdivsion.valueOf(x).getValue();
        }catch(IllegalArgumentException e){

        }finally {
            user.setPosition(n);
        }
        return user;
    }

    @Override
    public void login(User user) {
        userDao.login(user);
    }

    @Override
    public void update(HashMap<String, Object> map) {
        userDao.update(map);
    }

    @Override
    public List<User> selectAllReviewer(String id) {
        return userDao.selectAllReviewer(id);
    }


}
