package com.tsis.drs.service;

import com.tsis.drs.dao.DocumentDao;
import com.tsis.drs.dto.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    public DocumentDao documentDao;

    @Override
    public List<Document> selectAll() {
        return documentDao.selectAll();
    }

    @Override
    public List<Document> showUserD(String id) {
        return documentDao.showUserD(id);
    }

    @Override
    public List<Document> selectAllProcedure(HashMap<String, String> map) {
        return documentDao.selectAllProcedure(map);
    }

    @Override
    public void insertDocument(Document document) {
        documentDao.insertDocument(document);
    }

    @Override
    public void documentApproval(String document_id) {
        documentDao.documentApproval(document_id);
    }

    @Override
    public void documentUpdate(HashMap<String, String> map) {
        documentDao.documentUpdate(map);
    }


}
