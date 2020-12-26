package com.pichs.xsql;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EXTest {


    @Test
    public void  tss() {

        List<String> aa = new ArrayList<>();
        aa.add("1");
        aa.add("2");
        aa.add("3");
        aa.add("4");
        aa.add("5");

//        String[] strings = ;

//        System.out.println(strings);


        System.out.println(Arrays.toString(aa.toArray(new String[0])));
    }
}
