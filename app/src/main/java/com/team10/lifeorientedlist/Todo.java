package com.team10.lifeorientedlist;

/**
 * Implements a Todo using Task
 *
 * @author Michael Hegerhorst
 * @version 1.0
 * @since 3/1/2019
 *
 * @see Task
 */
public class Todo extends Task {

    /**
     * Uses the Tasks's constructor
     */
    Todo() { super(); }
    /**
     * Uses the Tasks constructor
     *
     * @param timeCreated The time the Task was created
     * @param timeDue The time the Task is due
     * @param name The name of the Task
     * @param points How many points the Task is worth
     *
     * @see Todo
     * @see Task
     */
    Todo(String timeCreated, String timeDue, String name, int points) { super(timeCreated, timeDue, name, points); }
    /**
     * Uses the Tasks constructor
     *
     * @param timeCreated The time the Task was created
     * @param timeDue The time the Task is due
     * @param name The name of the Task
     * @param points How many points the Task is worth
     * @param done Whether or not the Task is done
     *
     * @see Todo
     * @see Task
     */
    Todo(String timeCreated, String timeDue, String name, int points, boolean done) { super(timeCreated, timeDue, name, points, done); }
}
