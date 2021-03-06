package com.tsis.drs.service;

import com.tsis.drs.dto.Notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeService {
    public List<Notice> noticeSelectAll();
    public int deleteNotice(String notice_id);
    public int modifyNotice(Notice notice);
    public int createNotice(Notice notice);
    public List<Notice> noticeSelectcondition(String input);
}
