package armrobot.model;

import armrobot.interfaces.Controllable;
import armrobot.interfaces.Displayable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// ============================================================
// WEEK 1: Class, Object, Attribute, Data Type
//   - RobotArm has posX, posY (int), heldItem (ObjectItem)
//
// WEEK 2: Constructor, Encapsulation, Getter, Setter
//   - all attributes are private
//   - constructor sets starting position to (0,0)
//
// WEEK 3: Static, Collections
//   - robotCount = static counter
//   - moveHistory = ArrayList to record every move (ordered log)
//   - visitedPositions = Set to track unique positions visited
//   - positionLog = Map<String, Integer> to count visits per position
//
// WEEK 4: Object Relationships
//   - RobotArm HAS-A ObjectItem (composition when holding)
//   - RobotArm HAS-A User who is controlling it (association)
//
// WEEK 5: Interfaces
//   - implements Controllable (moveTo, drop, returnHome, getStatus)
//   - implements Displayable  (displayInfo)
// ============================================================

public class RobotArm implements Controllable, Displayable {

    // WEEK 3: static counter
    private static int robotCount = 0;

    // WEEK 1 + 2: private attributes
    private int     id;
    private String  robotName;
    private int     posX;
    private int     posY;
    private boolean isHolding;

    // WEEK 4: composition -- RobotArm contains an ObjectItem
    private ObjectItem heldItem;

    // WEEK 3: Collections
    // ArrayList -- ordered move log (duplicates allowed)
    private ArrayList<String> moveHistory = new ArrayList<>();

    // Set -- unique positions visited (no duplicates)
    private Set<String> visitedPositions = new HashSet<>();

    // Map -- counts how many times each position was visited
    private Map<String, Integer> positionLog = new HashMap<>();

    // WEEK 2: Constructor
    public RobotArm(String robotName) {
        this.id        = ++robotCount;
        this.robotName = robotName;
        this.posX      = 0;
        this.posY      = 0;
        this.isHolding = false;
        this.heldItem  = null;
        logPosition(0, 0);
    }

    // ============================================================
    // WEEK 5: Controllable interface methods
    // ============================================================

    @Override
    public void moveTo(int x, int y) {
        this.posX = x;
        this.posY = y;
        String move = "Moved to (" + x + "," + y + ")";
        moveHistory.add(move);
        logPosition(x, y);
        System.out.println("  " + robotName + " -> " + move);
    }

    @Override
    public void drop() {
        if (isHolding && heldItem != null) {
            System.out.println("  " + robotName + " dropped: " + heldItem.getName()
                    + " at (" + posX + "," + posY + ")");
            heldItem  = null;
            isHolding = false;
        } else {
            System.out.println("  " + robotName + " has nothing to drop.");
        }
    }

    @Override
    public void returnHome() {
        moveTo(0, 0);
        System.out.println("  " + robotName + " returned home.");
    }

    @Override
    public String getStatus() {
        String holding = isHolding ? heldItem.getName() : "nothing";
        return robotName + " | Position: (" + posX + "," + posY + ")"
                + " | Holding: " + holding;
    }

    // ============================================================
    // WEEK 5: Displayable interface method
    // ============================================================
    @Override
    public void displayInfo() {
        System.out.println("RobotArm [id=" + id
                + ", name=" + robotName
                + ", position=(" + posX + "," + posY + ")"
                + ", holding=" + isHolding + "]");
    }

    // ============================================================
    // WEEK 4: pick() -- single version, no user authorization
    // (overloading is Week 7 -- not included here)
    // ============================================================
    public boolean pick(ObjectItem item) {
        if (item == null) {
            System.out.println("  No item to pick.");
            returnHome();
            return false;
        }
        if (!item.getShape().equalsIgnoreCase("Cube")) {
            System.out.println("  PICK FAILED: " + item.getName()
                    + " is a " + item.getShape()
                    + ". Robot can only pick Cubes.");
            returnHome();
            return false;
        }
        this.heldItem  = item;
        this.isHolding = true;
        System.out.println("  PICK SUCCESS: " + item.getName()
                + " (" + item.getShape() + ") picked up.");
        return true;
    }

    // ============================================================
    // WEEK 3: helper to log positions into Set and Map
    // ============================================================
    private void logPosition(int x, int y) {
        String key = "(" + x + "," + y + ")";
        visitedPositions.add(key);
        positionLog.put(key, positionLog.getOrDefault(key, 0) + 1);
    }

    // ============================================================
    // WEEK 3: collection report methods
    // ============================================================
    public void printMoveHistory() {
        System.out.println("  Move History (ArrayList, in order):");
        for (String m : moveHistory) {
            System.out.println("    - " + m);
        }
    }

    public void printVisitedPositions() {
        System.out.println("  Unique Positions Visited (Set, no duplicates):");
        for (String pos : visitedPositions) {
            System.out.println("    - " + pos);
        }
    }

    public void printPositionLog() {
        System.out.println("  Position Visit Count (Map, key=position, value=count):");
        for (Map.Entry<String, Integer> entry : positionLog.entrySet()) {
            System.out.println("    - " + entry.getKey()
                    + " visited " + entry.getValue() + " time(s)");
        }
    }

    // WEEK 2: Getters
    public int        getId()         { return id; }
    public String     getRobotName()  { return robotName; }
    public int        getPosX()       { return posX; }
    public int        getPosY()       { return posY; }
    public boolean    isHolding()     { return isHolding; }
    public ObjectItem getHeldItem()   { return heldItem; }

    // WEEK 3: static getter
    public static int getRobotCount() { return robotCount; }
}
