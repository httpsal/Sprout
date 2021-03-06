package com.hcifedii.sprout.fragment.habitType;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcifedii.sprout.R;
import com.hcifedii.sprout.fragment.HabitTypeFragment;
import com.shawnlin.numberpicker.NumberPicker;

public class CounterTypeFragment extends Fragment implements HabitTypeInterface {

    private NumberPicker picker;
    private HabitTypeFragment parent;

    private static final String PICKER_VALUE_KEY = "pickerValue";

    public CounterTypeFragment() {
        // Required empty public constructor
    }

    public CounterTypeFragment(HabitTypeFragment habitTypeFragment) {
        parent = habitTypeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_habit_type_counter, container, false);

        picker = view.findViewById(R.id.repetitionsPicker);

        setDefaultValue();

        return view;
    }

    private void setDefaultValue() {
        if (parent != null) {
            int defaultValue = parent.getDefaultValue();
            if (defaultValue > 0)
                setMaxRepetitions(defaultValue);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            int value = savedInstanceState.getInt(PICKER_VALUE_KEY);
            picker.setValue(value);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(PICKER_VALUE_KEY, picker.getValue());
    }

    @Override
    public int getRepetitions() {
        return picker.getValue();
    }

    @Override
    public void setMaxRepetitions(int repetitions) {
        picker.setValue(repetitions);
    }
}