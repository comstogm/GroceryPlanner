package com.enterprise4045.groceryplanner;

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
    @GetMapping("/loggeditem/")
    @ResponseBody
    public List<LoggedItem> fetchAllLoggedItems() {
        return loggedItemService.fetchAll();
    }

    /*
    Fetches logged item by id
     */
    @GetMapping("/loggeditem/{id}/")
    public ResponseEntity fetchLoggedItemById(@PathVariable("id") String id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
    Creates a new item
     */
    @PostMapping(value="/loggeditem", consumes="appplication/json", produces ="application/json")
    public LoggedItem createLoggedItem(@RequestBody LoggedItem loggedItem) throws Exception {
        LoggedItem newloggedItem = null;

        try{
               newloggedItem = loggedItemService.save(loggedItem);
           }
        catch (Exception e){
               //TO-Do add logging
           }

           return newloggedItem;
    }

    /*
    Deletes and item based on id
     */
    @DeleteMapping("/loggeditem/{id}/")
    public ResponseEntity deleteLoggedItem(@PathVariable("id") String id) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
