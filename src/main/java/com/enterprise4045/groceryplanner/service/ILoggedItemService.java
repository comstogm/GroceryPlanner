package com.enterprise4045.groceryplanner.service;

import com.enterprise4045.groceryplanner.dto.Item;
import com.enterprise4045.groceryplanner.dto.LoggedItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ILoggedItemService {
    /*
    Fetch a loggedItem with a given ID.
    @param id a unique identifier for a loggedItem.
    @return the matching loggedItem, or null if dne/ no matches.
     */
    LoggedItem fetchById(int id) throws ExecutionException, InterruptedException;

    void delete(int id) throws Exception;

    LoggedItem save(LoggedItem loggedItem) throws Exception;

    List<LoggedItem> fetchAll() throws ExecutionException, InterruptedException;
    List<Item> fetchItems() throws IOException;

    void saveImage(MultipartFile imageFile) throws IOException;
}