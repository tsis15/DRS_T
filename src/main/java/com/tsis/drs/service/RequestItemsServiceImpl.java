package com.tsis.drs.service;

import com.tsis.drs.dao.DocumentDao;
import com.tsis.drs.dao.RequestItemsDao;
import com.tsis.drs.dto.Delinquent;
import com.tsis.drs.dto.Document;
import com.tsis.drs.dto.Reqitemsresponse;
import com.tsis.drs.dto.Requestitems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RequestItemsServiceImpl implements RequestItemsService {

    @Autowired
    public RequestItemsDao requestItemsDao;

    @Override
    public List<Requestitems> selectAll() {
        return requestItemsDao.selectAll();
    }

    @Override
    public Requestitems selectOne(String id) {
        return requestItemsDao.selectOne(id);
    }

    @Override
    public void insetRequestitems(Requestitems requestitems) {
        requestItemsDao.insetRequestitems(requestitems);
    }

    @Override
    public List<Reqitemsresponse> selectReq() {
        return requestItemsDao.selectReq();
    }

    @Override
    public List<Delinquent> delinqunet() {
        return requestItemsDao.delinqunet();
    }

}
