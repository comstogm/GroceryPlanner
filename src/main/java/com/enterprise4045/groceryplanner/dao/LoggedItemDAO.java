package com.enterprise4045.groceryplanner.dao;

import com.enterprise4045.groceryplanner.dto.LoggedItem;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@Profile("dev")
public class LoggedItemDAO implements ILoggedItemDAO{

    /* Initialize dbFirestore connection */
    private final Firestore dbFirestore = FirestoreClient.getFirestore();

    /* Creates new List to store LoggedItems */
    ArrayList<LoggedItem> allLoggedItems = new ArrayList<>();

    /* Saves LoggedItem to Firebase Firestore
    Returns LoggedItem */
    @Override
    public LoggedItem save(LoggedItem loggedItem) {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("test").document().set(loggedItem);
        return loggedItem;
    }

    /* Returns a list of all items in the HashMap */
    @Override
    public List<LoggedItem> fetchAll() throws ExecutionException, InterruptedException {
        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> allSnapshot = dbFirestore.collection("test").get();

        // allSnapshot.get() blocks on response
        List<QueryDocumentSnapshot> documents = allSnapshot.get().getDocuments();

        // Empty list
        allLoggedItems.clear();

        // Iterate over documents and add them to allLoggedItems
        for (QueryDocumentSnapshot document : documents) {
            LoggedItem foundLoggedItem = document.toObject(LoggedItem.class);

            allLoggedItems.add(foundLoggedItem);
        }

        return allLoggedItems;
    }


    /* Returns a specific item from Firebase Firestore */
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


    /* Deletes a specific item from the Firebase Firestore */
    @Override
    public void delete(int id) throws ExecutionException, InterruptedException {
        // Create reference to test collection
        CollectionReference test = dbFirestore.collection("test");

        // Create a query against the test collection
        Query query = test.whereEqualTo("loggedItemId", String.valueOf(id));

        // Retrieve query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        // For each query result, delete based on documentId
        for (DocumentSnapshot docs : querySnapshot.get().getDocuments()) {
            if(docs.exists()) {
                dbFirestore.collection("test").document(docs.getId()).delete();
            }
        }
    }
}
