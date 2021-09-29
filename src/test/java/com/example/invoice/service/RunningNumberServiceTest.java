package com.example.invoice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
public class RunningNumberServiceTest {

    @Autowired RunningNumberService runningNumberService;

    @Test
    public void testGetNumber() {
        Long result = runningNumberService.getNumber("test");
        Assertions.assertNotNull(result);
        System.out.println("Result : " + result);
    }

    @Test
    public void testGetNumberMultiThreaded() throws InterruptedException {
        int numberOfThread = 10;
        final int iteration = 5;

        ConcurrentHashMap resultMap = new ConcurrentHashMap();

        for (int i = 0; i < numberOfThread; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Math.abs(new Random().nextInt(100) * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < iteration; j++) {
                        List<Long> lastNumber = (List<Long>) resultMap.get(this.getId());
                        if (lastNumber == null) {
                            lastNumber = new ArrayList<>();
                        }

                        Long result = runningNumberService.getNumber("test");
                        System.out.println("Thread ["+ this.getId() +"] Last = " + result);
                        lastNumber.add(result);
                        resultMap.put(this.getId(), lastNumber);
                    }
                }
            };
            t.start();
        }

        Thread.sleep(10 * 1000);
        Enumeration<Long> keys = resultMap.keys();
        while (keys.hasMoreElements()) {
            Long key = keys.nextElement();
            System.out.println("***** Thread "+key+" *****");
            System.out.println(resultMap.get(key));
        }
    }

}
