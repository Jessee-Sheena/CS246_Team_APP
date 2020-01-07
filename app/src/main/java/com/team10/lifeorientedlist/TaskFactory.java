package com.team10.lifeorientedlist;

/**
 * Creates a Goal object or a Todo object
 *
 * @author Cameron Chappell
 * @version 1.0
 * @since 3/6/2019
 *
 * @see Task
 */
public class TaskFactory {

    /**
     * Determines the type of the Task
     *
     * @see Goal
     * @see Todo
     * @see Task
     */
    public static Task getTask(String type) {

        if(type == null) {
            return null;
        }
        if("Goal".equalsIgnoreCase(type)) {
            return new Goal();
        }
        else if("Todo".equalsIgnoreCase(type)) {
            return new Todo();
        }
        return null;
    }

    /**
     * Uses the Task constructor
     *
     * @param type The type of Task that was created
     * @param timeCreated The time the Task was created
     * @param timeDue The time the Task is due
     * @param name The name of the Task
     * @param points How many points the Task is worth
     * @param done Determines if a Task has been completed
     *
     * @see Goal
     * @see Todo
     * @see Task
     */
    public static Task getTask(String type, String timeCreated, String timeDue, String name, int points, boolean done) {

        if(type == null) {
            return null;
        }
        if("Goal".equalsIgnoreCase(type)) {
            return new Goal(timeCreated, timeDue, name, points, done);
        }
        else if("Todo".equalsIgnoreCase(type)) {
            return new Todo(timeCreated, timeDue, name, points, done);
        }
        return null;
    }

    /**
     * Uses the Task constructor
     *
     * @param type The type of Task that was created
     * @param timeCreated The time the Task was created
     * @param timeDue The time the Task is due
     * @param name The name of the Task
     * @param points How many points the Task is worth
     *
     * @see Goal
     * @see Todo
     * @see Task
     */
    public static Task getTask(String type, String timeCreated, String timeDue, String name, int points) {

        if(type == null) {
            return null;
        }
        if("Goal".equalsIgnoreCase(type)) {
            return new Goal(timeCreated, timeDue, name, points);
        }
        else if("Todo".equalsIgnoreCase(type)) {
            return new Todo(timeCreated, timeDue, name, points);
        }
        return null;
    }
}