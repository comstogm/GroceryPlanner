package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.LoggedItem;

import java.util.List;

public interface ILoggedItemDAO {
    LoggedItem save(LoggedItem loggedItem);

    List<LoggedItem> fetchAll();

    LoggedItem fetch(int id);

    void delete(int id);
}
