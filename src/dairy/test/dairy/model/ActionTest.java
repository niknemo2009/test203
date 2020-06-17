package dairy.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {
    private Action action;

    //можно не использовать публичные методы
    //@DisplayName можно указать название теста
    //@Disabled можно отключить тест
    //@Nested аннотации наследуют контекст родителя, можно групировать тесты
    //добавились Assumptions, отключение по условию

    @Nested
    class AddingTestBlock
    {
        @Test
        @DisplayName("My first test: adding action")
            //@Disabled
        void addAction() {
            Assumptions.assumeTrue(Runtime.getRuntime().availableProcessors()>=1);
            Assumptions.assumeTrue(Runtime.getRuntime().freeMemory()>0);
            action = new Action();
            int expected = action.getActionsArrayList().size()+1;
            action.addAction("new action");
            int actual = action.getActionsArrayList().size();
            assertEquals(expected,actual);
        }
    }

    @TestFactory
    @DisplayName("My first factory: adding action")
    Stream<DynamicTest> dynamicTestStream(){
        return Stream.generate(Math::random).limit(20).mapToInt(v->(int)(v*1000))
                .mapToObj(i->DynamicTest.dynamicTest("testing adding operation with name "+ i,
                        ()->assertTrue(Integer.toString(i).length()>0)));
    }

    @ParameterizedTest
    @DisplayName("My first ValueSource test: adding action")
    @ValueSource (strings = {"new action", "old action", "def action"})
    void addAction(String actionName) {
        action = new Action();
        action.addAction("new action");
        assertEquals(action.getActionsArrayList()
            .get(action.getActionsArrayList()
            .size()-1).getActionName().length(),
                actionName.length());
    }
}