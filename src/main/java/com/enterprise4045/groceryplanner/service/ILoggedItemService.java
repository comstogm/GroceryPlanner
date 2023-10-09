package com.enterprise4045.groceryplanner.service;

import com.enterprise4045.groceryplanner.dto.Item;
import com.enterprise4045.groceryplanner.dto.LoggedItem;

import java.io.IOException;
import java.util.List;

public interface ILoggedItemService {
    /*
    Fetch a loggedItem with a given ID.
    @param id a unique identifier for a loggedItem.
    @return the matching loggedItem, or null if dne/ no matches.
     */
    LoggedItem fetchById(int id);

    void delete(int id) throws Exception;

    LoggedItem save(LoggedItem loggedItem) throws Exception;

    List<LoggedItem> fetchAll();
    List<Item> fetchItems() throws IOException;
}
