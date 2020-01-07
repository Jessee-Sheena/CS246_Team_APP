package com.team10.lifeorientedlist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import javax.annotation.Nullable;
import static android.content.Context.ALARM_SERVICE;

/**
 * Allows the User to set daily notifications as their desired times.
 *
 * @author Cameron Chappell
 * @version 1.0
 * @since 4/6/2019
 *
 */
public class NotificationFragment extends Fragment {
    EditText name;
    EditText hour;
    EditText minute;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, @android.support.annotation.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Create Notification");

        hour = view.findViewById(R.id.hourInput);
        minute = view.findViewById(R.id.minuteInput);

        View createButton = view.findViewById(R.id.submit_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationFragment(view);
            }
        });

        View cancelButton = view.findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragment = new MainContent();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, MainActivity.fragment);
                ft.commit();
            }
        });
    }

    /**
     * Sets up the notification using the Calendar and AlarmManager classes.
     */
    private void notificationFragment(View view) {
        Calendar calendar = Calendar.getInstance();

        String hourInput = hour.getText().toString();
        int hourValue = Integer.parseInt(hourInput);

        String minuteInput = minute.getText().toString();
        int minuteValue = Integer.parseInt(minuteInput);

        calendar.set(Calendar.HOUR_OF_DAY, hourValue);
        calendar.set(Calendar.MINUTE, minuteValue);

        Intent intent = new Intent(getContext(), NotificationReceiver.class);
        intent.setAction("MY_NOTIFICATION_MESSAGE");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(getActivity(), "Daily reminder created!", Toast.LENGTH_SHORT).show();
    }
}