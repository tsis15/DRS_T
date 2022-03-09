package com.tsis.drs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.tsis.drs.dto.Item;
import com.tsis.drs.dto.ItemLog;

import java.util.HashMap;
import java.util.List;

public interface ItemService {
    public List<Item> selectAll();
    public Item selectOne(String id);
    public void insertItem(Item item);
    public void updateItem(HashMap<String, String> map);
    public void callAlwaysUpdate();
    public List<ItemLog> selectAllLog(String item_id);
}
