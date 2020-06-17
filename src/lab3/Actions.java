package lab3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Actions{
    String name;
    int time;
    public LocalDate date;
    Map<Goals, Integer> tbl = new TreeMap<>();
    static ArrayList<Actions> actionList = new ArrayList();


    public Actions(String name, int time, LocalDate date) {
        this.name = name;
        this.time = time;
        this.date = date;
        actionList.add(this);
    }

    public double getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void manageTime(Goals goal)
    {
        if(tbl.size() == 0)
        {
            tbl.put(goal, this.time);
        }
        else
        {
            int tableSize = tbl.size();
            int part = this.time / tableSize + 1;

            tbl.put(goal, part);
            for(Map.Entry<Goals, Integer> temp : tbl.entrySet())
            {
                temp.setValue(part);
            }
        }

        for(Map.Entry<Goals, Integer> i : tbl.entrySet())
        {
            System.out.println("g: " + i.getKey() + " " + i.getValue());
        }
    }

    public void setTime(int time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Actions{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", date=" + date +
                '}';
    }
}