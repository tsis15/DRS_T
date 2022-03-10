package com.tsis.drs.service;

import com.tsis.drs.dto.Document;

import java.util.HashMap;
import java.util.List;

public interface DocumentService {

    public List<Document> selectAll();
    public List<Document> showUserD(String id);
    public List<Document> selectAllProcedure(HashMap<String, String> map);
    public void insertDocument(Document document);
    public void documentApproval(String document_id);
    public void documentUpdate(HashMap<String, String> map);
}
