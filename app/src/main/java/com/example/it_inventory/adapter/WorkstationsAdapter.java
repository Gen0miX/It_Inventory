package com.example.it_inventory.adapter;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.it_inventory.database.entity.WorkstationEntity;

import java.util.List;

public class WorkstationsAdapter extends BaseAdapter {

    private final Context mContext ;
    private final LiveData<List<WorkstationEntity>> workstations ;

    public WorkstationsAdapter(Context mContext, LiveData<List<WorkstationEntity>> workstations){
    this.mContext = mContext ;
    this.workstations = workstations ;
    }

    @Override
    public int getCount() {
        return workstations.getValue().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView dummyTextView = new TextView(mContext);
        dummyTextView.setText(String.valueOf(position));
        return dummyTextView;
    }
}
