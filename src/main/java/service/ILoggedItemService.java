package service;

import dto.LoggedItem;

public interface ILoggedItemService {
    /*
    Fetch a loggedItem with a given ID.
    @param id a unique identifier for a loggedItem.
    @return the matching loggedItem, or null if dne/ no matches.
     */
    LoggedItem fetchById(int id);
}
