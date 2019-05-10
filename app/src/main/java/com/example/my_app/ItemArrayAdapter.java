package com.example.my_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidhire.splashscreen.R;

import java.util.ArrayList;
import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter {

    private List<String[]> list= new ArrayList<String[]>();
    static class ItemViewHolder{
        TextView location,date,time,instructor;
    }
    public ItemArrayAdapter( Context context, int resource) {
        super(context,resource);

    }
    public void add(String[] object){
        list.add(object);
        super.add(object);

    }
    @Override
    public int getCount(){
        return this.list.size(); }

    @Override
    public String[] getItem(int position) {
        return this.list.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_list_item, parent);
            viewHolder = new ItemViewHolder();
            viewHolder.location = (TextView) row.findViewById(R.id.location);
            viewHolder.date = (TextView) row.findViewById(R.id.date);
            viewHolder.time= (TextView) row.findViewById(R.id.time);
            viewHolder.instructor= (TextView) row.findViewById(R.id.instructor);}
        else {
            viewHolder = (ItemViewHolder) row.getTag(); }
        String[] stat = getItem(position);
        viewHolder.location.setText(stat[1]);
        viewHolder.date.setText(stat[2]);
        viewHolder.time.setText(stat[3]);
        viewHolder.instructor.setText(stat[5]);
        return row;
    }
}
