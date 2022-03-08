package com.tsis.drs.service;

import com.tsis.drs.dto.Delinquent;
import com.tsis.drs.dto.Document;
import com.tsis.drs.dto.Reqitemsresponse;
import com.tsis.drs.dto.Requestitems;

import java.util.HashMap;
import java.util.List;

public interface RequestItemsService {
    public List<Requestitems> selectAll();
    public Requestitems selectOne(String id);
    public void insetRequestitems(Requestitems requestitems);
    public List<Reqitemsresponse> selectReq();
    List<Delinquent> delinqunet();
}
