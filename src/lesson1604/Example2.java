package lesson1604;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Example2 {
    public static void main(String[] args) {
        List<Student> list=new ArrayList<>();
        Student st1=new Student(20,"Petrov");
        Student st2=new Student(18,"Ivanov");
        Student st3=new Student(23,"Sidorov");
        Student st4=new Student(21,"Ivanov");
        Student st5=new Student(21,"Sidorov");
        list.add(st1);
        list.add(st2);
        list.add(st3);
        list.add(st4);
        list.add(st5);
        Collections.sort(list,(q,w)->{
            return q.age==w.age?0:(q.age>w.age?1:-1);
        } );
        Collections.sort(list,(q,w)->{
            int result=-2;
            if(q.age==w.age){
               result=0;
            }else if(q.age<w.age){
                result=1;
            }else{
                result=-1;
            }
            return result;
        } );
        Collections.sort(list,(q,w)->{
            return q.name.compareTo(w.name)*111+
                    q.age==w.age?0:(q.age>w.age?1:-1);
        } );
        System.out.println(list);



            //public int compare(Student o1, Student o2) {


    }
}
