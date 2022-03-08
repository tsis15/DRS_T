package com.tsis.drs.dao;

import com.tsis.drs.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    public List<User> selectAll();
    public User selectOne(String id);
    public void login(User user);
    public void update(HashMap<String, Object> map);
    public List<User> selectAllReviewer(String id);
}
