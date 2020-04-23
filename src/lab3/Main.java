package lab3;

public class Main {
    public static void main(String[] args) {
        Goals g1 = new Goals("Study oop");
        Actions a11 = new Actions("labi", 1.30);
        Actions a12 = new Actions("books", 1.00);
        g1.add_action(new Actions("spat", 12.0));
        g1.add_action(a11, a12);
        g1.show_goal_actions();
        g1.remove_action(a11, a12);
        System.out.println("after removal");
        g1.show_goal_actions();
        System.out.println(g1.count_time());
        Goals g2 = new Goals("Otdihat");
        g2.show_goal_actions();
        g2.add_action(new Actions("spat", 24.00), new Actions("igrat v computer", 24.00));
        Actions a3 = new Actions("spat2", 2.00);
        g2.add_action(a3);
        g2.show_goal_actions();
        System.out.println(g2.count_time() + " hours");
    }
}
