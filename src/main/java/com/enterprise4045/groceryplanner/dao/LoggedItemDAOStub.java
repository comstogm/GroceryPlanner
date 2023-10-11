package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class LoggedItemDAOStub implements ILoggedItemDAO{
    HashMap<Integer, LoggedItem> allLoggedItem = new HashMap<>();
    @Override
    public LoggedItem save(LoggedItem loggedItem) {
        Integer loggedItemID = Integer.parseInt(loggedItem.getLoggedItemId());
        allLoggedItem.put(loggedItemID, loggedItem);
        return loggedItem;
    }

    @Override
    public List<LoggedItem> fetchAll() {
        List<LoggedItem> returnLoggedItem = new ArrayList<>(allLoggedItem.values());
        return returnLoggedItem;
    }

    @Override
    public LoggedItem fetch(int id) {
        return allLoggedItem.get(id);
    }

    @Override
    public void delete(int id) {
        allLoggedItem.remove(id);
    }
}
