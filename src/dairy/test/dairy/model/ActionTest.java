package dairy.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {

    @Test
    @DisplayName("My first test: adding action")
    //@Disabled
    void addAction() {
        Action action = new Action();
        int expected = action.getActionsArrayList().size()+1;
        action.addAction("new action");
        int actual = action.getActionsArrayList().size();
        assertEquals(expected,actual);
    }
}