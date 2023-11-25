package com.enterprise4045.groceryplanner.service;

import com.enterprise4045.groceryplanner.dto.LoggedItem;

import java.util.List;

public interface ILoggedItemService {
    /*
    Fetch a loggedItem with a given ID.
    @param id a unique identifier for a loggedItem.
    @return the matching loggedItem, or null if dne/ no matches.
     */
    LoggedItem fetchById(int id) throws Exception;

    void delete(int id) throws Exception;

    LoggedItem save(LoggedItem loggedItem) throws Exception;

    List<LoggedItem> fetchAll() throws Exception;
}
