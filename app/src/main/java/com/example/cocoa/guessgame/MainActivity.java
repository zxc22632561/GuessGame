package com.example.cocoa.guessgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> listStr = new ArrayList<>();
        for(int i =0;i<50;i++)
            listStr.add(new String("第"+ String.valueOf(i+1)+"項"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        ItemAdapter itemAdapter = new ItemAdapter(listStr);
        recyclerView.setAdapter(itemAdapter);


    }

    public class ItemAdapter extends
            RecyclerView.Adapter<ItemAdapter.ViewHolder>{

        private List<String>mListString;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public ImageView mImgView;
            public TextView mTxt;

            public ViewHolder(View itemView){
                super(itemView);
                mImgView = itemView.findViewById(R.id.imgView);
                mTxt = itemView.findViewById(R.id.txt);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(),
                        mListString.get(getAdapterPosition()),
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                //從MainActivity 到Main2Activity
                intent.setClass(MainActivity.this , MapsActivity.class);
                //開啟Activity
                startActivity(intent);


            }
        }

        public ItemAdapter(List<String> listString){
            mListString = listString;
        }

        @Override
        public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.recycler_view_item,parent,false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
            holder.mImgView.setImageResource(android.R.drawable.star_on);
            holder.mTxt.setText(mListString.get(position));
        }

        @Override
        public int getItemCount() {
            return mListString.size();
        }
    }


}
