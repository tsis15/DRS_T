package com.tsis.drs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.tsis.drs.dao.ItemDao;
import com.tsis.drs.dto.Item;
import com.tsis.drs.dto.ItemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemDao itemDao;

    @Override
    public List<Item> selectAll() {
        return itemDao.selectAll();
    }

    @Override
    public Item selectOne(String id) {
        return itemDao.selectOne(id);
    }

    @Override
    public void insertItem(Item item) {
        UUID id = UUID.randomUUID();
        if(item.getItem_id() == null) {
            item.setItem_id(id.toString());
        }
        else if(item.getItem_id().equals("")){
            item.setItem_id(id.toString());
        }
        itemDao.insertItem(item);
    }

    @Override
    public void updateItem(HashMap<String, String> map) {
        itemDao.updateItem(map);
    }

    @Override
    public void callAlwaysUpdate() {
        itemDao.callAlwaysUpdate();
    }

    @Override
    public List<ItemLog> selectAllLog(String item_id) {
        return itemDao.selectAllLog(item_id);
    }

    @Override
    public List<Item> selectExtendItems(String id) {
        return itemDao.selectExtendItems(id);
    }
}
