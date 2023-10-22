package com.enterprise4045.groceryplanner.service;

import com.enterprise4045.groceryplanner.dao.IItemDAO;
import com.enterprise4045.groceryplanner.dao.ILoggedItemDAO;
import com.enterprise4045.groceryplanner.dto.Item;
import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LoggedItemServiceStub implements ILoggedItemService {


    private final ILoggedItemDAO loggedItemDAO;
    private IItemDAO itemDAO;

    @Autowired
    public LoggedItemServiceStub(ILoggedItemDAO loggedItemDAO, IItemDAO itemDAO) {
        this.loggedItemDAO = loggedItemDAO;
        this.itemDAO = itemDAO;
    }

    public LoggedItemServiceStub(ILoggedItemDAO loggedItemDAO) {

        this.loggedItemDAO = loggedItemDAO;
    }
    @Override
    public LoggedItem fetchById(int id) {
        LoggedItem foundLoggedItem = loggedItemDAO.fetch(id);
        return foundLoggedItem;
    }

    @Override
    public void delete(int id) throws Exception {
        loggedItemDAO.delete(id);
    }

    @Override
    public LoggedItem save(LoggedItem loggedItem) throws Exception {
        return loggedItemDAO.save(loggedItem);
    }

    @Override
    public List<LoggedItem> fetchAll() {
        return loggedItemDAO.fetchAll();
    }

    @Override
    public List<Item> fetchItems() throws IOException {
        return itemDAO.getItems();
    }
}