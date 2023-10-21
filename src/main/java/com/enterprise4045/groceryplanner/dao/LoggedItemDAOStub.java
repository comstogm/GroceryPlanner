package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class LoggedItemDAOStub implements ILoggedItemDAO{

    /*
    * Create a new HashMap
    * Stores an Integer and a LoggedItem
    */
    HashMap<Integer, LoggedItem> allLoggedItem = new HashMap<>();

    /*
    * Saves LoggedItem to the HashMap
    * Pars the Integer and set the Integer to be a loggedItemID
    * Add loggedItemID and loggedItem to the HashMap
    */

    @Override
    public LoggedItem save(LoggedItem loggedItem) {
        Integer loggedItemID = Integer.parseInt(loggedItem.getLoggedItemId());
        allLoggedItem.put(loggedItemID, loggedItem);
        return loggedItem;
    }

    /*
    * Returns a list of all items in the HashMap
    */

    @Override
    public List<LoggedItem> fetchAll() {
        return new ArrayList<>(allLoggedItem.values());
    }


    /*
     * Returns a specific item from the HashMap
     */

    @Override
    public LoggedItem fetch(int id) {
        return allLoggedItem.get(id);
    }


    /*
     * Deletes a specific item from the HashMap
     */

    @Override
    public void delete(int id) {
        allLoggedItem.remove(id);
    }
}
