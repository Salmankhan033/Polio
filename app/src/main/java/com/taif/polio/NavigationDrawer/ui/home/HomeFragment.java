package com.taif.polio.NavigationDrawer.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.taif.polio.Common.SigninSignup.AddDataActivity;
import com.taif.polio.Common.SigninSignup.DataShowActivity;
import com.taif.polio.R;

public class HomeFragment extends Fragment {
ImageView addData,batch0,batch1,batch2,batch3,batch4;

    private HomeViewModel homeViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        addData=root.findViewById(R.id.addData);
        batch0=root.findViewById(R.id.batch0);
        batch1=root.findViewById(R.id.batch1);
        batch2=root.findViewById(R.id.batch2);
        batch3=root.findViewById(R.id.batch3);
        batch4=root.findViewById(R.id.batch4);

        batch0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DataShowActivity.class);
                intent.putExtra("batch","0");
                startActivity(intent);

            }
        });
        batch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DataShowActivity.class);
                intent.putExtra("batch","1");
                startActivity(intent);

            }
        });
        batch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DataShowActivity.class);
                intent.putExtra("batch","2");
                startActivity(intent);

            }
        });
        batch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DataShowActivity.class);
                intent.putExtra("batch","3");
                startActivity(intent);

            }
        });
        batch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DataShowActivity.class);
                intent.putExtra("batch","4");
                startActivity(intent);

            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddDataActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}