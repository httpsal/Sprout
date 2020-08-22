package com.hcifedii.sprout.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.transition.MaterialContainerTransform;
import com.hcifedii.sprout.HabitType;
import com.hcifedii.sprout.R;
import com.hcifedii.sprout.adapter.PresetHabitAdapter;

import java.util.LinkedList;
import java.util.List;

import model.Habit;


public class PresetFragment extends Fragment {

    Context context;
    PresetHabitAdapter adapter;

    public PresetFragment() {
    }

    public void setAdapterListener(@NonNull PresetHabitAdapter.OnClickListener listener) {
        this.adapter.setListener(listener);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_preset_list, container, false);

        context = view.getContext();

        RecyclerView presetHabitRecyclerView = view.findViewById(R.id.presetHabitRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        presetHabitRecyclerView.setLayoutManager(linearLayoutManager);

        // Set the listener to the recycler view
        adapter = new PresetHabitAdapter(createPresetHabitList());

        presetHabitRecyclerView.setAdapter(adapter);

        // Set the listener to the button
        MaterialButton showMore = view.findViewById(R.id.showMoreButton);

        showMore.setOnClickListener(new ShowMoreButtonListener(view));

        return view;
    }

    // Implementation of the listeners
    private static class ShowMoreButtonListener implements View.OnClickListener {

        View view;

        ShowMoreButtonListener(View view) {
            this.view = view;
        }

        @Override
        public void onClick(View v) {
            // Material 1.2.0 required
            ViewGroup endView = view.findViewById(R.id.bottomContainer);

            MaterialContainerTransform transform = new MaterialContainerTransform();

            LinearLayout container = view.findViewById(R.id.groupContainer);

            transform.setScrimColor(Color.TRANSPARENT);
            TransitionManager.beginDelayedTransition(container);

            if (endView.getVisibility() == View.GONE) {
                endView.setVisibility(View.VISIBLE);
            } else {
                endView.setVisibility(View.GONE);
            }
        }
    }

    private List<Habit> createPresetHabitList() {

        List<Habit> list = new LinkedList<>();

        Habit habit1 = new Habit(), habit2 = new Habit(), habit3 = new Habit(), habit4 = new Habit();

        habit1.setTitle(getString(R.string.preset_drink_more_water));
        habit1.setHabitType(HabitType.COUNTER);
        habit1.setRepetitions(8);
        habit1.setImage(R.drawable.ic_round_local_drink_24);

        habit2.setTitle(getString(R.string.preset_do_more_sports));
        habit2.setHabitType(HabitType.CLASSIC);
        habit2.setImage(R.drawable.ic_sports_tennis_24);

        habit3.setTitle(getString(R.string.preset_go_to_bed_early));
        habit3.setHabitType(HabitType.CLASSIC);
        habit3.setImage(R.drawable.ic_bed_24);

        habit4.setTitle(getString(R.string.preset_take_meds));
        habit4.setHabitType(HabitType.COUNTER);
        habit4.setImage(R.drawable.ic_healing_24);

        list.add(habit1);
        list.add(habit2);
        list.add(habit3);
        list.add(habit4);

        return list;
    }
}