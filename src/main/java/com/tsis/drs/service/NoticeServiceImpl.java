package com.tsis.drs.service;

import com.tsis.drs.dao.NoticeDao;
import com.tsis.drs.dto.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    public NoticeDao noticeDao;


    @Override
    public List<Notice> noticeSelectAll(HashMap<String, String> map) {
        return noticeDao.noticeSelectAll(map);
    }

    @Override
    public int deleteNotice(String notice_id) {
        return noticeDao.deleteNotice(notice_id);
    }

    @Override
    public int modifyNotice(Notice notice) {
        return noticeDao.modifyNotice(notice);
    }

    @Override
    public int createNotice(Notice notice) {
        return noticeDao.createNotice(notice);
    }

    @Override
    public List<Notice> noticeSelectcondition(HashMap<String, String> map) {
        return noticeDao.noticeSelectcondition(map);
    }
}
