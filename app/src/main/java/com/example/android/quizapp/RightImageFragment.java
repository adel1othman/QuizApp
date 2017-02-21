package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

public class RightImageFragment extends Fragment {

    public RightImageFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.city_list, container, false);

        final ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(R.string.zagreb, R.drawable.zagreb));
        cities.add(new City(R.string.berlin, R.drawable.bern));
        cities.add(new City(R.string.bern, R.drawable.berlin));
        cities.add(new City(R.string.brussels, R.drawable.copenhagen));
        cities.add(new City(R.string.copenhagen, R.drawable.brussels));
        cities.add(new City(R.string.helsinki, R.drawable.luxembourg));
        cities.add(new City(R.string.london, R.drawable.london));
        cities.add(new City(R.string.luxembourg, R.drawable.helsinki));
        cities.add(new City(R.string.oslo, R.drawable.vienna));
        cities.add(new City(R.string.paris, R.drawable.paris));
        cities.add(new City(R.string.rome, R.drawable.rome));
        cities.add(new City(R.string.vienna, R.drawable.oslo));

        CityAdapter adapter = new CityAdapter(getActivity(), cities);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        return rootView;
    }
}