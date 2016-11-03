package com.example.nayan.newmybdreceipetest.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nayan.newmybdreceipetest.R;
import com.example.nayan.newmybdreceipetest.activity.SingleReceipeActivity;
import com.example.nayan.newmybdreceipetest.model.MReceipe;

import java.util.ArrayList;

/**
 * Created by NAYAN on 11/3/2016.
 */
public class MyAcharAdapter extends RecyclerView.Adapter<MyAcharAdapter.MyViewHolder>{
    private View view;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<MReceipe>mReceipes;
    private MReceipe mReceipe;

    public MyAcharAdapter(Context context){
        this.context=context;
        mReceipes=new ArrayList<>();
        inflater=LayoutInflater.from(context);
    }
    public void setData(ArrayList<MReceipe>mReceipes){
        this.mReceipes=mReceipes;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view=inflater.inflate(R.layout.row_receipe_list,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mReceipe=mReceipes.get(position);
        holder.txtReceipe.setText(mReceipe.getTitle());

    }

    @Override
    public int getItemCount() {
        return mReceipes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtReceipe;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtReceipe=(TextView)itemView.findViewById(R.id.txtReceipe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mReceipe=mReceipes.get(getAdapterPosition());
                    Intent intent=new Intent(context, SingleReceipeActivity.class);
                    intent.putExtra("imgredins",mReceipe.getIngredients());
                    intent.putExtra("process",mReceipe.getProcess());
                    context.startActivity(intent);
                }
            });
        }
    }
}
