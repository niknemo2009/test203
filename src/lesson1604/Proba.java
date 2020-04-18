package lesson1604;

public class Proba {
//message 1

    public static void main(String[] args) {
Filtr ruleOne= new Rule2();
//int[] var={4};
int[] count={0};
        Filtr ruleTwo=new Filtr() {
            int temp=0;
    @Override
    public boolean doCondition(int element) {
        //var[0]=67;
        boolean result=false;
        if (element>4){
           // temp++;
            count[0]++;
            System.out.println(temp);
            result=true;
        }

        return result;
    }
};
//var[0]=78;

Filtr ruleThree= q-> {
    return q>4;
};
    Proba pr=new Proba();
    int[] arr={2,3,4,5,7};
    //pr.filtrByCondition(arr,ruleOne);
    pr.filtrByCondition(arr,ruleTwo);
        System.out.println(count[0]);
        final int var33=90;
        //var33=67;
        final int[] arr33={1,3,5};
        arr33[0]=45;
        int[] qwe={1,2};
        //arr33=qwe;
//pr.filtrByCondition(arr, (int element)-> element>3);
    }

    void filtrByCondition(int[] arr,Filtr filtr){
        for(int temp:arr){
            if(filtr.doCondition(temp)){
                System.out.println(temp);
            }
        }
    }
}

@FunctionalInterface
interface Filtr{
    boolean doCondition(int element);
}
