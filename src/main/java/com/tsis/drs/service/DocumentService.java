package com.tsis.drs.service;

import com.tsis.drs.dto.Document;
import com.tsis.drs.dto.Item;

import java.util.HashMap;
import java.util.List;

public interface DocumentService {
    public Document selectOne(String id);
    public List<Document> selectAll();
    public List<Document> showUserD(String id);
    public List<Document> selectAllProcedure(HashMap<String, String> map);
    public void insertDocument(Document document);
    public void documentApproval(String document_id);
    public void documentUpdate(HashMap<String, String> map);
    public List<String> documentItemIds(String document_id);
}
