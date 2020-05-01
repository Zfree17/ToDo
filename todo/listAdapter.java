package com.example.todo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class listAdapter extends BaseAdapter {
    Activity mActivity;
    myItems myItems;

    public listAdapter(Activity mActivity, myItems myItems) {
        this.mActivity = mActivity;
        this.myItems = myItems;
    }

    @Override
    public int getCount() {
        return myItems.getMyItems().size();
    }

    @Override
    public reminder getItem(int position) {
        return myItems.getMyItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View onePersonLine;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        onePersonLine = inflater.inflate(R.layout.item_one_line, parent, false);
        TextView tv_title = onePersonLine.findViewById(R.id.tv_title);
        TextView tv_time = onePersonLine.findViewById(R.id.time);
        ImageView tv_icon = onePersonLine.findViewById(R.id.tv_icon);

        reminder p = this.getItem(position);

        tv_title.setText(p.getTitle());
        tv_time.setText(Integer.toString(p.getTime()));

        int icon_resource_numbers [] = {
                R.drawable.ic_action_name,
                R.drawable.ic_action_name,
                R.drawable.ic_action_name,
                R.drawable.ic_action_name,
                R.drawable.ic_action_name,
                R.drawable.ic_action_name,
                R.drawable.ic_action_name,
                R.drawable.ic_action_name,
                R.drawable.ic_action_name,

        };

        tv_icon.setImageResource(icon_resource_numbers[position]);

        return onePersonLine;
    }
}
