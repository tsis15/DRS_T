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
}
