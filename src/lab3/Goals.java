package lab3;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Goals implements Comparable<Goals>{
    String goal;
    ArrayList<Actions> act = new ArrayList<>();
    static ArrayList<Goals> goalsList = new ArrayList<>();

    public Goals(String goal) {
        this.goal = goal;
        goalsList.add(this);
    }

    public void add_action(Actions... a)
    {
        for(Actions i : a)
        {
            act.add(i);
        }
    }

    public void edit_action(int index, Actions edited)
    {
        act.set(index, edited);
    }

    public void remove_action(Actions... a)
    {
        for(Actions i : a)
        {
            act.remove(i);
        }
    }

    public void countPeriodTime(LocalDate start, LocalDate end)
    {
        LocalDate resultDate = end;
        int years = 0;
        int months = 0;
        if(start.getYear() == end.getYear())
        {
            months = end.getMonthValue() - start.getMonthValue();
            System.out.println("You`ll need " + months + " monts to complete your goal");
        }
        else
        {
            years = end.getYear() - start.getYear();
            System.out.println("You`ll need " + years + " year to complete your goal");
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

    public int count_time()
    {
        int sum = 0;
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

    public int compareTo(Goals g)
    {
        return this.goal.compareTo(g.goal);
    }
}