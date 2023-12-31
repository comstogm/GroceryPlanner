package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("test")
public class LoggedItemDAOStub implements ILoggedItemDAO {

    Map<Integer, LoggedItem> allLoggedItems = new HashMap<>();
    @Override
    public LoggedItem save(LoggedItem loggedItem) {
        Integer loggedItemId = Integer.parseInt(loggedItem.getLoggedItemId());
        allLoggedItems.put(loggedItemId, loggedItem);
        return loggedItem;
    }

    @Override
    public List<LoggedItem> fetchAll() {
        List<LoggedItem> returnLoggedItems = new ArrayList(allLoggedItems.values());
        return returnLoggedItems;
    }

    @Override
    public LoggedItem fetch(int id) {
        return allLoggedItems.get(id);
    }

    @Override
    public void delete(int id) {
        allLoggedItems.remove(id);
    }

    @Override
    public void saveImage(MultipartFile imageFile) throws IOException {

    }
}
