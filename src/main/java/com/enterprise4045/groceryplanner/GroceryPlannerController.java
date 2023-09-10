package com.enterprise4045.groceryplanner;

import dto.LoggedItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GroceryPlannerController {
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
    public ResponseEntity fetchallLoggedItems() {
        return new ResponseEntity(HttpStatus.OK);
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
    public LoggedItem createLoggedItem(@RequestBody LoggedItem loggedItem) {
        return loggedItem;
    }

    /*
    Deletes and item based on id
     */
    @DeleteMapping("/loggeditem/{id}/")
    public ResponseEntity deleteLoggedItem(@PathVariable("id") String id) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
