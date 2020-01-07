package com.team10.lifeorientedlist;

import android.content.Context;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SaveGoalLocallyTest {

    private static final String NAME = "Test NAME";
    private static final int    POINTS = 100;
    private static final String CREATION_TIME = "Now";
    private static final String DUE_TIME = "Later";
    private static final boolean DONE = true;

    private static List<Task> testTaskList;

    public SaveGoalLocallyTest() {}

    @BeforeClass
    public static void loadTaskForTesting() {
        Task saveTask = new Goal();

        saveTask.setName(NAME);
        saveTask.setPoints(POINTS);
        saveTask.setTimeCreated(CREATION_TIME);
        saveTask.setTimeDue(DUE_TIME);
        saveTask.setDone(DONE);

        User user = new User("Test","test", "TEST");
        List<Task> testList = new ArrayList<>();
        testList.add(saveTask);

        user.setGoalList(testList);

        Context appContext = InstrumentationRegistry.getTargetContext();
        ManageData manageData = new ManageData(appContext);
        manageData.storeData(user);
        User testUser = manageData.getUser();

        testTaskList = testUser.getGoalList();
    }

    @Test
    public void nameShouldBeTheSame() { assertEquals(NAME, testTaskList.get(0).getName()); }

    @Test
    public void pointsShouldBeTheSame() {
        assertEquals(POINTS, testTaskList.get(0).getPoints());
    }

    @Test
    public void creationTimeShouldBeTheSame() { assertEquals(CREATION_TIME, testTaskList.get(0).getTimeCreated()); }

    @Test
    public void dueTimeShouldBeTheSame() {
        assertEquals(DUE_TIME, testTaskList.get(0).getTimeDue());
    }

    @Test
    public void doneShouldBeTheSame() {
        assertEquals(DONE, testTaskList.get(0).isDone());
    }
}
