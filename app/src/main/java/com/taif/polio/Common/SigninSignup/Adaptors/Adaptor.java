package com.taif.polio.Common.SigninSignup.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.taif.polio.HelperClasses.ChildData;
import com.taif.polio.R;

import java.util.List;

public class Adaptor extends RecyclerView.Adapter<ChidDataViewHolder> {
    private Context myContext;
    List<ChildData> childDataList;
    String Key;

    public Adaptor(Context myContext, List<ChildData> childDataList) {
        this.myContext = myContext;
        this.childDataList = childDataList;
    }

    @NonNull
    @Override
    public ChidDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card, parent, false);
        return new ChidDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChidDataViewHolder holder, final int position) {
        final String Name =childDataList.get(position).getName();
        final String F_name=childDataList.get(position).getF_Name();
        final String M_name=childDataList.get(position).getM_Name();
        final String Address=childDataList.get(position).getAddress();
        final String dateOfBirth=childDataList.get(position).getDateofBirth();
        final String PhoneNo=childDataList.get(position).getPhoneNo();
        Integer Age= Integer.valueOf(childDataList.get(position).getAge());
        Age=Age+1;
        final String AgeString= String.valueOf(Age);

        holder.name.setText(Name);
        holder.fName.setText(F_name);
        holder.fAddress.setText(Address);
        holder.mName.setText(M_name);
        holder.phone.setText(PhoneNo);
        holder.dateOfBirth.setText(dateOfBirth);
holder.PolioDone.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FirebaseDatabase firebaseDatabase;
      DatabaseReference databaseReference;
        firebaseDatabase=FirebaseDatabase.getInstance();

        ChildData childData=new ChildData(Name,F_name,M_name,dateOfBirth,AgeString,Address,PhoneNo,"");
        databaseReference=firebaseDatabase.getReference().child("Children").child(AgeString).push();
        databaseReference.setValue(childData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Key = childDataList.get(position).getId();
                Toast.makeText(myContext, "Thanks For giving Polio", Toast.LENGTH_LONG).show();

                final DatabaseReference databaseReferenc = FirebaseDatabase.getInstance().getReference("Children").child(childDataList.get(position).getAge());
                databaseReferenc.child(Key).removeValue();


            }
        });


    }
});



    }

    @Override
    public int getItemCount() {
        return childDataList.size();
    }
}

class ChidDataViewHolder extends RecyclerView.ViewHolder{
  TextView name, fName, fAddress, mName,phone,dateOfBirth;
    CardView cardView;
    Button PolioDone;
    public ChidDataViewHolder(@NonNull View itemView) {
        super(itemView);
         name=itemView.findViewById(R.id.childName);
         fName=itemView.findViewById(R.id.Fname);
          fAddress=itemView.findViewById(R.id.Address);
          mName=itemView.findViewById(R.id.Mname);
          phone=itemView.findViewById(R.id.mobile);
          dateOfBirth=itemView.findViewById(R.id.dateBirth);
          PolioDone=itemView.findViewById(R.id.polio);

    }
}
