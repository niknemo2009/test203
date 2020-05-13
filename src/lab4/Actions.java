package lab4;

public class Actions{
    String name;
    double time;

    public Actions(String name, double time) {
        this.name = name;
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Actions - " +
                "name='" + name + '\'' +
                ", time=" + time + "\n";
    }
}