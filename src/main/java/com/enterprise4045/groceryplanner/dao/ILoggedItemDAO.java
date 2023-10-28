package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.LoggedItem;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ILoggedItemDAO {
    LoggedItem save(LoggedItem loggedItem);

    List<LoggedItem> fetchAll() throws ExecutionException, InterruptedException;

    LoggedItem fetch(int id) throws ExecutionException, InterruptedException;

    void delete(int id);
}
