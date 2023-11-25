package com.enterprise4045.groceryplanner.service;

import com.enterprise4045.groceryplanner.dao.ILoggedItemDAO;
import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class LoggedItemServiceStub implements ILoggedItemService {

    private final ILoggedItemDAO loggedItemDAO;
    private static final Logger logger = LoggerFactory.getLogger(LoggedItemServiceStub.class);

    @Autowired
    public LoggedItemServiceStub(ILoggedItemDAO loggedItemDAO){
        this.loggedItemDAO = loggedItemDAO;
    }

    @Override
    public LoggedItem fetchById(int id) throws Exception{
        logger.info("Fetching LoggedItem by ID: {}", id);
        try {
            return loggedItemDAO.fetch(id);
        } catch (Exception e) {
            logger.error("Error fetching LoggedItem by ID: {}", id, e);
            throw e;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        logger.info("Deleting LoggedItem by ID: {}", id);
        try {
            loggedItemDAO.delete(id);
        } catch (Exception e){
            logger.error("Error deleting LoggedItem with ID: {}", id, e);
            throw e;
        }
    }

    @Override
    public LoggedItem save(LoggedItem loggedItem) throws Exception {
        logger.info("Saving LoggedItem: {}", loggedItem);
        try {
            return loggedItemDAO.save(loggedItem);
        } catch (Exception e){
            logger.error("Error saving LoggedItem: {}", loggedItem, e);
            throw e;
        }
    }

    @Override
    public List<LoggedItem> fetchAll() throws Exception {
        logger.info("Getting all a list LoggedItems");
        try {
            return loggedItemDAO.fetchAll();
        } catch (Exception e){
            logger.error("Error fetching LoggedItems list");
            throw e;
        }
    }
}
