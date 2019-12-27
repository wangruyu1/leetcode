package com.canal.client.demo;

import java.util.ArrayList;
import java.util.List;

public class SonFieldTest {

    public static void main(String[] args){
        Father f1 = new Son1();
        f1.addStr("son1");
        Father f2 = new Son2();
        f2.addStr("son2");
        f1.print();
        f2.print();
    }


    static class Father {
        protected List<String> strs = new ArrayList<>();

        public void addStr(String str) {
            this.strs.add(str);
        }

        public void print(){
            System.out.println(this.strs);
        }
    }

    static class Son1 extends Father {


    }

    static class Son2 extends Father {


    }
}
