package hu.ait.camdensikes.weatheroverground.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.ait.camdensikes.weatheroverground.R;
import hu.ait.camdensikes.weatheroverground.WeatherDetailActivity;

/**
 * Created by Camden Sikes on 11/29/2016.
 */

public class DetailsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, null);

        TextView tvCityName = (TextView) rootView.findViewById(R.id.tvCityName);
        tvCityName.setText(((WeatherDetailActivity) container.getContext()).getCity());

        TextView tvMaxTemp = (TextView) rootView.findViewById(R.id.tvMaxTemp);
        tvMaxTemp.setText(((WeatherDetailActivity) container.getContext()).getMaxTemp());

        TextView tvMinTemp = (TextView) rootView.findViewById(R.id.tvMinTemp);
        tvMinTemp.setText(((WeatherDetailActivity) container.getContext()).getMinTemp());

        TextView tvVis = (TextView) rootView.findViewById(R.id.tvVis);
        tvVis.setText(((WeatherDetailActivity) container.getContext()).getVis());

        TextView tvHumidity = (TextView) rootView.findViewById(R.id.tvHumidity);
        tvHumidity.setText(((WeatherDetailActivity) container.getContext()).getHumidity());

        TextView tvPressure = (TextView) rootView.findViewById(R.id.tvPressure);
        tvPressure.setText(((WeatherDetailActivity) container.getContext()).getPressure());

        TextView tvWind = (TextView) rootView.findViewById(R.id.tvWind);
        tvWind.setText(((WeatherDetailActivity) container.getContext()).getWind());


        return rootView;
    }
}
