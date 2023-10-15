package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class LoggedItemDAOStub implements ILoggedItemDAO{

    //Creates a new hash map when instantiating
    HashMap<Integer, LoggedItem> allLoggedItem = new HashMap<>();

    //Gets the key of the hashMap and adds the items to the database
    @Override
    public LoggedItem save(LoggedItem loggedItem) {
        Integer loggedItemID = Integer.parseInt(loggedItem.getLoggedItemId());
        allLoggedItem.put(loggedItemID, loggedItem);
        return loggedItem;
    }

    //Gets all the loggedItems and returns them into a list
    @Override
    public List<LoggedItem> fetchAll() {
        List<LoggedItem> returnLoggedItem = new ArrayList<>(allLoggedItem.values());
        return returnLoggedItem;
    }

    //Returns the loggedItem, given the ID
    @Override
    public LoggedItem fetch(int id) {
        return allLoggedItem.get(id);
    }

    //Deletes the loggedItem from the database, given the ID
    @Override
    public void delete(int id) {
        allLoggedItem.remove(id);
    }
}
