# Grocery Planner

---

Design Document

Gabriel Comstock
-
-
-

## Introduction

Grocery Planner is an application that lets users create lists of items among members of a household that need to be picked up at the store.

-	Ability to create a shopping list.
-	Food items can search USDA database to associate more information with entries.
-	Lists can be marked as complete and move to history.
-	Users can view historical lists.


## Storyboard

[Link to Storyboard](https://it3048ccomsto.invisionapp.com/freehand/GroceryPlanner-znaD5UVdQ)

## Functional Requirements

### Requirement 1: Create new entries in a list

#### Scenario

User wants to add a new item to a shopping list

#### Dependencies

User has navigated to the Grocery Planner Website

#### Assumptions

User is able to enter text into a text box, and hit a button to add that to an active list

#### Example

**Given**
User wants to add broccoli to the list

**When**
User enters broccoli into a text box and presses add to list.

**Then**
broccoli is added to the active list.

Example of Return Data

{
    List 2023.9.10,

Items:
{
Broccoli,
otherItem,
}

}

### Requirement 2: Food entries are matched to database entries

#### Scenario
User wants to add a food item to the list.

#### Dependencies
USDA database results are available.

#### Assumptions
Food names are in English.

#### Example

2.1
**Given**
User wants to add Broccoli to the list

**When**
User enters Broccoli, top results from the USDA database are returned.

**Then**  result
User will be able to choose one of these with food data attached, or may make a new entry.


## Class Diagram

![GroceryPlanner class diagram](https://github.com/comstogm/GroceryPlanner/assets/110064071/3be64f85-0142-47f9-b9cb-c5fd84f30bfb)

### Class Diagram Description


**List of parts:**   
GroceryPlannerApplication: The main program class that kicks off the application

GroceryPlannerController: controls the API endpoints for adding/removing items.

Item class: Represents an item.

Food class: subclass of item, represents a food item and will hold USDA database information

LoggedItem class: represents each specific item that is added to the list.

LoggedItemService: Manages persistence of user entered data.

ItemDAO and ILoggedItemDAO: Used by LoggedItemService to create persistent data.


## Scrum Roles

- DevOps/Product Owner/Scrum Master:
- Frontend Developer:
- Integration Developer:

## Milestone
https://github.com/comstogm/GroceryPlanner/milestone/1
## Weekly Meeting

Time tbd

Meeting Information
[Meeting link](actual link to meeting)
Meeting number:
### ### ###








