package com.ssj.mysqldemo;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnlyTest {
    @Test
    public void filterUrls() {
        try(
                FileReader fr = new FileReader("/tmp/aa.txt");
                BufferedReader br = new BufferedReader(fr)
        ) {
            ArrayList<String> left;
            File dir = new File("/Users/shengjiesong/Downloads/mp4");
            String[] list = dir.list();
            if (dir.exists()) {
                System.out.println("exists");
            }
            if (list == null) {
                System.out.println("dir contains no files");
                return;
            }
            left = new ArrayList<>(Arrays.asList(list));
            ArrayList<String> result = new ArrayList<>(64);
            String url;
            while ((url = br.readLine()) != null) {
                if (url.length() > 0) {
                    String tail = url.substring(url.lastIndexOf('/')+1);
                    String name = tail;
                    if (tail.indexOf('?') > 0)
                        name = tail.substring(0, tail.indexOf('?'));
                        boolean a = left.remove(name + ".mp4");
                    if (a) {
                        System.out.println("removed " + name);
                    } else {
                        result.add(url);
                    }
                }
            }
            for (String u : result){
                System.out.println(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void xmlTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        Object moreComplexObject = context.getBean("moreComplexObject");
        System.out.println(moreComplexObject);
    }

}
