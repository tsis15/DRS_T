package com.tsis.drs.dao;

import com.tsis.drs.dto.Document;
import com.tsis.drs.dto.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DocumentDao {
    public List<Document> selectAll();
    public Document selectOne(String id);
    public List<Document> showUserD(String id);
    public List<Document> selectAllProcedure(HashMap<String,String> map);
    public void insertDocument(Document document);
    public void documentApproval(String document_id);
    public void documentUpdate(HashMap<String, String> map);
    public List<String> documentItemIds(String document_id);
}
