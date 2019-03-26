package com.example.it_inventory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.it_inventory.R;
import com.example.it_inventory.database.entity.OfficeEntity;

import java.util.List;

public class OfficeAdapter extends RecyclerView.Adapter<OfficeAdapter.OfficeViewHolder> {

    private final LayoutInflater layoutInflater ;
    private List<OfficeEntity> offices;

    public OfficeAdapter(Context context) {layoutInflater = LayoutInflater.from(context);}

    @Override
    public OfficeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recyclerviewitem_office, parent, false);
        return new OfficeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder (OfficeViewHolder officeViewHolder, int position){
        if(offices != null){
            OfficeEntity current = offices.get(position);
            officeViewHolder.twName.setText(current.getBuilding());
            officeViewHolder.twFloor.setText(current.getFloor());
            officeViewHolder.twCity.setText(current.getCity());
            officeViewHolder.twCountry.setText(current.getCountry());
        }else{
            officeViewHolder.twName.setText("No data");
            officeViewHolder.twFloor.setText("No data");
            officeViewHolder.twCity.setText("No data");
            officeViewHolder.twCountry.setText("No data");
        }
    }

    void setOffices(List<OfficeEntity> offices){
        this.offices = offices ;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if(offices != null)
            return offices.size();
        else
            return 0 ;
    }



    class OfficeViewHolder extends RecyclerView.ViewHolder {
        private final TextView twName;
        private final TextView twFloor;
        private final TextView twCity;
        private final TextView twCountry;

        private OfficeViewHolder(View itemView){
            super(itemView);
            twName = itemView.findViewById(R.id.office_name);
            twFloor = itemView.findViewById(R.id.office_floor);
            twCity = itemView.findViewById(R.id.office_city);
            twCountry = itemView.findViewById(R.id.office_country);
        }

    }

}
