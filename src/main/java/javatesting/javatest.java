package javatesting;

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
        javatest.test();


    }
}
