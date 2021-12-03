package com.taif.polio.NavigationDrawer.ui.ActiveJobs;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taif.polio.R;

public class ActiveJobsFragment extends Fragment {

    private ActiveJobsViewModel mViewModel;

    public static ActiveJobsFragment newInstance() {
        return new ActiveJobsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_active_jobs, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ActiveJobsViewModel.class);
        // TODO: Use the ViewModel
    }

}