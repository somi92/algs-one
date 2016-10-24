/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author milos
 */
public class BoardTest {
    
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void hammingOkTest() {
        Board b = new Board(generate8Matrix());
        assertTrue(b.hamming() == 5);
    }
    
    @Test
    public void manhattanOkTest() {
        Board b = new Board(generate8Matrix());
        assertTrue(b.manhattan() == 10);
    }
    
    @Test
    public void isGoalOkTest() {
        Board initial = new Board(generate8Matrix());
        assertFalse(initial.isGoal());
        Board goal = new Board(generate8GoalMatrix());
        assertTrue(goal.isGoal());
    }
    
    @Test
    public void equalsTest() {
        Board initial1 = new Board(generate8Matrix());
        Board initial2 = new Board(generate8Matrix());
        Board goal1 = new Board(generate8GoalMatrix());
        Board goal2 = new Board(generate8GoalMatrix());
        assertTrue(initial1.equals(initial1));
        assertTrue(goal1.equals(goal1));
        assertTrue(initial1.equals(initial2));
        assertTrue(goal1.equals(goal2));
        assertFalse(initial1.equals(goal1));
        assertFalse(goal1.equals(initial2));
    }
    
    @Test
    public void twinOkTest() {
        
    }
    
    private int[][] generate8GoalMatrix() {
        int[][] matrix = new int[3][3];
        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[0][2] = 3;
        matrix[1][0] = 4;
        matrix[1][1] = 5;
        matrix[1][2] = 6;
        matrix[2][0] = 7;
        matrix[2][1] = 8;
        matrix[2][2] = 0;
        return matrix;
    }
    
    private int[][] generate8Matrix() {
        int[][] matrix = new int[3][3];
        matrix[0][0] = 8;
        matrix[0][1] = 1;
        matrix[0][2] = 3;
        matrix[1][0] = 4;
        matrix[1][1] = 0;
        matrix[1][2] = 2;
        matrix[2][0] = 7;
        matrix[2][1] = 6;
        matrix[2][2] = 5;
        return matrix;
    }
}
