package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LoggedItemDAOStub implements ILoggedItemDAO {

    List<LoggedItem> allLoggedItems = new ArrayList<LoggedItem>();
    @Override
    public LoggedItem save(LoggedItem loggedItem) throws Exception {
        allLoggedItems.add(loggedItem);
        return loggedItem;
    }

    @Override
    public List<LoggedItem> fetchAll() {
        return allLoggedItems;
    }
}
