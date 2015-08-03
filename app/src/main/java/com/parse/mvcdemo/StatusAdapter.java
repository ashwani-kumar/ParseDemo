package com.parse.mvcdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Ashwani on 25-07-2015.
 */
public class StatusAdapter extends ArrayAdapter<ParseObject> {

    private Context mContext;
    private List<ParseObject> mList;
    private int layoutResourceId;

    public StatusAdapter(Context context, int resourse) {
        super(context, resourse);
    }

    public StatusAdapter(Context context, int resourse, List<ParseObject> list) {
        super(context, resourse, list);
        mContext = context;
        layoutResourceId = resourse;
        mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.userImage = (ImageView) row.findViewById(R.id.userImage);
            holder.username = (TextView) row.findViewById(R.id.userName);
            holder.statusMessage = (TextView) row.findViewById(R.id.statusMessage);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ParseObject statusObject = mList.get(position);

        String statusMsg = statusObject.getString("newstatus");
        String userNm = statusObject.getString("user");
        holder.username.setText(userNm);
        holder.statusMessage.setText(statusMsg);
        return row;
    }

    static class ViewHolder {
        ImageView userImage;
        TextView username;
        TextView statusMessage;
    }
}
