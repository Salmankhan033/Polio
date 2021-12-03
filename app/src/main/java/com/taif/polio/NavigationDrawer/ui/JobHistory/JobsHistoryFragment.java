package com.taif.polio.NavigationDrawer.ui.JobHistory;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taif.polio.R;

public class JobsHistoryFragment extends Fragment {

    private JobsHistoryViewModel mViewModel;

    public static JobsHistoryFragment newInstance() {
        return new JobsHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobs_history, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(JobsHistoryViewModel.class);
        // TODO: Use the ViewModel
    }

}