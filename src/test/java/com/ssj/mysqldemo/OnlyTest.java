package com.ssj.mysqldemo;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class OnlyTest {
//    @Test
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

//    @Test
    public void xmlTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        Object moreComplexObject = context.getBean("moreComplexObject");
        System.out.println(moreComplexObject);
    }

    @Test
    public void jasyptTest() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("SSJ");
        String encrypt = textEncryptor.encrypt("123456");
        System.out.println(encrypt);

        String decrypt = textEncryptor.decrypt(encrypt);
        System.out.println(decrypt);
    }

    @Test
    public void testYear() {
        System.out.println("2022GDP美/中：" + 100.0/72);
        double cnGrowth = 0.036;
        double usGrowth = 0.019;
        int years = 20;
        System.out.println("如果中国GDP年均增长率维持在" + cnGrowth + "，美国在" + usGrowth + "，则" + years + "年后：");
        double cnGdp = Math.pow(1 + cnGrowth, years);
        double usGdp = Math.pow(1 + usGrowth, years);
        System.out.println("中国GDP涨幅" + (cnGdp - 1));
        System.out.println("美国GDP涨幅" + (usGdp - 1));
        System.out.println(years + "年后美/中：" + usGdp/cnGdp*100/72);
    }

}
