package com.team10.lifeorientedlist;

/**
 * Used to set a Reward for the user to buy
 *
 * @author Michael Hegerhorst
 * @version 1.0
 * @since 3/19/2019
 */
public class Reward {
    private int cost;
    private String name;
    private boolean claimed = false;
    private String timeCreated;

    /**
     * Advanced constructor. Sets the name and cost and time created.
     *
     * @param name The name of the Reward
     * @param cost The point cost of the Reward.
     */
    public Reward(String name, int cost, String timeCreated) { this.name = name; this.cost = cost; this.timeCreated = timeCreated; }

    /**
     *
     * @return cost
     */
    public int getCost() { return cost; }
    /**
     *
     * @return name
     */
    public String getName() { return name; }
    /**
     *
     * @return claimed
     */
    public boolean isClaimed() { return claimed; }
    /**
     *
     * @return timeCreated
     */
    public String getTimeCreated() { return timeCreated; }

    /**
     *
     * @param cost Sets the cost
     */
    public void setCost(int cost) { this.cost = cost; }
    /**
     *
     * @param name Sets the name
     */
    public void setName(String name) { this.name = name; }
    /**
     *
     * @param claimed Sets the claimed
     */
    public void setClaimed(boolean claimed) { this.claimed = claimed; }
    /**
     *
     * @param timeCreated Sets time created
     */
    public void setTimeCreated(String timeCreated) { this.timeCreated = timeCreated; }
}
