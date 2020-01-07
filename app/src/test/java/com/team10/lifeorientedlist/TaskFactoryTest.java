package com.team10.lifeorientedlist;

import org.junit.Test;

import static org.junit.Assert.*;

import com.team10.lifeorientedlist.TaskFactory;

public class TaskFactoryTest {

    private final String NAME = "Test NAME";
    private final int    POINTS = 100;
    private final String CREATION_TIME = "Now";
    private final String DUE_TIME = "Later";
    private final boolean DONE = true;

    private final String GOAL_INFO = NAME + " " + DUE_TIME + " " + DONE;
    private final String TODO_INFO = NAME + " " + DUE_TIME + " " + DONE + " " + POINTS;

    @Test
    public void goalGetInfoShouldBeCorrect()
    {
        TaskFactory taskFactory = new TaskFactory();

        Task testGoal = taskFactory.getTask("GOAL", CREATION_TIME, DUE_TIME, NAME, POINTS,  DONE);

        String testString = testGoal.getName() + " " + testGoal.getTimeDue() + " " + testGoal.isDone();

        assertEquals(testString, GOAL_INFO);
    }

    @Test
    public void todoGetInfoShouldBeCorrect()
    {
        TaskFactory taskFactory = new TaskFactory();

        Task testGoal = taskFactory.getTask("TODO", CREATION_TIME, DUE_TIME, NAME, POINTS,  DONE);

        String testString = testGoal.getName() + " " + testGoal.getTimeDue() + " " + testGoal.isDone() + " " + testGoal.getPoints();

        assertEquals(testString, TODO_INFO);
    }

}
