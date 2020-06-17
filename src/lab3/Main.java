package lab3;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Goals g1 = new Goals("Study oop");
        g1.add_action(new Actions("ac1", 1, LocalDate.of(2000, 5, 10)));
        g1.add_action(new Actions("ac2", 3, LocalDate.of(2001, 6, 11)));
        g1.add_action(new Actions("ac3", 3, LocalDate.of(2002, 6, 11)));

        //g1.show_goal_actions();
        System.out.println(g1.count_time());

        g1.countPeriodTime(LocalDate.of(2000, 5, 10), LocalDate.of(2000, 11, 11));
        Actions a11 = new Actions("labi", 1, LocalDate.of(2002, 7, 12));
        System.out.println(a11.actionList);
        a11.manageTime(g1);
        a11.manageTime(g1);
        //Actions a12 = new Actions("books", 1, LocalDate.of(2003, 8, 1));
//        g1.add_action(new Actions("spat", 12, LocalDate.of(2003, 8, 1)));
//        g1.add_action(a11, a12);
//        g1.show_goal_actions();
//        g1.remove_action(a11, a12);
//        System.out.println("after removal");
//        g1.show_goal_actions();
//        System.out.println(g1.count_time());
//        Goals g2 = new Goals("Otdihat");
//        g2.show_goal_actions();
//        g2.add_action(new Actions("spat", 24, LocalDate.of(2004, 9, 2)), new Actions("igrat v computer", 24, LocalDate.of(2003, 8, 2)));
//        Actions a3 = new Actions("spat2", 2, LocalDate.of(2003, 8, 1));
//        g2.add_action(a3);
//        System.out.println("before edit");
//        g2.show_goal_actions();
//        g2.edit_action(0, new Actions("ssppaat", 0, LocalDate.of(2003, 8, 1)));
//        System.out.println("after edit");
//        g2.show_goal_actions();
//        System.out.println(g2.count_time() + " hours");
    }
}