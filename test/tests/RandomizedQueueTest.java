/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tasks.RandomizedQueue;

/**
 *
 * @author milos
 */
public class RandomizedQueueTest {
    
    private RandomizedQueue<Integer> randomizedQueue;
    
    public RandomizedQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        randomizedQueue = new RandomizedQueue<>();
    }
    
    @After
    public void tearDown() {
        randomizedQueue = null;
    }
    
    @Test
    public void enqueueBasicTest() {
        assertTrue(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 0);
        randomizedQueue.enqueue(1);
        assertFalse(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 1);
        randomizedQueue.enqueue(2);
        assertFalse(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 2);
    }
    
    @Test(expected = NullPointerException.class)
    public void enqueueBasicExceptionTest() {
        randomizedQueue.enqueue(null);
        assertTrue(randomizedQueue.isEmpty());
    }
    
    @Test
    public void dequeueBasicTest() {
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        assertFalse(randomizedQueue.isEmpty());
        System.out.println(randomizedQueue.dequeue());
        assertFalse(randomizedQueue.isEmpty());
        System.out.println(randomizedQueue.dequeue());
        assertFalse(randomizedQueue.isEmpty());
        System.out.println(randomizedQueue.dequeue());
        assertTrue(randomizedQueue.isEmpty());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void dequeueExceptionTest() {
        randomizedQueue.dequeue();
        assertTrue(randomizedQueue.isEmpty());
    }
    
    @Test
    public void sampleBasicTest() {
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        assertFalse(randomizedQueue.isEmpty());
        System.out.println("sample " + randomizedQueue.sample());
        assertFalse(randomizedQueue.isEmpty());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void sampleExceptionTest() {
        randomizedQueue.sample();
        assertTrue(randomizedQueue.isEmpty());
    }
    
    @Test
    public void nonEmptyEmptyTest() {
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        assertFalse(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 3);
        
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        assertTrue(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 0);
        
        randomizedQueue.enqueue(1);
        assertFalse(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 1);
        
        randomizedQueue.dequeue();
        assertTrue(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 0);
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void iteratorRemoveExceptionTest() {
        randomizedQueue.iterator().remove();
        assertTrue(randomizedQueue.isEmpty());
    }
    
    @Test
    public void iteratorHasNextTest() {
        randomizedQueue.enqueue(1);
        Iterator<Integer> itr = randomizedQueue.iterator();
        assertTrue(itr.hasNext());
        randomizedQueue.dequeue();
        itr = randomizedQueue.iterator();
        assertFalse(itr.hasNext());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void iteratorNextExceptionTest() {
        Iterator<Integer> itr = randomizedQueue.iterator();
        assertFalse(itr.hasNext());
        itr.next();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void iteratorNextTest() {
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        Iterator<Integer> itr = randomizedQueue.iterator();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }
    
    @Test
    public void iteratorHasNextNextTest() {
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        randomizedQueue.enqueue(5);
        Iterator<Integer> itr = randomizedQueue.iterator();
        int i = 1;
        while(itr.hasNext()) {
            assertTrue(itr.hasNext());
            itr.next();
        }
        assertFalse(itr.hasNext());
    }
}
