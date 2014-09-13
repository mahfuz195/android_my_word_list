package com.mahfuz.mywordlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by islammha on 9/13/2014.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    Context context ;
    public LayoutInflater inflater ;
    ArrayList<String> parent_list , tempChild ;
    public ArrayList<Object> child_list = new ArrayList<Object>();
    ExpandableListAdapter(Context con , ArrayList<String> parent ,ArrayList<Object> child){
        this.context = con ;
        this.parent_list = parent ;
        this.child_list = child;
    }
    @Override
    public int getGroupCount() {
        return parent_list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return ((ArrayList<String>) child_list.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i2) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = new TextView(context);
        }
        ((TextView) convertView).setText(parent_list.get(i));
        convertView.setTag(parent_list.get(i));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        tempChild = (ArrayList<String>) child_list.get(groupPosition);
        TextView text = null;
        if (convertView == null) {
            convertView = new TextView(context);
        }
        text = (TextView) convertView;
        text.setText(">"+tempChild.get(childPosition));
//		convertView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(activity, tempChild.get(childPosition),
//						Toast.LENGTH_SHORT).show();
//			}
//		});
        convertView.setTag(tempChild.get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }
}
