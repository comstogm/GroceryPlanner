package com.enterprise4045.groceryplanner;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import com.enterprise4045.groceryplanner.service.ILoggedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroceryPlannerController {

    private final ILoggedItemService loggedItemService;
    @Autowired
    public GroceryPlannerController(ILoggedItemService loggedItemService) {
        this.loggedItemService = loggedItemService;
    }

    /*
        Handle the root (/) endpoint and return a start page.
         */
    @RequestMapping("/")
    public ResponseEntity<String> index() {
        String htmlResponse = "<html><body>"
                + "<h1>Welcome to the Grocery Planner API!</h1>"
                + "<p>Use the endpoints to interact with the API.</p>"
                + "</body></html>";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(htmlResponse, headers, HttpStatus.OK);
    }


    /*`
    Fetches all logged items
     `*/
    @GetMapping("/loggedItems/")
    @ResponseBody
    public List<LoggedItem> fetchAllLoggedItems() {
        return loggedItemService.fetchAll();
    }

    /*
    Fetches logged item by id
     */
    @GetMapping("/logged-items/{id}/")
    public ResponseEntity<LoggedItem> fetchLoggedItemById(@PathVariable("id") String id) {
        LoggedItem foundLoggedItem = loggedItemService.fetchById(Integer.parseInt(id));

        if (foundLoggedItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(foundLoggedItem, HttpStatus.OK);
    }

    /*
    Creates a new item
     */
    @PostMapping(value="/loggedItem", consumes="application/json", produces ="application/json")
    public ResponseEntity<Object> createLoggedItem(@RequestBody LoggedItem loggedItem) {
        // Validation
        if (loggedItem.getDescription() == null || loggedItem.getDescription().isEmpty()) {
            return new ResponseEntity<>("Description cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        // Process the item
        try {
            LoggedItem newLoggedItem = loggedItemService.save(loggedItem);
            return new ResponseEntity<>(newLoggedItem, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
    Deletes and item based on id
     */

    // Custom Exception for Item not found
    public class ItemNotFoundException extends RuntimeException {
        public ItemNotFoundException(String itemId) {
            super("No item found with ID: " + itemId);
        }
    }

    @DeleteMapping("/loggedItems/{id}/")
    public ResponseEntity deleteLoggedItem(@PathVariable("id") String id) {
        try {
            loggedItemService.delete(Integer.parseInt(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
