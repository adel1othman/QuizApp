package com.example.android.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

class CityAdapter extends ArrayAdapter<City> {

    private ArrayList<City> appInfoList;
    static ArrayList<Boolean> positionArray;

    CityAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
        appInfoList = cities;

        positionArray = new ArrayList<>(cities.size());
        for(int i =0;i<cities.size();i++){
            positionArray.add(false);
        }
    }

    @Override
    public int getCount() {
        return appInfoList.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.quiz3, parent, false);

            holder = new Holder();
            holder.ckbox =(CheckBox)listItemView.findViewById(R.id.chkBox);

            listItemView.setTag(holder);
        }else {

            holder = (Holder) convertView.getTag();
            holder.ckbox.setOnCheckedChangeListener(null);

        }

        holder.ckbox.setFocusable(false);
        holder.ckbox.setChecked(positionArray.get(position));
        holder.ckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    System.out.println(position+"--- :)");
                    positionArray.set(position, true);
                }else
                    positionArray.set(position, false);
            }
        });

        City currentCity = getItem(position);

        TextView cityTxtViw = (TextView) listItemView.findViewById(R.id.cityTextView);
        cityTxtViw.setText(currentCity.getmCityName());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentCity.getmImageResourceId());

        return listItemView;
    }

    private static class Holder
    {
        CheckBox ckbox;
    }
}