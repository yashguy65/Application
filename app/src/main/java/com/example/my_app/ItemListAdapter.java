package com.example.my_app;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidhire.splashscreen.R;

public class ItemListAdapter extends ArrayAdapter<String[]>{
    String  Location,Date,Time,Fee,Instructor;
    List<String[]> items;
    Context context;
    String path;
    int groupid;

    public ItemListAdapter(Context context,int vg,int id, List<String[]> items){
        super(context,id,items);
        String lg,dg ,tg,fg,ig; //String lg,String dg ,String tg,String fg,String ig,  *was there
        this.context=context;
        this.items=items;

    }
    static class ViewHolder {
        public TextView location;
        public TextView date;
        public TextView time;
        public TextView fee;
        public TextView instructor;


    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.location = (TextView) rowView.findViewById(R.id.location);
            viewHolder.date = (TextView) rowView.findViewById(R.id.date);
            viewHolder.time = (TextView) rowView.findViewById(R.id.time);
            viewHolder.fee = (TextView) rowView.findViewById(R.id.fee);
            viewHolder.instructor = (TextView) rowView.findViewById(R.id.instructor);


            rowView.setTag(viewHolder);
        }
        // Fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        String[] row=items.get(position);
        holder.location.setText(row[1]);
        holder.date.setText(row[2]);
        holder.time.setText(row[3]);
        holder.fee.setText(row[4]);
        holder.instructor.setText(row[5]);


        return rowView;
    }

}