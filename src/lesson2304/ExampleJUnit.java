package lesson2304;

public class ExampleJUnit {

    void one(){

    }

    public int[] deleteElement(int[] arr, int index) {
        int[] result=new int[arr.length==0?0:arr.length-1];
        if(index>=arr.length|| index<0){
            return arr;
        }
        for(int i=0;i<arr.length-1;i++){
            if(i<index){
                result[i]=arr[i];
            }else{
                result[i]=arr[i+1];
            }

        }

        return result;
    }
    public int[] deleteElementwithEgor(int[] arr, int index) {
        int[] result=new int[arr.length==0?0:arr.length-1];
        if(index>=arr.length|| index<0){
            return arr;
        }
        for (int i=0; i<index;i++) {
            result[i]=arr[i];
        }
        for (int i=index; i<result.length;i++) {
            result[i]=arr[i+1];
        }


        return result;
    }
}
