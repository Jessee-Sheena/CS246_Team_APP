package com.team10.lifeorientedlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.team10.lifeorientedlist.MainActivity.getContext;

/**
* Manage Data
 * <p> Manages all the data that needs to be stored in shared preferences</p>
 * @author Sheena Jessee, Micheal Hegerhorst, Cameron Chappell
 * @version 1.0
*/
public class ManageData {
    private static final String USER_DETAILS = "userDetails";
    private SharedPreferences userInfo;

    /**
     * Constructor
     * @param context passes the context of the activity calling the class
     */
    ManageData(Context context) {
          userInfo = context.getSharedPreferences(USER_DETAILS, 0);
    }

    private final String TAG = "ManageData";

    /**
     * Stores the user
     * <P>stores the user into shared preferences</P>
     * @param  user represents current user
     */

    void storeUser(User user){
        SharedPreferences.Editor  editData = userInfo.edit();
        editData.putString("name", user.getName());
        editData.putString("email", user.getEmail());
        editData.commit();
    }

    /**
     * Stores the users data
     * <P>stores the goals, to do's and rewards associated with the user</p>
     * @param user represents the current user
     */
    void storeData(final User user) {
        //get the list into json to place into shared preferences
        Log.d(TAG, "Started storeData()");

    /**
    * Saves the tasks on the background thread.
    */
        class backgroundSave extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {
                Gson gson = new Gson();
                String jsonG = gson.toJson(user.getGoalList());
                String jsonT = gson.toJson(user.getToDoList());
                String jsonR = gson.toJson(user.getRewardList());

                // set the shared preferences
                SharedPreferences.Editor editData = userInfo.edit();

                if (jsonG != null) {
                    editData.putString("goals", jsonG);
                }
                if (jsonT != null) {
                    editData.putString("toDo", jsonT);
                }
                editData.putString("goals", jsonG);  // make sure to put JSON to a string in user class
                editData.putString("toDos", jsonT);
                editData.putString("rewards", jsonR);
                editData.putInt("currentPoints", user.getCurrentPoints());
                editData.putInt("lifetimePoints", user.getLifetimePoints());

                editData.commit();

                return "Task created!";
            }

            @Override
            protected void onPostExecute(String result) {
                Context context = getContext();
                if (context != null) {
                    Toast.makeText(context, "Task created!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        new backgroundSave().execute();

        Log.d(TAG, "Ended storeData()");
    }

    /**
     * Get the current user
     * <p>retrieves the current user and all their associated data</p>
     * @return the current user that is stored in shared preferences
     */
    public User getUser() {
        Log.d(TAG, "Started getUser()");

        String name = userInfo.getString("name", "");
        String email = userInfo.getString("email", "");


        User currentUser = new User(name, email);
        String strGoal = userInfo.getString("goals", "");
        String strToDo = userInfo.getString("toDos", "");
        String strReward = userInfo.getString("rewards", "");

        // RETRIEVING DATA AS JSON TO BE SENT TO THE SETlIST METHODS TO BE INSERTED INTO LISTS

        //Michael Hegerhorst's code
        Gson gson = new Gson();
        List<Goal> goals = gson.fromJson(strGoal, new TypeToken<List<Goal>>(){}.getType());
        List<Todo> todos = gson.fromJson(strToDo, new TypeToken<List<Todo>>(){}.getType());
        List<Reward> rewards = gson.fromJson(strReward, new TypeToken<List<Reward>>(){}.getType());


        if (goals == null)
            goals = new ArrayList<Goal>();

        if (todos == null)
            todos = new ArrayList<Todo>();

        if (rewards == null)
            rewards = new ArrayList<>();

        currentUser.setGoalList(goals);
        currentUser.setToDoList(todos);
        currentUser.setRewardList(rewards);
        currentUser.setCurrentPoints(userInfo.getInt("currentPoints", 0));
        currentUser.setLifetimePoints(userInfo.getInt("lifetimePoints", 0));

        Log.d(TAG, "Finished loading");
        //End Michael's code


        return currentUser;
    }

    /**
     * Set if User is logged in
     * <p> a boolean is set when a user is logged in</p>
     * @param isloggedIn represents if user is logged in or not
     */
    public void setLoggedIn(boolean isloggedIn) {
        SharedPreferences.Editor  editData = userInfo.edit();
        editData.putBoolean("loggedIn", isloggedIn);
        editData.commit();
    }

    /**
     * Is the User logged in
     * <p> gets the boolean if the user is logged in or not</p>
     * @return returns true or false
     */
    public boolean isLoggedIn () {
        if(userInfo.getBoolean("loggedIn" , false) == true) {
            return true;
        }else
            return false;
    }

    /**
     * Sets User ID
     * @param id the database id for current user
     */
    public void setUserID(String id) {
        SharedPreferences.Editor  editData = userInfo.edit();
        editData.putString("ID", id);
        editData.commit();
    }

    /**
     * retrieves the current users id
     */
    public String getUserID() {
        String id = userInfo.getString("ID", "");
        return id;
    }
    public void setUserColors(int color1, int color2, int color3) {
        SharedPreferences.Editor editData = userInfo.edit();
        editData.putInt("primaryColor", color1);
        editData.putInt("secondaryColor", color2);
        editData.putInt("accentColor", color3);
        editData.commit();
    }
    public int getPrimaryColor() {

        if(userInfo.getInt("primaryColor", 0) != 0) {
            int color = userInfo.getInt("primaryColor", 0);
            return color;
        }else {
            return 0;
        }

    }
    public int getSecondaryColor() {

        if(userInfo.contains("secondaryColor")) {
            int color = userInfo.getInt("secondaryColor", 0);
            return color;
        }else {
            return 0;
        }

    }
    public int getAccentColor() {

        if(userInfo.contains("accentColor")) {
            int color = userInfo.getInt("accentColor", 0);
            return color;
        }else {
            return 0;
        }

    }



    /**Clears the data from shared preferences*/
    public void clearData() {
        SharedPreferences.Editor  editData = userInfo.edit();
        editData.clear();
    }
}
