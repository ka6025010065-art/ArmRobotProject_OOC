package armrobot.interfaces;

// ============================================================
// WEEK 5: Interface
// A Controllable contract means: any robot arm that implements
// this MUST be able to move, drop, return home, and give status.
// This is the "shared behavior" rule — like Displayable in the
// coffee shop lesson.
// ============================================================
public interface Controllable {
    void moveTo(int x, int y);
    void drop();
    void returnHome();
    String getStatus();
}
