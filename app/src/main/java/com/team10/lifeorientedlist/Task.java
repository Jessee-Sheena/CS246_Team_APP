package com.team10.lifeorientedlist;

/**
 * Abstract class that holds the information for a task.
 *
 * @author Michael Hegerhorst
 * @version 1.0
 * @since 3/1/2019
 *
 * @see Goal
 * @see Todo
 * @see TaskFactory
 */
public abstract class Task {

    protected String timeCreated;
    protected String timeDue;
    protected String name;
    protected boolean done = false;
    protected int points;

    /**
     * Basic constructor for a Task. Nothing is initialized
     *
     * @see Task
     */
    Task() {}

    /**
     * Advanced constructor for a Task. Initializes everything according to the
     * given parameters and sets done to false.
     *
     * @param timeCreated The time the Task was created
     * @param timeDue The time the Task is due
     * @param name The name of the Task
     * @param points How many points the Task is worth
     *
     * @see Task
     */
    Task(String timeCreated, String timeDue, String name, int points)
    {
        this.timeCreated = timeCreated;
        this.timeDue = timeDue;
        this.name = name;
        this.points = points;
    }

    /**
     * Advanced constructor for a Task. Initializes everything according to the
     * given parameters.
     *
     * @param timeCreated The time the Task was created
     * @param timeDue The time the Task is due
     * @param name The name of the Task
     * @param points How many points the Task is worth
     * @param done Whether or not the Task is done
     *
     * @see Task
     */
    Task(String timeCreated, String timeDue, String name, int points, boolean done)
    {
        this.timeCreated = timeCreated;
        this.timeDue = timeDue;
        this.name = name;
        this.points = points;
        this.done = done;
    }

    /**
     *
     * @return timeCreated
     */
    public String getTimeCreated() { return timeCreated; }
    /**
     *
     * @return timeDue
     */
    public String getTimeDue() { return timeDue; }
    /**
     *
     * @return timeName
     */
    public String getName() { return name; }
    /**
     *
     * @return done
     */
    public boolean isDone() { return done; }
    /**
     *
     * @return points
     */
    public int getPoints() { return points; }

    /**
     *
     * @param timeCreated Sets time created
     */
    public void setTimeCreated(String timeCreated) { this.timeCreated = timeCreated; }

    /**
     *
     * @param timeDue Sets time due
     */
    public void setTimeDue(String timeDue) { this.timeDue = timeDue; }
    /**
     *
     * @param name Sets name
     */
    public void setName(String name) { this.name = name; }
    /**
     *
     * @param points Sets points
     */
    public void setPoints(int points) { this.points = points; }
    /**
     *
     * @param done Sets done
     */
    public void setDone(boolean done) { this.done = done; }
}
