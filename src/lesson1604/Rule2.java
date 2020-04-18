package lesson1604;

public class Rule2 implements Filtr{
    @Override
    public boolean doCondition(int element) {
        return element%2==0?true:false;
    }
}
