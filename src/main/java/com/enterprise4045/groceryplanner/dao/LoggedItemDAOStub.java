package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class LoggedItemDAOStub implements ILoggedItemDAO{

    /*
     * Initialize dbFirestore connection
     */
    private final Firestore dbFirestore = FirestoreClient.getFirestore();

    /*
    * Create a new HashMap
    * Stores an Integer and a LoggedItem
    */
    HashMap<Integer, LoggedItem> allLoggedItem = new HashMap<>();

    /*
    * Saves LoggedItem to Firebase Firestore
    * Returns LoggedItem
    */
    @Override
    public LoggedItem save(LoggedItem loggedItem) {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("test").document(loggedItem.getDescription()).set(loggedItem);
        return loggedItem;
    }

    /*
    * Returns a list of all items in the HashMap
    */
    @Override
    public List<LoggedItem> fetchAll() {
        return new ArrayList<>(allLoggedItem.values());
    }


    /*
     * Returns a specific item from Firebase Firestore
     */
    @Override
    public LoggedItem fetch(int id) throws ExecutionException, InterruptedException {
        // Create reference to test collection
        CollectionReference test = dbFirestore.collection("test");

        // Create a query against the test collection
        Query query = test.whereEqualTo("loggedItemId", String.valueOf(id));

        // Retrieve query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        // For loop, could possibly return multiple items; todo()
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {

            // Empty LoggedItem to hold results
            LoggedItem loggedItem;

            // Results into LoggedItem variable
            if(document.exists()) {
                loggedItem = document.toObject(LoggedItem.class);
                return loggedItem;
            }
        }

        // Found no results
        return null;
    }


    /*
     * Deletes a specific item from the HashMap
     */
    @Override
    public void delete(int id) {
        allLoggedItem.remove(id);
    }
}
