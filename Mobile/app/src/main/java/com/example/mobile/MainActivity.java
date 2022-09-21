package com.example.mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Phones> phoneList = new ArrayList<>();///Array List///
    int phoneCounter =0;
    FirebaseDatabase database=FirebaseDatabase.getInstance("https://phonesshop-8d4c4-default-rtdb.firebaseio.com/");//to link the app with firebase database, the url must be inside ""
    DatabaseReference dbRef = database.getReference();//to link the app with firebase database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      ///  TextView name;
      ///  TextView price;
      ///  ImageView image;
      ///  Button buttonNext;

    ///Link object with Java///
       ///    name=findViewById(R.id.textViewName);
      ///  price=findViewById(R.id.textViewPrice);
      ///  image=findViewById(R.id.imageView);
      ///  buttonNext=findViewById(R.id.buttonNext);

    ///Phones List /// below has been added to firebase
       //Phones phone1 = new Phones("Samsung Galaxy S22 Ultra" , 464 , R.drawable.s22ultra);
        //Phones phone2 = new Phones("Samsung Galaxy Z Fold" , 574 , R.drawable.zflod3);
        //Phones phone3 = new Phones("Apple iPhone 13 Pro" , 390 , R.drawable.iphone13promax);
        //Phones phone4 = new Phones("Apple iPhone 12 Pro" , 330 , R.drawable.iphone12promax);
        //Phones phone5 = new Phones("HUAWEI P50 Pocket" , 429.90 , R.drawable.p50pocket);
        //Phones phone6 = new Phones("Samsung Galaxy S21" , 250 , R.drawable.s21);

    ///addition to ArrayList///
        //phoneList.add(phone1);
        //phoneList.add(phone2);
        //phoneList.add(phone3);
        //phoneList.add(phone4);
        //phoneList.add(phone5);
        //phoneList.add(phone6);

///Create Recycler View
        RecyclerView rm =findViewById(R.id.rv);
        rm.setHasFixedSize(true);
        RecyclerView.LayoutManager manager= new LinearLayoutManager(this);
        rm.setLayoutManager(manager);


        ItemAdapter adapter = new ItemAdapter(phoneList,this);//11th step we create adapter to link
        rm.setAdapter(adapter);

        //create query to update the db in firebase

        final Query myPhone= dbRef.child("Phones");
        myPhone.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot a:snapshot.getChildren()){//for each loop to search on each snapshot children and update it
                    Phones phone = a.getValue(Phones.class);
                    phoneList.add(phone);
                    adapter.notifyDataSetChanged();//to update the adapter and db
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}