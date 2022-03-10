package com.tsis.drs.service;

import com.tsis.drs.dto.User;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    public List<User> selectAll();
    User selectOne(String id);
    public User setUserDao(JSONObject data);
    public void login(User user);
    public void update(HashMap<String, Object> map);
    public List<User> selectAllReviewer(String id);
    public List<User> Selectcondition(String input);
}
