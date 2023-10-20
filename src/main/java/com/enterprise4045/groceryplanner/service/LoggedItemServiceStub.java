package com.enterprise4045.groceryplanner.service;

import com.enterprise4045.groceryplanner.dao.ILoggedItemDAO;
import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggedItemServiceStub implements ILoggedItemService {
    @Autowired
    private ILoggedItemDAO loggedItemDAO;


    public LoggedItemServiceStub(ILoggedItemDAO loggedItemDAO){

        this.loggedItemDAO = loggedItemDAO;
    }

    @Override
    public LoggedItem fetchById(int id) {

       LoggedItem foundloggedItem = loggedItemDAO.fetch(id);

        return foundloggedItem;
    }

    @Override
    public void delete(int id) throws Exception{
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


}
