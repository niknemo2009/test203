package dairy.model;

import java.util.ArrayList;

public class Target implements Cloneable{
    private String targetName;
    private int timeOnTargetRequired;
    private static ArrayList<Target> targetsArrayList = new ArrayList<>();
    private static ArrayList<ArrayList<Double>> targetsCoefficients = new ArrayList<>();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public ArrayList<Target> getTargetsArrayList() {
        return targetsArrayList;
    }

    public String getTargetName() {
        return targetName;
    }

    public int getTimeOnTargetRequired() {
        return timeOnTargetRequired;
    }

    public ArrayList<ArrayList<Double>> getTargetsCoefficients() {
        return targetsCoefficients;
    }

    public void setTimeOnTargetRequired(int timeOnTargetRequired) {
        this.timeOnTargetRequired = timeOnTargetRequired;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Target() {
    }

    public Target(String nameTarget) {
        this.targetName = nameTarget;
        this.timeOnTargetRequired = 0;
    }

    public Target(String nameTarget, int timeOnTargetRequired) {
        this.targetName = nameTarget;
        this.timeOnTargetRequired = timeOnTargetRequired;
    }

    public void addTarget(String targetName)
    {
        Target target = new Target();
        Action action = new Action();
        target.targetsArrayList.add(new Target(targetName));
        target.targetsCoefficients.add(new ArrayList<>());
        for (int i = 0; i < action.getActionsArrayList().size(); i++) {
            target.getTargetsCoefficients().get(target.getTargetsArrayList().size()-1).add(i,0.0);
        }
    }

    public void addTarget(String targetName, int timeOnTargetRequired)
    {
        Target target = new Target();
        Action action = new Action();
        target.targetsArrayList.add(new Target(targetName,timeOnTargetRequired));
        target.targetsCoefficients.add(new ArrayList<>());
        for (int i = 0; i < action.getActionsArrayList().size(); i++) {
            target.getTargetsCoefficients().get(target.getTargetsArrayList().size()-1).add(i,0.0);
        }
    }

    public void printTargetsCoefficients()
    {
        Target target = new Target();
        for (int i = 0; i < target.getTargetsCoefficients().size(); i++) {
            System.out.print("target №"+(i+1)+":  ");
            for (int j = 0; j < target.getTargetsCoefficients().get(i).size(); j++) {
                System.out.print(target.getTargetsCoefficients().get(i).get(j)+"\t");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    public void setRandomTargetsCoefficients()
    {
        double scale = Math.pow(10, 1);
        Target target = new Target();
        for (int i = 0; i < target.getTargetsCoefficients().size(); i++) {
            for (int j = 0; j < target.getTargetsCoefficients().get(i).size(); j++) {
                target.getTargetsCoefficients().get(i).set(j, Math.round(Math.random() * scale) / scale);
            }
        }
    }
}
