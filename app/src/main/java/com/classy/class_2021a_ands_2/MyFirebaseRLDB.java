package com.classy.class_2021a_ands_2;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyFirebaseRLDB {

    public static final String REFERENCE_PATH = "users/guy/enImage";

    public interface CallBack_DataIsReady {
        void CallBack_DataIsReady(String data);
    }

    public static void uploadString(String data) {
        Log.d("pttt", "uploadString");
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(REFERENCE_PATH).setValue(data)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("pttt", "onFailure");
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Log.d("pttt", "onCanceled");

                    }
                }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("pttt", "onComplete");
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("pttt", "onSuccess");
            }
        });
    }

    public static void downloadData(CallBack_DataIsReady callBack_dataIsReady) {
        Log.d("pttt", "downloadData");
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child(REFERENCE_PATH).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (callBack_dataIsReady != null) {
                    callBack_dataIsReady.CallBack_DataIsReady(value);
                }
                //Log.d("pttt", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("pttt", "Failed to read value.", error.toException());
            }
        });
    }
}
