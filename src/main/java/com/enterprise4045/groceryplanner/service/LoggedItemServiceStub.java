package com.enterprise4045.groceryplanner.service;

import com.enterprise4045.groceryplanner.dao.ILoggedItemDAO;
import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggedItemServiceStub implements ILoggedItemService {

    private final ILoggedItemDAO loggedItemDAO;

    @Autowired
    public LoggedItemServiceStub(ILoggedItemDAO loggedItemDAO){
        this.loggedItemDAO = loggedItemDAO;
    }

    @Override
    public LoggedItem fetchById(int id) throws Exception{
        try {
            return loggedItemDAO.fetch(id);
        } catch (Exception e) {
            throw new Exception("Cannot find loggedItem with " + id + ".");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            loggedItemDAO.delete(id);
        } catch (Exception e){
            throw new Exception("Cannot find loggedItem with " + id + ".");
        }
    }

    @Override
    public LoggedItem save(LoggedItem loggedItem) throws Exception {
        try {
            return loggedItemDAO.save(loggedItem);
        } catch (Exception e){
            throw new Exception("Cannot find loggedItem: " + loggedItem.getDescription() + ".");
        }
    }

    @Override
    public List<LoggedItem> fetchAll() {
        return loggedItemDAO.fetchAll();
    }
}
