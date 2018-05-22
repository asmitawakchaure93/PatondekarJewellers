package com.patondekarjewellers.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.patondekarjewellers.R;
import com.patondekarjewellers.models.SideMenuItem;

import java.util.ArrayList;

/**
 * Created by Asmita on 09-08-2016.
 */
    public class SlidingPaneAdapter extends ArrayAdapter<SideMenuItem>
{
        public SlidingPaneAdapter(Context context, int textViewResourceId, ArrayList<SideMenuItem> objects) {
            super(context, textViewResourceId, objects);
        }

        class ViewHolder {
            TextView tvTitle,tvCount;
            ImageView ivIcon;

            private void initialise(View convertView){
                tvTitle = (TextView) convertView.findViewById(R.id.tvChannelShareCount);
                ivIcon = (ImageView)convertView.findViewById(R.id.ivIcon);
            }
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(getContext(), R.layout.sliding_pane_list_items, null);
                holder.initialise(convertView);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvTitle.setText(getItem(position).getTitle());
            holder.ivIcon.setImageResource(getItem(position).getImageres());

            return convertView;
        }
}