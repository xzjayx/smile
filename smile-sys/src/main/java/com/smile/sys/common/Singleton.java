package com.smile.sys.common;

public class Singleton {

    private static volatile Singleton singleton;

    private Singleton(){

    }

    public static Singleton getInstance(){

            if(singleton ==null){
                synchronized (Singleton.class){
                    if(singleton ==null){
                        singleton = new Singleton();
                    }
                }
            }

        return singleton;
    }
}
