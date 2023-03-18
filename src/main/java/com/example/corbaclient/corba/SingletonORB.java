package com.example.corbaclient.corba;

import org.omg.CORBA.ORB;

public class SingletonORB {
    private static SingletonORB instance;
    private final ORB orb;

    private SingletonORB() {
        orb = ORB.init(new String[]{}, null);
    }

    public static synchronized SingletonORB getInstance() {
        if (instance == null) {
            instance = new SingletonORB();
        }
        return instance;
    }

    public ORB getORB() {
        return orb;
    }
}
