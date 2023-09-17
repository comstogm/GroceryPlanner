package com.enterprise4045.groceryplanner.service;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.stereotype.Component;

@Component
public class LoggedItemServiceStub implements ILoggedItemService {
    @Override
    public LoggedItem fetchById(int id) {

        LoggedItem loggedItem = new LoggedItem();
        loggedItem.setDescription("Broccoli");
        loggedItem.setItemId(1);

        return loggedItem;
    }
}
