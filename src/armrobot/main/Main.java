package armrobot.main;

// ============================================================
// IMPORTS -- Week 3: packages and import statements
// ============================================================
import armrobot.model.ObjectItem;
import armrobot.model.RobotArm;
import armrobot.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// ============================================================
// Main.java -- Entry point for the ArmRobot project (Weeks 1-5)
//
// WEEK 1: Class + Object creation, data types
// WEEK 2: Constructor, encapsulation, getters, setters
// WEEK 3: static keyword, packages, ArrayList, Set, Map
// WEEK 4: Object relationships (RobotArm knows User + ObjectItem)
// WEEK 5: Interfaces (Displayable, Controllable)
// ============================================================

public class Main {

    public static void main(String[] args) {

        printBanner("ARM ROBOT CONTROL SYSTEM -- WEEKS 1 TO 5");

        // ====================================================
        // WEEK 1 + 2: Create ObjectItem objects
        // ObjectItem = class (blueprint)
        // cube1, sphere1 = objects (real examples)
        // ====================================================
        printSection("WEEK 1 + 2 -- Creating ObjectItems");

        ObjectItem cube1   = new ObjectItem("CubeA",   "Cube");
        ObjectItem cube2   = new ObjectItem("CubeB",   "Cube");
        ObjectItem sphere1 = new ObjectItem("SphereX", "Sphere");
        ObjectItem bad     = new ObjectItem("",         "Triangle"); // invalid shape test

        // WEEK 5: Displayable interface -- displayInfo() is called on each
        cube1.displayInfo();
        cube2.displayInfo();
        sphere1.displayInfo();
        bad.displayInfo();

        // WEEK 3: static counter -- belongs to the class, not one object
        System.out.println("Total ObjectItems created: " + ObjectItem.getItemCount());

        // ====================================================
        // WEEK 1 + 2: Create User objects
        // ====================================================
        printSection("WEEK 1 + 2 -- Creating Users");

        User user1 = new User("Dara",  "Engineer");
        User user2 = new User("Sokha", "Engineer");
        User user3 = new User("Bopha", "Manager");

        // WEEK 5: Displayable interface
        user1.displayInfo();
        user2.displayInfo();
        user3.displayInfo();

        // WEEK 3: static user count
        System.out.println("Total Users created: " + User.getUserCount());

        // ====================================================
        // WEEK 3: Collections (ArrayList, Set, Map)
        // ====================================================
        printSection("WEEK 3 -- Collections Demo");

        // ArrayList -- ordered list, allows duplicates
        ArrayList<ObjectItem> itemList = new ArrayList<>();
        itemList.add(cube1);
        itemList.add(cube2);
        itemList.add(sphere1);
        System.out.println("ArrayList of items (ordered, duplicates allowed):");
        for (ObjectItem item : itemList) {
            System.out.println("  - " + item);
        }

        // Set -- unique roles only, no duplicates
        Set<String> roles = new HashSet<>();
        roles.add("Engineer");
        roles.add("Manager");
        roles.add("Engineer"); // duplicate -- will be ignored
        roles.add("Viewer");
        System.out.println("Set of roles (unique only): " + roles);
        System.out.println("Role count: " + roles.size()
                + " (we added 4 but Engineer is duplicate)");

        // Map -- key-value pairs for fast lookup
        Map<Integer, String> itemMap = new HashMap<>();
        itemMap.put(cube1.getId(),   cube1.getName());
        itemMap.put(cube2.getId(),   cube2.getName());
        itemMap.put(sphere1.getId(), sphere1.getName());
        System.out.println("Map (id -> name): " + itemMap);
        System.out.println("Look up item id=" + cube1.getId()
                + ": " + itemMap.get(cube1.getId()));

        // ====================================================
        // WEEK 4 + 5: RobotArm -- Relationships + Interfaces
        // ====================================================
        printSection("WEEK 4 + 5 -- RobotArm (Relationships + Interface)");

        RobotArm robot = new RobotArm("ARM-01");

        // WEEK 5: Displayable interface
        robot.displayInfo();
        // WEEK 5: Controllable interface
        System.out.println("Status: " + robot.getStatus());

        // WEEK 4: RobotArm uses User (association)
        System.out.println("Controlled by: " + user1.getName()
                + " [role=" + user1.getRole() + "]");

        // WEEK 4: RobotArm picks ObjectItem (composition when holding)
        System.out.println("\nTest 1: Pick a Cube:");
        robot.moveTo(3, 5);
        boolean result1 = robot.pick(cube1);
        System.out.println("Result: " + (result1 ? "SUCCESS" : "FAILED"));
        System.out.println("Status: " + robot.getStatus());

        robot.moveTo(7, 2);
        robot.drop();
        System.out.println("Status: " + robot.getStatus());

        System.out.println("\nTest 2: Pick a Sphere (should fail):");
        robot.moveTo(5, 5);
        boolean result2 = robot.pick(sphere1);
        System.out.println("Result: " + (result2 ? "SUCCESS" : "FAILED"));
        System.out.println("Status: " + robot.getStatus());

        System.out.println("\nTest 3: Pick another Cube:");
        robot.moveTo(1, 1);
        boolean result3 = robot.pick(cube2);
        System.out.println("Result: " + (result3 ? "SUCCESS" : "FAILED"));
        System.out.println("Status: " + robot.getStatus());
        robot.drop();

        // ====================================================
        // WEEK 3: Collections report from RobotArm
        // ====================================================
        printSection("WEEK 3 -- Robot Internal Collections Report");
        robot.printMoveHistory();
        robot.printVisitedPositions();
        robot.printPositionLog();

        // ====================================================
        // WEEK 3: Static counters
        // ====================================================
        printSection("WEEK 3 -- Static Counters");
        System.out.println("Total Robots created : " + RobotArm.getRobotCount());
        System.out.println("Total Users created  : " + User.getUserCount());
        System.out.println("Total ObjectItems    : " + ObjectItem.getItemCount());

        printBanner("END OF SIMULATION");
    }

    private static void printSection(String title) {
        System.out.println("\n" + "=".repeat(55));
        System.out.println(" " + title);
        System.out.println("=".repeat(55));
    }

    private static void printBanner(String title) {
        System.out.println("\n" + "*".repeat(55));
        System.out.println("*  " + title);
        System.out.println("*".repeat(55) + "\n");
    }
}
