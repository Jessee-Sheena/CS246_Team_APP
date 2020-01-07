package com.team10.lifeorientedlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;

import java.util.Map;

import static com.team10.lifeorientedlist.MainActivity.getContext;

/**
 * Class that is ready to be set up to the firestore database in future development
 *
 */

public class UpdateDatabase {
    private String TAG = "UPDATE_DATABASE";


    FirebaseFirestore db;
    FirebaseAuth auth;
    UpdateDatabase() {
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }

    /**
     * Inserts users goals to the database
     * @param goal a string that contains all the goals
     * @param todos a string that contains all the todos
     * @param reward a string that contains all the rewards
     */
    public void insertGoal(String goal, String todos, String reward) {
        long millis=System.currentTimeMillis();
        Map<String, Object> data = new HashMap<>();
        data.put("goals", goal);
        data.put("todos", todos);
        data.put("rewards", reward);
        data.put("date", millis);


        if (auth.getCurrentUser() == null) { //If the user does not exist, return and leave a Toast
            /*Toast.makeText(getContext(), //The View needs to be passed in, otherwise getContext() is null
                    TAG + "Error connecting to database: User is NULL!", Toast.LENGTH_SHORT).show();*/
            return;
        }


        db.collection("user").document(auth.getCurrentUser().getUid()
        ).collection("tasks").document("goals")
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }
}
