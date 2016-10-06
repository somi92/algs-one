/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author milos
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int n;
    
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if(item == null)
            throw new NullPointerException();
        if(n == queue.length)
            resize(2 * queue.length);
        queue[n++] = item;
    }

    public Item dequeue() {
        if(isEmpty())
            throw new NoSuchElementException();
        int index = StdRandom.uniform(n);
        Item item = queue[index];
        queue[index] = queue[--n];
        queue[n] = null;
        if(n > 0 && n == queue.length/4)
            resize(queue.length/2);
        return item;
    }

    public Item sample() {
        if(isEmpty())
            throw new NoSuchElementException();
        int index = StdRandom.uniform(n);
        return queue[index];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {

    }
    
    private void resize(int capacity) {
        Item[] newQueue = (Item[]) new Object[capacity];
        for(int i = 0; i < n; i++)
            newQueue[i] = queue[i];
        queue = newQueue;
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        
        private int[] randomIndexes = new int[n];
        private int current = 0;
        
        public RandomizedQueueIterator() {
            for(int i=0; i<randomIndexes.length; i++) {
                randomIndexes[i] = i;
            }
            StdRandom.shuffle(randomIndexes);
        }
        
        @Override
        public boolean hasNext() {
            return current < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = queue[randomIndexes[current++]];
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
