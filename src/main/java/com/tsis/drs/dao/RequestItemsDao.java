package com.tsis.drs.dao;

import com.tsis.drs.dto.Delinquent;
import com.tsis.drs.dto.Reqitemsresponse;
import com.tsis.drs.dto.Requestitems;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface RequestItemsDao {
    public List<Requestitems> selectAll();
    public Requestitems selectOne(String id);
    void insetRequestitems(Requestitems requestitems);
    public List<Reqitemsresponse> selectReq();
    List<Delinquent> delinqunet();
}
