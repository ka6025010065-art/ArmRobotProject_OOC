package armrobot.model;

import armrobot.interfaces.Displayable;

// ============================================================
// WEEK 1: Class, Object, Attribute, Data Type
//   - ObjectItem is a class (blueprint)
//   - name is String, shape is String, id is int
//
// WEEK 2: Constructor, Encapsulation, Getter, Setter
//   - private attributes protect the data
//   - constructor sets values when object is created
//   - setShape() validates: only "Cube" or "Sphere" allowed
//
// WEEK 3: Static keyword
//   - itemCount belongs to the class, not one object
//   - every new ObjectItem auto-increments the shared counter
//
// WEEK 5: Interface
//   - implements Displayable means this class MUST have displayInfo()
// ============================================================

public class ObjectItem implements Displayable {

    // WEEK 3: static - one shared counter for ALL ObjectItem objects
    private static int itemCount = 0;

    // WEEK 2: private attributes (encapsulation)
    // WEEK 1: data types: int, String
    private int id;
    private String name;
    private String shape; // only "Cube" or "Sphere"

    // WEEK 2: Constructor - sets values the moment object is created
    public ObjectItem(String name, String shape) {
        this.id    = ++itemCount;   // WEEK 3: auto-increment static counter
        setName(name);
        setShape(shape);
    }

    // ---- WEEK 5: Interface method (required by Displayable) ----
    @Override
    public void displayInfo() {
        System.out.println("ObjectItem [id=" + id
                + ", name=" + name
                + ", shape=" + shape + "]");
    }

    // ---- WEEK 2: Getters ----
    public int getId()              { return id; }
    public String getName()         { return name; }
    public String getShape()        { return shape; }

    // WEEK 3: static getter - called with ObjectItem.getItemCount()
    public static int getItemCount() { return itemCount; }

    // ---- WEEK 2: Setters with validation ----
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            this.name = "Unknown";
        } else {
            this.name = name;
        }
    }

    public void setShape(String shape) {
        if (shape != null &&
           (shape.equalsIgnoreCase("Cube") || shape.equalsIgnoreCase("Sphere"))) {
            this.shape = shape;
        } else {
            System.out.println("Warning: shape must be Cube or Sphere. Set to Unknown.");
            this.shape = "Unknown";
        }
    }

    @Override
    public String toString() {
        return "ObjectItem [id=" + id + ", name=" + name + ", shape=" + shape + "]";
    }
}
