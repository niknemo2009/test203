package lab3;

import java.util.ArrayList;

public class Goals {
    String goal;
    ArrayList<Actions> act = new ArrayList<>();

    public Goals(String goal) {
        this.goal = goal;
    }

    public void add_action(Actions... a)
    {
        for(Actions i : a)
        {
            act.add(i);
        }
    }

    public void remove_action(Actions... a)
    {
        for(Actions i : a)
        {
            act.remove(i);
        }
    }

    public void show_goal_actions()
    {
        System.out.println(goal.toString());
        if(act.size() == 0)
        {
            System.out.println("Your actions list is empty! Add something.");
        }
        else {
            for (Actions i : act) {
                System.out.println(i);
            }
        }
    }

    public double count_time()
    {
        double sum = 0;
        for (Actions i : act)
        {
            sum += i.getTime();
        }

        return sum;
    }

    @Override
    public String toString() {
        return "Goals{" +
                "goal='" + goal + '\'' +
                '}';
    }
}
