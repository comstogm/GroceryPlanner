package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.Item;

import java.io.IOException;
import java.util.List;

public interface IItemDAO {
    List<Item> getItems() throws IOException;
}