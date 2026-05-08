# ArmRobot Project — OOC Weeks 1 to 5

## Project Overview
A robot arm control simulation that demonstrates OOP concepts from weeks 1 to 5.
The robot picks up cube objects, moves to positions, drops items, and logs all activity.

---

## How to Run

### Command line
```
javac -d out -sourcepath src src/armrobot/main/Main.java
java -cp out armrobot.main.Main
```

### IntelliJ IDEA
1. Open the project folder
2. Right-click `src` and choose Mark Directory as Sources Root
3. Open `Main.java` and click the green play button

### VS Code
1. Install Extension Pack for Java
2. Open the project folder
3. Open `Main.java` and click Run above the main method

---

## Project Structure

```
src/
  armrobot/
    interfaces/
      Displayable.java      (Week 5)
      Controllable.java     (Week 5)
    model/
      ObjectItem.java       (Weeks 1, 2, 3, 5)
      User.java             (Weeks 1, 2, 3, 5)
      RobotArm.java         (Weeks 1, 2, 3, 4, 5)
    main/
      Main.java             (runs everything)
```

---

## File Summary

### `Displayable.java` — Week 5
Defines the `displayInfo()` contract.
Any class that implements this must provide a `displayInfo()` method.
```java
public interface Displayable {
    void displayInfo();
}
```
Implemented by: `ObjectItem`, `User`, `RobotArm`

---

### `Controllable.java` — Week 5
Defines the robot control contract.
Any class that implements this must provide `moveTo()`, `drop()`, `returnHome()`, and `getStatus()`.
```java
public interface Controllable {
    void moveTo(int x, int y);
    void drop();
    void returnHome();
    String getStatus();
}
```
Implemented by: `RobotArm`

---

### `ObjectItem.java` — Weeks 1, 2, 3, 5

| Week | What it shows |
|---|---|
| 1 | Class with attributes: `name` (String), `shape` (String), `id` (int) |
| 2 | Private attributes, constructor, getters, setters with validation |
| 3 | Static `itemCount` counter shared by all objects |
| 5 | Implements `Displayable`, provides `displayInfo()` |

Valid shapes: `Cube` or `Sphere` only. Any other value is rejected and set to `Unknown`.

---

### `User.java` — Weeks 1, 2, 3, 5

| Week | What it shows |
|---|---|
| 1 | Class with attributes: `name` (String), `role` (String), `id` (int) |
| 2 | Private attributes, constructor, getters, setters with validation |
| 3 | Static `userCount` counter shared by all objects |
| 5 | Implements `Displayable`, provides `displayInfo()` |

Note: `Engineer` and `Manager` subclasses are not included. Those belong to weeks 6 and 7.

---

### `RobotArm.java` — Weeks 1, 2, 3, 4, 5

| Week | What it shows |
|---|---|
| 1 | Class with attributes: `robotName`, `posX`, `posY`, `isHolding` |
| 2 | Private attributes, constructor, getters |
| 3 | Static `robotCount`, `ArrayList` move history, `Set` visited positions, `Map` position log |
| 4 | Association with `User`, composition with `ObjectItem` via `heldItem` |
| 5 | Implements `Controllable` and `Displayable` |

Key behavior:
- `pick(ObjectItem)` only succeeds if the item shape is `Cube`
- `drop()` releases the held item
- `returnHome()` moves the robot back to position (0, 0)
- `getStatus()` returns current position and held item info

---

### `Main.java` — entry point

Demonstrates all 5 weeks in sequence:
1. Creates `ObjectItem` objects and calls `displayInfo()`
2. Creates `User` objects and calls `displayInfo()`
3. Shows `ArrayList`, `Set`, and `Map` with items and roles
4. Creates `RobotArm`, connects it to `User` and `ObjectItem`
5. Calls `Controllable` and `Displayable` interface methods on the robot

---

## OOP Concepts Covered

| Week | Concept |
|---|---|
| 1 | Class, object, attribute, data type |
| 2 | Constructor, encapsulation, getter, setter |
| 3 | Static keyword, packages, ArrayList, Set, Map |
| 4 | Association and composition between classes |
| 5 | Interfaces (Displayable, Controllable) |
