package dairy.model;

import java.util.*;

public class Action implements Cloneable{
    private String actionName;
    private static ArrayList<Action> actionsArrayList = new ArrayList<>();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public ArrayList<Action> getActionsArrayList() {
        return actionsArrayList;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Action() {
    }

    public Action(String actionName) {
        this.actionName = actionName;
    }

    public void addAction(String actionName)
    {
        actionsArrayList.add(new Action(actionName));
        Target target = new Target();
        for (int i = 0; i < target.getTargetsCoefficients().size(); i++) {
            target.getTargetsCoefficients().get(i).add(0.0);
        }
    }

//    public void removeAction(int index)
//    {
//        Action action = new Action();
//        Target target = new Target();
//        action.getActionsUniqueArrayList().remove(index);
//        for (int i = 0; i < target.getTargetsCoefficients().size(); i++) {
//            target.getTargetsCoefficients().get(i).remove(index);
//        }
//    }
}
