package com.taif.polio.Common.SigninSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.taif.polio.Common.SigninSignup.Adaptors.Adaptor;
import com.taif.polio.HelperClasses.ChildData;
import com.taif.polio.NavigationDrawer.NavigationDrawerActivity;
import com.taif.polio.R;

import java.util.ArrayList;
import java.util.List;

public class DataShowActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ChildData> childDataList;
    TextView loading;

    FirebaseDatabase firebaseDatabase;
    String batch;

    DatabaseReference databaseReference;
    ChildData childDataHelper;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);
        recyclerView=findViewById(R.id.recylerView);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        loading=findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);
        back=findViewById(R.id.databack);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        batch=getIntent().getStringExtra("batch").toString();



        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,1);
        recyclerView .setLayoutManager(gridLayoutManager);
        childDataList=new ArrayList<>();

        final Adaptor myAdapter=new Adaptor(getApplicationContext(),childDataList);
        recyclerView.setAdapter(myAdapter);

        databaseReference.child("Children").child(batch).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // order=snapshot.getValue(OrderHelperClass.class);
                childDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){

                    childDataHelper=itemSnapshot.getValue(ChildData.class);
                    childDataHelper.setId(itemSnapshot.getKey());
                    childDataList.add(childDataHelper);


                }
                myAdapter.notifyDataSetChanged();
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

}