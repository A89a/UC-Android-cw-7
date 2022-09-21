package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TextView nameText=findViewById(R.id.textViewNameD);
        TextView priceText=findViewById(R.id.textViewPriceD);
        ImageView img= findViewById(R.id.imageViewDetails);

        //next day  we add view and link it to show the next page once click on the main view
        Bundle bundle=getIntent().getExtras();
        Phones sentPhone =(Phones) bundle.getSerializable("phone");
        nameText.setText(sentPhone.getPhoneName());
        priceText.setText(sentPhone.getPhonePrice()+"");
       // img.setImageResource(sentPhone.getPhoneImg());
        Picasso.with(this).load(sentPhone.getPhoneImg()).into(img);//picasso code to load images from the link

    }
}