package com.example.mobile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//1st  create adaptor class to link between data source and xml
public class ItemAdapter extends RecyclerView.Adapter {
    ///2nd create attributes
    ArrayList<Phones> phonesList = new ArrayList<>();///3rd create arraylist
    Context context;///

    //4th generate constructor


    public ItemAdapter(ArrayList<Phones> phonesList, Context context) {
        this.phonesList = phonesList;
        this.context = context;
    }

    @NonNull
    @Override
    ///1st generate methods automatic by click on red lamp error checker.
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//from constructor
        ///7th step we create view inflater to link xml with adapter
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        ViewHolder vh=new ViewHolder(view);///up with 7th step
        return vh;//from constructor but we change null to vh
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
///8th step we create like set text view
        ((ViewHolder)holder).textname.setText(phonesList.get(position).getPhoneName());//set textview name
        ((ViewHolder)holder).textviewprice.setText(phonesList.get(position).getPhonePrice()+"KD");//set textview pricd
        //((ViewHolder)holder).img.setImageResource(phonesList.get(position).getPhoneImg());//set image view
        Picasso.with(context).load(phonesList.get(position).getPhoneImg()).into(((ViewHolder)holder).img);//picaso code to load image link inside image view.
///up with 8th step

        //nextday adding button step 1... on click in view do the  following
        ((ViewHolder)holder).v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,DetailsActivity.class);
                intent.putExtra("phone",phonesList.get(position));
                context.startActivity(intent);
            }
        });
        //adding click listener for the delete button
        ((ViewHolder)holder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phones removedPhone= phonesList.get(position);//variable to store deleted phone temporary
                int x=position;//variable to reserve the deleted data position in selected undo
                AlertDialog.Builder alert =new AlertDialog.Builder(context)//ALert Message
                        .setTitle("Attention")
                        .setMessage("Are you sure you want to delete")
                        .setPositiveButton("delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               phonesList.remove(position);//delete the item once click on delete button
                                 notifyDataSetChanged();//to refresh the data list

                                Snackbar.make(context,view,"1 item is deleted",3000)//add snack bar at the bottom
                                        .setAction("UNDO", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                phonesList.add(position,removedPhone);
                                                notifyDataSetChanged();

                                            }
                                        }).show();//to show the snackbar ribbon alert at the bottom
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();//do nothing
                            }
                        });
alert.show();//to show the dialog

            }
        });
    }

    @Override
    public int getItemCount() {
        return phonesList.size();//10th step we assign the counter to the size of the array list
    }
    //5th step we create inner class inside the main class, there will be error we generate constructor

    public class ViewHolder extends RecyclerView.ViewHolder{
//6th step we create variables for the output view
        ImageView img, delete;
        TextView textname, textviewprice;
        View v;//up with 6th step
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v=itemView;//created
            img=v.findViewById(R.id.imageViewItem); //created view
            textname=v.findViewById(R.id.textViewName1);//created view
            textviewprice=v.findViewById(R.id.textViewPrice1);//created view
            delete=v.findViewById(R.id.imageViewDelete);
        }

    }


}
