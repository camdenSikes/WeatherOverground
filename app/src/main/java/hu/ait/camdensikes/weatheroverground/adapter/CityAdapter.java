package hu.ait.camdensikes.weatheroverground.adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import hu.ait.camdensikes.weatheroverground.MainActivity;
import hu.ait.camdensikes.weatheroverground.R;
import hu.ait.camdensikes.weatheroverground.WeatherDetailActivity;
import hu.ait.camdensikes.weatheroverground.data.ListItem;

/**
 * Created by Camden Sikes on 11/29/2016.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCity;
        public Button btnDelete;
        public Button btnGetWeather;


        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            btnGetWeather = (Button) itemView.findViewById(R.id.btnGetWeather);
        }
    }

    private List<ListItem> itemList;
    private Context context;

    public CityAdapter(List<ListItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvCity.setText(itemList.get(position).getCity());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeCity(position);
            }
        });
        holder.btnGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = itemList.get(position).getCity();
                ((MainActivity) context).openWeatherDetails(cityName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void addCity(ListItem item) {
        item.save();
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void removeCity(int index) {
        // remove it from the DB
        itemList.get(index).delete();
        // remove it from the list
        itemList.remove(index);
        notifyDataSetChanged();
    }


}
