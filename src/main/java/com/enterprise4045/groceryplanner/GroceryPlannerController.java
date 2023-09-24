package com.enterprise4045.groceryplanner;

import com.enterprise4045.groceryplanner.dao.ILoggedItemDAO;
import com.enterprise4045.groceryplanner.dto.LoggedItem;
import com.enterprise4045.groceryplanner.service.ILoggedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroceryPlannerController {

    @Autowired
    ILoggedItemService loggedItemService;
    /*
    Handle the root (/) endpoint and return a start page.
     */
    @RequestMapping("/")
    public String index() {
        return "start";
    }

    /*
    Fetches all logged items
     */
    @GetMapping("/loggedItem/")
    @ResponseBody
    public List<LoggedItem> fetchallLoggedItems() {
        return loggedItemService.fetchAll();
    }

    /*
    Fetches logged item by id
     */
    @GetMapping("/loggedItem/{id}/")
    public ResponseEntity fetchLoggedItemById(@PathVariable("id") String id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
    Creates a new item
     */
    @PostMapping(value="/loggedItem", consumes="appplication/json", produces ="application/json")
    public LoggedItem createLoggedItem(@RequestBody LoggedItem loggedItem) throws Exception {
        LoggedItem newLoggedItem = null;
        try {
            loggedItemService.save(loggedItem);
        } catch (Exception e) {
            //TODO add logging
        }
        return newLoggedItem;
    }

    /*
    Deletes and item based on id
     */
    @DeleteMapping("/loggedItem/{id}/")
    public ResponseEntity deleteLoggedItem(@PathVariable("id") String id) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
