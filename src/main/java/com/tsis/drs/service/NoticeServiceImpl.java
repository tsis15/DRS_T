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
import java.util.UUID;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    public NoticeDao noticeDao;

    @Override
    public List<Notice> noticeSelectAll() {
        return noticeDao.noticeSelectAll();
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
        if(notice.getNotice_id().equals("") || notice.getNotice_id() == null) { // 게시글 생성할 경우 ""으로 넘어옴
            UUID id = UUID.randomUUID();
            notice.setNotice_id(id.toString());
            return noticeDao.createNotice(notice);
        }
        return noticeDao.createNotice(notice);
    }

    @Override
    public List<Notice> noticeSelectcondition(String input) {
        return noticeDao.noticeSelectcondition(input);
    }


}
