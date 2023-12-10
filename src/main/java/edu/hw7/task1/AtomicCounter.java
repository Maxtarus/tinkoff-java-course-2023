package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

 class AtomicCounter implements ThreadSafeCounter {
    private final AtomicInteger count = new AtomicInteger(0);

     @Override
     public void increment() {
         count.incrementAndGet();
     }

     @Override
     public int get() {
         return count.get();
     }
 }




