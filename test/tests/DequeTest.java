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
import tasks.Deque;

/**
 *
 * @author milos
 */
public class DequeTest {
    
    private Deque<Integer> deque;
    
    public DequeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        deque = new Deque<>();
    }
    
    @After
    public void tearDown() {
        deque = null;
    }
    
    @Test
    public void testAddFirstBasic() {
        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
        deque.addFirst(1);
        assertTrue(deque.size() == 1);
        assertFalse(deque.isEmpty());
        deque.addFirst(2);
        assertTrue(deque.size() == 2);
        assertFalse(deque.isEmpty());
    }
    
    @Test(expected = NullPointerException.class)
    public void testAddFirstException() {
        deque.addFirst(null);
        assertTrue(deque.isEmpty());
    }
    
    @Test
    public void testAddLastBasic() {
        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
        deque.addLast(1);
        assertTrue(deque.size() == 1);
        assertFalse(deque.isEmpty());
        deque.addLast(2);
        assertTrue(deque.size() == 2);
        assertFalse(deque.isEmpty());
    }
    
    @Test(expected = NullPointerException.class)
    public void testAddLastException() {
        deque.addLast(null);
        assertTrue(deque.isEmpty());
    }
    
    @Test
    public void testRemoveFirstBasic() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        assertFalse(deque.isEmpty());
        assertTrue(deque.removeFirst() == 3);
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() == 2);
        assertTrue(deque.removeFirst() == 2);
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() == 1);
        assertTrue(deque.removeFirst() == 1);
        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
        
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstException() {
        deque.removeFirst();
        assertTrue(deque.isEmpty());
    }
    
    @Test
    public void testRemoveLastBasic() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertFalse(deque.isEmpty());
        assertTrue(deque.removeLast() == 3);
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() == 2);
        assertTrue(deque.removeLast() == 2);
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() == 1);
        assertTrue(deque.removeLast() == 1);
        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
        
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastException() {
        deque.removeLast();
        assertTrue(deque.isEmpty());
    }
    
    @Test
    public void testAddFirstRemoveLast() {
        for(int i=0; i<10; i++) {
            deque.addFirst(i);
        }
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() == 10);
        for(int j=0; j<10; j++) {
            assertTrue(deque.removeLast() == j);
        }
        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
    }
    
    @Test
    public void testAddLastRemoveFirst() {
        for(int i=0; i<10; i++) {
            deque.addLast(i);
        }
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() == 10);
        for(int j=0; j<10; j++) {
            assertTrue(deque.removeFirst() == j);
        }
        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
    }
    
    @Test
    public void testNonEmptyEmpty() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() == 3);
        
        assertTrue(deque.removeFirst() == 1);
        assertTrue(deque.removeFirst() == 2);
        assertTrue(deque.removeFirst() == 3);
        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
        
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() == 1);
        
        assertTrue(deque.removeLast() == 1);
        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void iteratorRemoveExceptionTest() {
        deque.iterator().remove();
        assertTrue(deque.isEmpty());
    }
    
    @Test
    public void iteratorHasNextTest() {
        deque.addFirst(1);
        Iterator<Integer> itr = deque.iterator();
        assertTrue(itr.hasNext());
        deque.removeLast();
        itr = deque.iterator();
        assertFalse(itr.hasNext());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void iteratorNextExceptionTest() {
        Iterator<Integer> itr = deque.iterator();
        assertFalse(itr.hasNext());
        itr.next();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void iteratorNextTest() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        Iterator<Integer> itr = deque.iterator();
        assertTrue(itr.next() == 3);
        assertTrue(itr.next() == 2);
        assertTrue(itr.next() == 1);
        itr.next();
    }
    
    @Test
    public void iteratorHasNextNextTest() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        Iterator<Integer> itr = deque.iterator();
        int i = 1;
        while(itr.hasNext()) {
            assertTrue(itr.next() == i++);
        }
    }
}
