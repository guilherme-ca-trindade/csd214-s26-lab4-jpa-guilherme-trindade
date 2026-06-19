# Bookstore CLI Application

A console-based Java application for managing a bookstore inventory, performing sales, and tracking cash flow. This project demonstrates object-oriented programming concepts including inheritance, polymorphism, and interface implementation in Java 24.

## Features

*   **Inventory Management:**
    *   **Books:** Manage items with Title, Author, Price, and Copies.
    *   **Magazines:** Manage periodicals with Order Quantity and Issue Date.
    *   **Disc Magazines:** Specialized magazines that include a disc.
    *   **Tickets:** Simple saleable items with a description and price.
*   **CRUD Operations:** Add, Edit, and Delete items from the inventory.
*   **Sales System:** Sell items to decrement inventory count and increase the Cash Till total.
*   **Data Generation:** Uses `JavaFaker` to populate the inventory with realistic dummy data.
*   **Menu System:** Interactive console menu for navigation.

## Class Hierarchy

![Class Diagram](documentation/bookstore-2026-01-30-142617.png)

The hierarchy implements the following structure:
*   **SaleableItem (Interface):** Defines `sellItem()` and `getPrice()`.
*   **Editable (Abstract):** Handles console input/output and parsing.
*   **Publication:** Base class for Books and Magazines (Title, Price, Copies).

## Prerequisites

*   **Java JDK:** Version 24
*   **Maven:** 3.6+

## Dependencies

*   [JavaFaker](https://github.com/DiUS/java-faker) (1.0.2): For generating random test data.
*   [JUnit 5](https://junit.org/junit5/) (5.10.0): For unit testing.

## How to Run

1.  **Compile the project:**
    ```bash
    mvn clean compile
    ```

2.  **Run the application:**
    ```bash
    mvn exec:java -Dexec.mainClass="csd214.bookstore.Main"
    ```

## Usage

Upon starting, the application will populate the list with random data. You will see the following menu:

```text
***********************
 1. Add Items
 2. Edit Items
 3. Delete Items
 4. Sell item(s)
 5. List items
99. Quit
***********************
```

*   **Add Items:** Choose a specific type (Book, Magazine, etc.) and follow the prompts.
*   **Edit Items:** Select an index from the list to modify fields.
*   **Sell Items:** Select an index to sell. This decreases the 'Copies' count (for Publications) and adds the price to the internal Cash Till.

## Running Tests

Unit tests are implemented using JUnit 5 to verify the logic of POJOs and input mocking.

Run the tests using Maven:

```bash
mvn test
```

## Project Structure

```
src/
├── main/
│   └── java/
│       └── csd214/
│           └── bookstore/
│               ├── Main.java           # Entry point
│               ├── App.java            # Controller / Menu Logic
│               └── pojos/              # Data Models
│                   ├── Editable.java
│                   ├── SaleableItem.java
│                   ├── Product.java
│                   ├── Publication.java
│                   ├── Book.java
│                   ├── Magazine.java
│                   ├── DiscMag.java
│                   ├── Ticket.java
│                   └── CashTill.java
└── test/
    └── java/
        └── csd214/
            └── bookstore/
                └── pojos/              # Unit Tests
```

## Lab 1: Analysis

### Problem Statement:
A Nintendo Gaming Store specializing in the sale of Nintendo consoles, gaming
hardware, and physical game titles. Customers can purchase complete gaming systems 
for example, Nintendo Switch), standalone hardware components (for example,Joy-Con controllers), 
and Nintendo-exclusive game titles (for example, Mario Odyssey, or Legend of Zelda).
The store tracks inventory by product type, manages pricing for different product 
categories.

### Noun/Verb Table: 
#### Nouns Table (Potential Classes/Attributes):
1. **Console:** (Nintendo Switch, Switch OLED, Switch Lite)
2. **Game** (Mario Odyssey, The Legend of Zelda, Mario Kart)
3. **Hardware** (controllers, docks, accessories)
4. **Manufacturer** (Nintendo, third-party)
5. **Stock/Inventory**
6. **Price**
7. **Release Date**

#### Verbs Table (Potential Methods):
1. **sellItem()** - process a sale
2. **checkStock()** - verify inventory
3. **updatePrice()** - modify pricing
4. **initialize()** - set up product details
5. **edit()** - modify product attributes

#### Synonyms Table:
1. **Game** = Title = Software
2. **Console** = Platform = System
3. **Hardware** = Component = Accessory

### Phase 2: AI-Assisted Architectural Blueprinting

#### Class Hierarchy Design

Based on OO Analysis principles, the following class hierarchy is recommended:
![NintendoClassHierarchy.png](documentation/NintendoClassHierarchy.png)

### **Detailed Design:**

| Class Name | Parent Class | Unique Field | Field Type | Purpose |
| --- | --- | --- | --- | --- |
| **NintendoProduct** | Product | platform | String | Identifies which Nintendo system (Switch, Wii U, 3DS) |
| **NintendoGame** | NintendoProduct | genre | String | Specifies game category (Action, RPG, Sports, Puzzle, Adventure) |
| **NintendoHardware** | NintendoProduct | hardwareType | String | Specifies hardware category (Console, Controller, Dock, Case, Charger) |

### **Why This Design?**
✅ **Encapsulation:** Each class will have private fields with public getters/setters

✅ **Constructor Chaining:** The "Bucket Brigade" pattern passes up through

✅ **Extensibility:** Easy to add more concrete types (e.g., `NintendoAccessory`) later

✅ **Polymorphism:** Each concrete class can override `sellItem()` for specific behavior

✅ **Java Bean Compliant:** No-arg constructors and loaded constructors included `productId``Product`







