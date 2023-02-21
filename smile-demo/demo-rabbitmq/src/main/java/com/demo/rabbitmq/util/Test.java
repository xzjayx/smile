package com.demo.rabbitmq.util;

/**
 * @author :xiezhi
 * @date : 2023/2/20
 */
public class Test {
    public static void main(String[] args) {
        A ab = new B();
        ab = new B();
    }

}


class A{
    static {
        System.out.println(1);
    }

    public A(){
        System.out.println(2);
    }
}

class B extends A{
    static {
        System.out.println("a");
    }

    public B(){
        System.out.println("b");
    }
}

