package com.example.weather.UI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weather.R;
import com.example.weather.model.HourlyData;

import java.util.ArrayList;
import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder>{

    private TextView time, temperature;
    private ImageView icon;
    private List<HourlyData> hourlyDataList;

    private final LayoutInflater mInflater;

    //Constructor for adapter
    public HourlyAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup , int viewType) {

        View v = mInflater.inflate(R.layout.hourly_view, viewGroup, false);
        icon = v.findViewById(R.id.icon);
        time = v.findViewById(R.id.time);
        temperature = v.findViewById(R.id.temperature);
        return new ViewHolder(v, icon, time, temperature);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyAdapter.ViewHolder holder, int position) {
//        holder.icon.setImageDrawable(hourlyDataList.get(position).getIcon());
//        holder.time.setText(hourlyDataList.get(position).getTime());
        holder.time.setText("Monday");
    }

    public void setData(List<HourlyData> data){
        hourlyDataList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (hourlyDataList != null)
            return hourlyDataList.size();
        else return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView time, temperature;
        private ImageView icon;

        public ViewHolder(View view, ImageView icon, TextView time, TextView temperature) {
            super(view);
            this.icon = icon;
            this.temperature = temperature;
            this.time = time;
        }
    }
}
