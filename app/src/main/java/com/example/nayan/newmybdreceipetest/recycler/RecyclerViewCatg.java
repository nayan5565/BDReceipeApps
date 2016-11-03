package com.example.nayan.newmybdreceipetest.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.newmybdreceipetest.R;
import com.example.nayan.newmybdreceipetest.activity.ListOfReceipeActivity;
import com.example.nayan.newmybdreceipetest.model.MCategory;

import java.util.ArrayList;

/**
 * Created by NAYAN on 10/31/2016.
 */
public class RecyclerViewCatg extends RecyclerView.Adapter<RecyclerViewCatg.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<MCategory> mCategories;
    private MCategory category;

    public RecyclerViewCatg(Context context) {
        this.context = context;
        mCategories = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<MCategory> mCategories) {
        this.mCategories = mCategories;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_list_catg, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        category = mCategories.get(position);
        holder.image.setImageResource(R.drawable.bd);
        holder.title.setText(category.getCategoryTitle());
        holder.description.setText(category.getCategoryDetails());
        holder.number.setText(category.getCategoryTotalRecipe() + "");


    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, number, description;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            number = (TextView) itemView.findViewById(R.id.number);
            image = (ImageView) itemView.findViewById(R.id.image);
            description = (TextView) itemView.findViewById(R.id.description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    category = mCategories.get(getAdapterPosition());
                    Intent intent = new Intent(context, ListOfReceipeActivity.class);
                    intent.putExtra("id", category.getCategoryId());
                    context.startActivity(intent);


                }
            });

        }
    }
}
