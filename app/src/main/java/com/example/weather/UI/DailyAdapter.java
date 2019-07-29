package com.example.weather.UI;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weather.R;
import com.example.weather.model.DailyData;
import com.example.weather.model.HourlyData;

import java.util.ArrayList;
import java.util.List;


public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder>{

private TextView day, highTemp, lowTemp;
private ImageView icon;
private List<DailyData> dailyDataList;

private final LayoutInflater mInflater;


//Constructor for adapter
public DailyAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
}

@NonNull
@Override
public DailyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup , int viewType) {

        View v = mInflater.inflate(R.layout.daily_view, viewGroup, false);
        icon = v.findViewById(R.id.icon);
        day = v.findViewById(R.id.day);
        highTemp = v.findViewById(R.id.highTemp);
        lowTemp = v.findViewById(R.id.lowTemp);
        return new ViewHolder(v, icon, day, highTemp,lowTemp);
        }

@Override
public void onBindViewHolder(@NonNull DailyAdapter.ViewHolder holder, int position) {
//        holder.icon.setImageDrawable(hourlyDataList.get(position).getIcon());
//        holder.time.setText(hourlyDataList.get(position).getTime());
    holder.highTemp.setText("Tuesday");
        }

    public void setData(List<DailyData> data){
        dailyDataList = data;
        Log.d("DailyAdapter", "setData: "+data.size());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (dailyDataList != null)
            return dailyDataList.size();
        else return 0;
    }


public class ViewHolder extends RecyclerView.ViewHolder{

    private TextView day, highTemp, lowTemp;
    private ImageView icon;

    public ViewHolder(View view, ImageView icon, TextView day, TextView highTemp, TextView lowTemp) {
        super(view);
        this.day = day;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.icon = icon;
    }
}
}