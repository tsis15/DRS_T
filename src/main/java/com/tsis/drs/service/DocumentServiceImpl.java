package com.tsis.drs.service;

import com.tsis.drs.dao.DocumentDao;
import com.tsis.drs.dto.Document;
import com.tsis.drs.dto.DocumentResponse;
import com.tsis.drs.dto.Item;
import com.tsis.drs.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    public DocumentDao documentDao;
    @Autowired
    UserService userService;
    @Autowired
    DocumentService documentService;

    @Override
    public List<Document> selectAll() {
        return documentDao.selectAll();
    }

    @Override
    public Document selectOne(String id) {
        return documentDao.selectOne(id);
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

    @Override
    public List<String> documentItemIds(String document_id) {
        return documentDao.documentItemIds(document_id);
    }


}
