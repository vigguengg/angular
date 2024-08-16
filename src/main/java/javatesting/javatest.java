package javatesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class javatest {

    public void test() {
        System.out.println("inside java");
    }
}

class java2 extends javatest {
    public void test(){
        System.out.println("inside java2");
    }

    public static void main(String[] args) {
        javatest javatest = new java2();
        List<Integer> list = Arrays.asList(1,2,5,4);
       // list.stream().s
        javatest.test();


    }
}
