package com.enterprise4045.groceryplanner;

import com.enterprise4045.groceryplanner.dto.Item;
import com.enterprise4045.groceryplanner.dto.LabelValue;
import com.enterprise4045.groceryplanner.dto.LoggedItem;
import com.enterprise4045.groceryplanner.service.ILoggedItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class GroceryPlannerController {

    private final ILoggedItemService loggedItemService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public GroceryPlannerController(ILoggedItemService loggedItemService) {
        this.loggedItemService = loggedItemService;
    }
    /*
    Handle the root (/) endpoint and return a start page.
    Populates the page w/ default LoggedItem
     */
    @RequestMapping("/")
    public String index(Model model) throws ExecutionException, InterruptedException, IOException {
        LoggedItem loggedItem = new LoggedItem();
        Item item = new Item();
        loggedItem.setItemId(420);
        loggedItem.setLoggedItemId("620");
        loggedItem.setDescription("Milk");
        model.addAttribute("loggedItem", loggedItem);
        model.addAttribute("item", item);
        model.addAttribute("itemList", loggedItemService.fetchAll());
        model.addAttribute("retrofitItemsList", loggedItemService.fetchItems());
        return "start";
    }

    @RequestMapping("/saveLoggedItems")
    public String saveLoggedItem(LoggedItem loggedItem, Model model) throws IOException, ExecutionException, InterruptedException {
        try {
            loggedItemService.save(loggedItem);
            model.addAttribute("itemList", loggedItemService.fetchAll());
        } catch (IOException e) {
            log.error("IOException in saveLoggedItem endpoint", e);
            return "error";
        } catch (ExecutionException e) {
            log.error("ExecutionException in saveLoggedItem endpoint", e);
            return "error";
        } catch (InterruptedException e) {
            log.error("InterruptedException in saveLoggedItem endpoint", e);
            return "error";
        }
        catch (Exception e) {
            log.error("Error in saveLoggedItem endpoint", e);
            return "start";
        }
        return index(model);
    }

    @PostMapping(value="/saveLoggedItemsFromRetrofit")
    public String saveLoggedItemsFromRetrofit(@RequestParam String id, Model model) throws IOException, ExecutionException, InterruptedException {

        Integer newId = Integer.parseInt(id);
        List<Item> retrofitItems = loggedItemService.fetchItems();
        Item providedItem = new Item();

        for (Item item : retrofitItems) {
            if (item.getLoggedItemId().equals(newId.toString())) {
                providedItem.setItemId(item.getItemId());
                providedItem.setLoggedItemId(item.getLoggedItemId());
                providedItem.setDescription(item.getDescription());
            }
        }


        //new LoggedItem to convert from the retrofit Item
        LoggedItem newLoggedItem = new LoggedItem();
        newLoggedItem.setItemId(providedItem.getItemId());
        newLoggedItem.setLoggedItemId(providedItem.getLoggedItemId());
        newLoggedItem.setDescription(providedItem.getDescription());

        try {
            loggedItemService.save(newLoggedItem);
            // adds newLoggedItem to model attribute for start.html
            model.addAttribute(newLoggedItem);
            model.addAttribute("itemList", loggedItemService.fetchAll());
        } catch (Exception e) {
            log.error("Error in saveLoggedItem endpoint", e);
            return "error";
        }
        return index(model);
    }


    /*`
    Fetches all logged items
    `*/
    @GetMapping(value="/loggedItems/")
    @ResponseBody
    public List<LoggedItem> fetchAllLoggedItems() throws ExecutionException, InterruptedException {
        return loggedItemService.fetchAll();
    }

    /*
    Fetches logged item by id
     */
    @GetMapping("/loggedItems/{id}/")
    public ResponseEntity<LoggedItem> fetchLoggedItemById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        LoggedItem foundLoggedItem = loggedItemService.fetchById(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (foundLoggedItem==null) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundLoggedItem, headers, HttpStatus.OK);
    }

    /*
    Creates a new item
     */
    @PostMapping(value="/loggedItems", consumes="application/json", produces ="application/json")
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
    @DeleteMapping("/loggedItems/{id}/")
    public ResponseEntity<LoggedItem> deleteLoggedItem(@PathVariable("id") String id) {
        log.debug("Entering delete item endpoint");
        try {
            loggedItemService.delete(Integer.parseInt(id));
            log.info("Item with ID " + id + " was deleted.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to delete the item with ID " + id+ ", message: "+ e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Delete Mapping for HTML page, HTML cannot accept HTTP Delete request
     */
    @RequestMapping(value="/deleteLoggedItems", method = RequestMethod.POST)
    public String deleteHTMLLoggedItem(@RequestParam String id, Model model) throws IOException, ExecutionException, InterruptedException {
        log.debug("Entering delete item endpoint");
        try {
            loggedItemService.delete(Integer.parseInt(id));
            log.info("Item with ID " + id + " was deleted.");
            LoggedItem loggedItem = new LoggedItem();
            loggedItem.setItemId(420);
            loggedItem.setLoggedItemId("620");
            loggedItem.setDescription("Milk");
            model.addAttribute(loggedItem);
            model.addAttribute("itemList", loggedItemService.fetchAll());
        } catch (Exception e) {
            log.error("Unable to delete the item with ID " + id+ ", message: "+ e.getMessage(), e);
            return "error";
        }
        return index(model);
    }

    /*
    Get mapping for start.html search field
    returns JSON list of items
     */
    @GetMapping(value="/items", consumes="application/json", produces ="application/json")
    public ResponseEntity<Object> searchItems(@RequestParam(value="searchTerm", required = false, defaultValue = "None") String searchTerm) {
        try {
            List<Item> items = loggedItemService.fetchItems();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(items, headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error("Error in searchItems endpoint", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/items")
    public String searchItemsForm(@RequestParam(value="searchTerm", required = false,
            defaultValue = "None") String searchTerm, Model model) {
        try {
            List<Item> items = loggedItemService.fetchItems();
            model.addAttribute("items", items);
            return "items";
        } catch (IOException e) {
            log.error("Error in searchItemsForm endpoint", e);
            return "error";
        }
    }

    /**
     * @return superCoolPage placeholder page
     */
    @RequestMapping("/superCoolPage")
    public String superCoolPage() {
        return "superCoolPage";
    }

    @GetMapping("/itemNameAutoComplete")
    @ResponseBody
    public List<LabelValue> itemNameAutoComplete(@RequestParam(value = "term",
            required = false, defaultValue = "") String term){
        List<LabelValue> allItemNames = new ArrayList<LabelValue>();
        try {
            List<Item> items = loggedItemService.fetchItems(); //Should have term in the brackets
            for (Item item: items) {
                LabelValue labelValue = new LabelValue();
                labelValue.setLabel(item.getDescription());
                labelValue.setValue(item.getItemId());
                allItemNames.add(labelValue);
            }
        } catch (IOException e){
            e.printStackTrace();

            return new ArrayList<LabelValue>();
        }
        return allItemNames;
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, Model model) {
        String returnValue = "start";
        try {
            loggedItemService.saveImage(imageFile);
            LoggedItem loggedItem = new LoggedItem();
            model.addAttribute("loggedItem", loggedItem);
            model.addAttribute("itemList", loggedItemService.fetchAll());
            returnValue = "start";
        } catch (IOException e) {
            log.error("Error in uploadImage endpoint", e);
            returnValue = "error";
        } catch (ExecutionException e) {
            log.error("Execution Exception in uploadImage endpoint", e);
            returnValue = "error";
        } catch (InterruptedException e) {
            log.error("Interrupted Exception in uploadImage endpoint", e);
            returnValue = "error";
        }

        return returnValue;

    }
}