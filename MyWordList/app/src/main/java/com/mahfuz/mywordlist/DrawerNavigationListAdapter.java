package com.mahfuz.mywordlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by IslamMha on 8/27/2014.
 */
public class DrawerNavigationListAdapter extends BaseAdapter {
    private Context context ;
    ArrayList<DrawNavItems> items = new ArrayList<DrawNavItems>();
    DrawerNavigationListAdapter(Context con, ArrayList<DrawNavItems> i){
        this.context  = con ;
        this.items= i ;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View courtView, ViewGroup parent) {
        if(courtView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            courtView = inflater.inflate(R.layout.drawer_list_view,null);

            ImageView img = (ImageView)courtView.findViewById(R.id.icon);
            TextView  tx = (TextView)courtView.findViewById(R.id.title);

            img.setImageResource(items.get(position).getIcon());
            tx.setText(items.get(position).getTitle());
        }
        return courtView;
    }
}
