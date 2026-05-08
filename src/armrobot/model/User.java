package armrobot.model;

import armrobot.interfaces.Displayable;

// ============================================================
// WEEK 1: Class, Object, Attribute, Data Type
//   - User is a class (blueprint for all user types)
//   - name=String, role=String, id=int
//
// WEEK 2: Constructor, Encapsulation, Getter, Setter
//   - private attributes protect the data
//   - constructor initializes all values safely
//
// WEEK 3: Static keyword
//   - userCount tracks how many User objects exist in total
//
// WEEK 5: Interface
//   - implements Displayable -- every User must show info
// ============================================================

public class User implements Displayable {

    // WEEK 3: static counter -- belongs to the whole class
    private static int userCount = 0;

    // WEEK 2: private attributes (encapsulation)
    // WEEK 1: data types: int, String
    private int    id;
    private String name;
    private String role;

    // WEEK 2: Constructor
    public User(String name, String role) {
        this.id = ++userCount;
        setName(name);
        setRole(role);
    }

    // WEEK 5: Interface method (required by Displayable)
    @Override
    public void displayInfo() {
        System.out.println("User [id=" + id
                + ", name=" + name
                + ", role=" + role + "]");
    }

    // WEEK 2: Getters
    public int    getId()   { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }

    // WEEK 3: static getter
    public static int getUserCount() { return userCount; }

    // WEEK 2: Setters with validation
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            this.name = "Unknown";
        } else {
            this.name = name;
        }
    }

    public void setRole(String role) {
        if (role == null || role.isBlank()) {
            this.role = "None";
        } else {
            this.role = role;
        }
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", role=" + role + "]";
    }
}
