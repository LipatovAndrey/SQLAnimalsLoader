package com.example.animalsloader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Андрей on 21.05.2017.
 */

public class AnimalListAdapter extends BaseAdapter {

    private List<Animal> mAnimalList;

    public AnimalListAdapter(List<Animal> animals) {
        mAnimalList = animals;
    }

    @Override
    public int getCount() {
        return mAnimalList.size();
    }

    @Override
    public Animal getItem(int position) {
        return mAnimalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Animal currentAnimal = mAnimalList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.mNameView = (TextView) convertView.findViewById(R.id.itemName);
            viewHolder.mSpecieView = (TextView) convertView.findViewById(R.id.itemSpecie);
            viewHolder.mAgeView = (TextView) convertView.findViewById(R.id.itemAge);

            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.mNameView.setText(currentAnimal.getName());
        viewHolder.mSpecieView.setText(currentAnimal.getSpecie());
        viewHolder.mAgeView.setText(String.valueOf(currentAnimal.getAge()));
        convertView.setId((int) getItemId(position));
        return convertView;
    }

    class ViewHolder {
        TextView mNameView;
        TextView mSpecieView;
        TextView mAgeView;
    }
}
