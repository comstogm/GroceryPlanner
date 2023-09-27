package com.enterprise4045.groceryplanner.service;

import com.enterprise4045.groceryplanner.dao.ILoggedItemDAO;
import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.stereotype.Component;

@Component
public class LoggedItemServiceStub implements ILoggedItemService {

    private ILoggedItemDAO loggedItemDAO;


    public LoggedItemServiceStub(){

    }

    public LoggedItemServiceStub(ILoggedItemDAO loggedItemDAO){

        this.loggedItemDAO = loggedItemDAO;
    }

    @Override
    public LoggedItem fetchById(int id) {

        LoggedItem loggedItem = new LoggedItem();
        loggedItem.setDescription("Broccoli");
        loggedItem.setItemId(1);

        return loggedItem;
    }

    @Override
    public LoggedItem save(LoggedItem loggedItem) throws Exception {
        return loggedItemDAO.save(loggedItem);
    }


}
