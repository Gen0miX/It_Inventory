package com.example.it_inventory.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_inventory.R;
import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.util.RecyclerViewItemClickListener;
import com.example.it_inventory.viewmodel.office.OfficeListViewModel;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;

public class OfficeAdapter<T> extends RecyclerView.Adapter<OfficeAdapter.OfficeViewHolder> {

    private RecyclerViewItemClickListener mylistener;
    private List<T> data;

    private Context context ;


    static class OfficeViewHolder extends RecyclerView.ViewHolder {
        private final TextView twName;
        private final TextView twFloor;
        private final TextView twCity;
        private final TextView twCountry;
       /* public final LinearLayout linearLayout;*/

        private OfficeViewHolder(View view, TextView twName, TextView twFloor, TextView twCity, TextView twCountry) {
            super(view);
            this.twName = twName;
            this.twFloor = twFloor;
            this.twCity = twCity;
            this.twCountry = twCountry;
            /*this.linearLayout = (LinearLayout) itemView.findViewById(R.id.office_LinearLayout);*/
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

       /* holder.linearLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(context,"You clicked "+((OfficeEntity)item).getBuilding(), Toast.LENGTH_LONG).show() ;
            }
        });*/
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
            notifyItemRangeInserted(0, offices.size()+1);
        }else{
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return data.size();
                }

                @Override
                public int getNewListSize() {
                    return 0;
                }

                @Override
                public boolean areItemsTheSame(int i, int i1) {
                    return false;
                }

                @Override
                public boolean areContentsTheSame(int i, int i1) {
                    return false;
                }
            });
            data = offices ;
            result.dispatchUpdatesTo(this);
        }
    }
}
