package com.team10.lifeorientedlist;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Used to put the data from a Reward into a Recycler
 *
 * @author Michael Hegerhorst
 * @since 3/18/2019
 * @version 1.0
 *
 * @see Reward
 * @see android.support.v7.widget.RecyclerView.Recycler
 */
public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.RecyclerViewHolder> {
    private List<Reward> _dataset;

    public RewardAdapter (List<Reward> _dataset) {
        this._dataset = _dataset;
    }


    @Override
    public RewardAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        viewHolder.setReward(view, _dataset.get(i));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        Reward reward = _dataset.get(i);

        recyclerViewHolder._rewardName.setText(reward.getName());
        recyclerViewHolder._timeCreated.setText(reward.getTimeCreated());
        recyclerViewHolder._points.setText(Integer.toString(reward.getCost()));
    }

    @Override
    public int getItemCount() {
        return _dataset.size();
    }


    public void clear() {
        int size = _dataset.size();
        _dataset.clear();
        notifyItemRangeRemoved(0, size);
    }





    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView _rewardName;
        public TextView _timeCreated;
        public TextView _points;
        private Reward reward;

        public RecyclerViewHolder(View v) {
            super(v);

            _rewardName = v.findViewById(R.id.recyclerTaskName);
            _timeCreated = v.findViewById(R.id.recyclerTimeCreated);
            _points = v.findViewById(R.id.recyclerPoints);

            ((TextView)v.findViewById(R.id.recyclerTimeDue)).setText("");

            final View doneButton = v.findViewById(R.id.BUTTON_recyclerDone);
            doneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reward.setClaimed(!(reward.isClaimed()));
                    if (reward.isClaimed()) {
                        ManageData mData = new ManageData(v.getContext());
                        User user = mData.getUser();
                        int cost = reward.getCost() * -1;
                        user.addCurrentPoints(cost);
                        mData.storeData(user);
                    }
                    setCheckmarkColors(v, reward.isClaimed());
                }
            });

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MainActivity.fragment = new RewardOverviewFragment();

            Bundle variables = new Bundle();
            variables.putString("name", (String)_rewardName.getText());
            variables.putString("timeCreated", (String)_timeCreated.getText());
            variables.putString("points", (String)_points.getText());

            MainActivity.fragment.setArguments(variables);

            FragmentTransaction ft = ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, MainActivity.fragment);
            ft.commit();
        }

        private void setReward(View view, Reward reward) {
            this.reward = reward;
            setCheckmarkColors(view.findViewById(R.id.BUTTON_recyclerDone), reward.isClaimed());
        }

        /**
         * Switches a View's background between red_checkmark and green_checkmark.
         *
         * @param v The View with the checkmark
         */
        private void setCheckmarkColors(View v, boolean done) {
            if (done)
                v.setBackground(v.getResources().getDrawable(R.drawable.green_checkmark));
            else
                v.setBackground(v.getResources().getDrawable(R.drawable.red_checkmark));
        }
    }


}
