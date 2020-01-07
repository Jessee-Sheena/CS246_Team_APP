package com.team10.lifeorientedlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Reward view
 * <p>Sets the recycler view for the reward list</p>
 * @author Sheena Jessee
 * @version 1.0
 */
public class RewardView extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reward_view, container, false);

        RecyclerView rewardRecycler = (RecyclerView) view.findViewById(R.id.reward_view_id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        rewardRecycler.setLayoutManager(layoutManager);

        User user = new ManageData(this.getContext()).getUser();

        RewardAdapter goalAdapter = new RewardAdapter(user.getRewardList());
        rewardRecycler.setAdapter(goalAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Reward View");
    }
}
