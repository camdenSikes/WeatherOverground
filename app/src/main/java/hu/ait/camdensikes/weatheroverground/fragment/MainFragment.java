package hu.ait.camdensikes.weatheroverground.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hu.ait.camdensikes.weatheroverground.R;
import hu.ait.camdensikes.weatheroverground.WeatherDetailActivity;

/**
 * Created by Camden Sikes on 11/29/2016.
 */

public class MainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, null);


        TextView tvCityName = (TextView) rootView.findViewById(R.id.tvCityName);
        tvCityName.setText(((WeatherDetailActivity) container.getContext()).getCity());

        TextView tvTemp = (TextView) rootView.findViewById(R.id.tvTemp);
        tvTemp.setText(((WeatherDetailActivity) container.getContext()).getTemp());

        TextView tvDesc = (TextView) rootView.findViewById(R.id.tvDesc);
        tvDesc.setText(((WeatherDetailActivity) container.getContext()).getDesc());

        ImageView icWeather = (ImageView) rootView.findViewById(R.id.icWeather);
        Glide.with(this).load(((WeatherDetailActivity) container.getContext()).getIcon()).into(icWeather);


        return rootView;
    }
}
