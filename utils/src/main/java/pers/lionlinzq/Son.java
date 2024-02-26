package pers.lionlinzq;

public class Son extends Father {

    public static void main(String[] args) {
        new Son().test();
    }

    @Override
    public void test() {
        System.out.println("这是子类方法");
        super.test();
    }

}
