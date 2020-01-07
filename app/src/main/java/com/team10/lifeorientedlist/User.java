package com.team10.lifeorientedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * User Object
 * <p> Various attributes of User</p>
 * @author Sheena Jessee and Micheal Hegerhorst
 * @version 1.0
 * @since 3/19/2019
 *
 */
public class User {
    private String name;
    private String email;
    private List<Goal> goals = new ArrayList<>();
    private List<Todo> toDos = new ArrayList<>();
    private List<Reward> rewards = new ArrayList<>();
    private int currentPoints = 0;
    private int lifetimePoints = 0;
/**
 * Constructor
 * @param name name of the user.
 * @param email users email address.
 * */
    User (String name, String email) {
        this.name = name;
        this.email = email;

    }

    /**
     * Get User's Name
     * @return a string representing the user's name.
     */
    public String getName() {return name;}

    /**
     * Get The current points a User has
     * @return an integer representing the current point value.
     */

    public int getCurrentPoints() { return currentPoints; }

    /**
     *  Gets the total points a user has accumulated throughout use of the app.
     * @return an integer representing the total point value.
     */
    public int getLifetimePoints() { return lifetimePoints; }

    /**
     * Get the list of Goals
     * @return a list containing the users goals.
     */
    public List getGoalList() { return goals;}
    /**
     * Get the list of To Do items
     * @return a list containing the users to do items.
     */
    public List getToDoList() { return toDos;}
    /**
     * Get the list of the rewards for each goal or to do
     * @return a list containing the users rewards for each goal or to do.
     */
    public List getRewardList() { return rewards;}

    /**
     * Get the user email address
     * @return a string representing the users email.
     */
    public String getEmail () { return email;}

    /**
     * Set the User's name
     * @param name String representing the users name.
     */
    public void setName(String name) {this.name = name;}

    /**
     * Set the User's email
     * @param email string representing the user's email address.
     */
    public void setEmail(String email) {this.email = email;}

    /**
     * Set a Goal
     * @param name string representing the name of the goal.
     * @param points int representing the goals point value.
     * @param creation_time string that represents the goals time of creation.
     * @param due_time string representing the time a goal is due.
     * @param done boolean that indicates if a goal is finished or not.
     */
    public void setGoal(String name, int points, String creation_time, String due_time, boolean done) {
        TaskFactory taskFactory = new TaskFactory();
        //taskFactory.setTask("GOAL", name, points, creation_time, due_time, done);
        goals.add((Goal)taskFactory.getTask("GOAL", creation_time, due_time, name, points, done));

    }

    /**
     * Set a To Do
     * @param name string repreenting the name of a To Do item.
     * @param points int representing the To Do point value.
     * @param creation_time string that represents the to do time of creation.
     * @param due_time string representing the time a to do is due.
     * @param done boolean that indicates if a to do is finished or not.
     */
    public void setToDo(String name, int points, String creation_time, String due_time, boolean done) {
        TaskFactory taskFactory = new TaskFactory();
        //taskFactory.setTask("TODO", name, points, creation_time, due_time, done);
        toDos.add((Todo)taskFactory.getTask("TODO", creation_time, due_time, name, points, done));

    }

    /**
     * Set the Current Point Value.
     * @param points int representing the value of points.
     */
    public void setCurrentPoints(int points) { currentPoints = points; }
    /**
     * Adds to the Current Point Value.
     * @param points int representing the value of points.
     */
    public void addCurrentPoints(int points) { if (currentPoints + points >= 0) currentPoints += points; if (points > 0) lifetimePoints += points; }

    /**
     * Set the User's Lifetime Point Balance.
     * @param points int that represents the total lifetime points of User.
     */
    public void setLifetimePoints(int points) { lifetimePoints = points; }

    /**
     * Sets the To Do list
     * @param todos list that will contain the collective to do's of user.
     */
    public void setToDoList(List<Todo> todos) { //Michael Hegerhorst
        this.toDos = todos;
    }

    /**
     * Sets the Goal list
     * @param goals list that will contain the collective goals of user.
     */
    public void setGoalList(List<Goal> goals) { //Michael Hegerhorst
        this.goals = goals;
    }

    /**
     * Sets the  Reward list
     * @param rewards list that will contain the collective rewards for each to do and goal.
     */
    public void setRewardList(List<Reward> rewards) { //Michael Hegerhorst
        this.rewards = rewards;
    }

    /**
     * Sets The Reward
     * @param name string represents name of the reward
     * @param cost int that represents the cost of the reward.
     */
    public void setReward(String name, int cost, String timeCreated) { Reward reward = new Reward(name, cost, timeCreated); rewards.add(reward); }



    public int indexOfTask(Task task) {

        if (task.getClass() == Goal.class) {
            for (Goal goal : goals) {
                if (task.getName().equals(goal.getName())
                        && task.getTimeCreated().equals(goal.getTimeCreated())
                        && task.getTimeDue().equals(goal.getTimeDue())
                        && task.getPoints() == goal.getPoints()
                ) {
                    return goals.indexOf(goal);
                }
            }
        }
        else if (task.getClass() == Todo.class) {
            for (Todo todo : toDos)
            if (task.getName().equals(todo.getName())
                    && task.getTimeCreated().equals(todo.getTimeCreated())
                    && task.getTimeDue().equals(todo.getTimeDue())
                    && task.getPoints() == todo.getPoints()
            ) {
                return toDos.indexOf(todo);
            }
        }

        return -1;
    }

}
