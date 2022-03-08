package com.tsis.drs.dao;

import com.tsis.drs.dto.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface NoticeDao {
    public int deleteNotice(String notice_id);
    public int modifyNotice(Notice notice);
    public int createNotice(Notice notice);
    public List<Notice> noticeSelectAll(HashMap<String, String> map);
    public List<Notice> noticeSelectcondition(HashMap<String, String> map);
}
