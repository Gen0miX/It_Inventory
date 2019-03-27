package com.example.it_inventory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.it_inventory.R;
import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.util.RecyclerViewItemClickListener;
import com.example.it_inventory.viewmodel.office.OfficeListViewModel;

import org.w3c.dom.Text;

import java.util.List;

public class OfficeAdapter<T> extends RecyclerView.Adapter<OfficeAdapter.OfficeViewHolder> {

    private RecyclerViewItemClickListener mylistener;
    private List<T> data;

    static class OfficeViewHolder extends RecyclerView.ViewHolder {
        private final TextView twName;
        private final TextView twFloor;
        private final TextView twCity;
        private final TextView twCountry;

        private OfficeViewHolder(View view, TextView twName, TextView twFloor, TextView twCity, TextView twCountry) {
            super(view);
            this.twName = twName;
            this.twFloor = twFloor;
            this.twCity = twCity;
            this.twCountry = twCountry;
        }
    }

    public OfficeAdapter (RecyclerViewItemClickListener listener){mylistener = listener;}

    @Override
    public OfficeAdapter.OfficeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerviewitem_office, parent, false);

        final TextView twName = v.findViewById(R.id.office_name);
        final TextView twFloor = v.findViewById(R.id.office_floor);
        final TextView twCity = v.findViewById(R.id.office_city);
        final TextView twCountry =  v.findViewById(R.id.office_country);

        final OfficeViewHolder officeViewHolder = new OfficeViewHolder(v, twName, twFloor, twCity, twCountry);
        v.setOnClickListener(view -> mylistener.onItemClick(view, officeViewHolder.getAdapterPosition()));
        v.setOnClickListener(view -> {
            mylistener.onItemLongClick(view, officeViewHolder.getAdapterPosition());
        });
        return officeViewHolder;
    }

    @Override
    public void onBindViewHolder(OfficeAdapter.OfficeViewHolder holder, int position){
        T item = data.get(position);
        holder.twName.setText(((OfficeEntity) item).getBuilding());
        holder.twFloor.setText(((OfficeEntity) item).getFloorString());
        holder.twCity.setText(((OfficeEntity) item).getCity());
        holder.twCountry.setText(((OfficeEntity) item).getCountry());
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    public void setData(final List<T> offices){
        if(this.data == null){
            this.data = offices ;
            notifyItemRangeInserted(0, offices.size());
        }else {

        }
    }


  /*  private final LayoutInflater layoutInflater ;
    private List<OfficeEntity> offices;

    public OfficeAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
//        this.offices = offices;
    }

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

  *//*  void setOffices(List<OfficeEntity> offices){
        this.offices = offices ;
        notifyDataSetChanged();
    }*//*

    @Override
    public int getItemCount(){
        if(offices != null)
            return offices.size();
        else
            return 0 ;
    }

    public void setData(final List<OfficeEntity> offices){
        if(this.offices == null){
            this.offices = offices ;
            notifyItemRangeInserted(0, offices.size());
        }else {

        }
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
*/
}
