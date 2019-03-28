package com.example.it_inventory.adapter;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.it_inventory.R;
import com.example.it_inventory.database.entity.WorkstationEntity;
import com.example.it_inventory.util.RecyclerViewItemClickListener;

import java.util.List;

public class WorkstationsAdapter<T> extends RecyclerView.Adapter<WorkstationsAdapter.WorkstationViewHolder> {

    private RecyclerViewItemClickListener mylisterner;
    private List<T> data;

    static class WorkstationViewHolder extends RecyclerView.ViewHolder {

        private final TextView twName;
        private final Switch swScreens;
        private final Switch swPortable;
        private final TextView twOs;
        private final TextView twRam;
        private final TextView twStorage;
        private final TextView twProcessor;
        private final TextView twKeyboard;

        private WorkstationViewHolder(View view, TextView twName, Switch swScreens, Switch swPortable, TextView twOs, TextView twRam,
                                        TextView twStorage, TextView twProcessor, TextView twKeyboard) {
            super(view);
            this.twName = twName ;
            this.swScreens = swScreens;
            this.swPortable = swPortable;
            this.twOs = twOs;
            this.twRam = twRam;
            this.twStorage = twStorage;
            this.twProcessor = twProcessor;
            this.twKeyboard = twKeyboard;
        }
    }

    public WorkstationsAdapter(RecyclerViewItemClickListener listener){mylisterner = listener;}

    @Override
    public WorkstationsAdapter.WorkstationViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerviewitem_workstation, parent, false);

        final TextView twName = v.findViewById(R.id.workstation_name);
        final Switch swScreens = v.findViewById(R.id.workstation_switch_screen);
        final Switch swPortable = v.findViewById(R.id.workstation_switch_portable);
        final TextView twOs = v.findViewById(R.id.workstation_os);
        final TextView twRam = v.findViewById(R.id.workstation_ram);
        final TextView twStorage = v.findViewById(R.id.workstation_storage);
        final TextView twProcessor = v.findViewById(R.id.workstation_processor);
        final TextView twKeyboard = v.findViewById(R.id.workstation_keyboard_type);

        final WorkstationViewHolder workstationViewHolder = new WorkstationViewHolder(v, twName, swScreens, swPortable, twOs, twRam,
                                                                                        twStorage, twProcessor, twKeyboard);
        v.setOnClickListener(view -> mylisterner.onItemClick(view, workstationViewHolder.getAdapterPosition()));
        v.setOnClickListener(view -> {
            mylisterner.onItemLongClick(view, workstationViewHolder.getAdapterPosition());
        });
        return workstationViewHolder;
    }

    @Override
    public void onBindViewHolder(WorkstationsAdapter.WorkstationViewHolder holder, int position){
        T item = data.get(position);
        holder.twName.setText("Workstation "+(position+1));

        //Desactivate the switch and set them
       if(((WorkstationEntity)item).isScreens()){
           holder.swScreens.setFocusable(false);
           holder.swScreens.setClickable(false);
           holder.swScreens.setChecked(true);
       }else{
           holder.swScreens.setFocusable(false);
           holder.swScreens.setClickable(false);
           holder.swScreens.setChecked(false);
       }
       if(((WorkstationEntity)item).isPortable()){
           holder.swPortable.setFocusable(false);
           holder.swPortable.setClickable(false);
           holder.swPortable.setChecked(true);
       }else{
           holder.swPortable.setFocusable(false);
           holder.swPortable.setClickable(false);
           holder.swPortable.setChecked(false);
       }

       holder.twOs.setText(((WorkstationEntity)item).getOs());
       holder.twRam.setText(((WorkstationEntity)item).getRamString());
       holder.twStorage.setText(((WorkstationEntity)item).getStorageString());
       holder.twProcessor.setText(((WorkstationEntity)item).getProcessor());
       holder.twKeyboard.setText(((WorkstationEntity)item).getKeyboardType());
    }

    @Override
    public int getItemCount(){
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    public void setData(final List<T> workstations){
        if(this.data == null){
            this.data = workstations ;
            notifyItemRangeInserted(0, workstations.size());
        }else{
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return data.size();
                }

                @Override
                public int getNewListSize() {
                    return workstations.size();
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
            data = workstations;
            result.dispatchUpdatesTo(this);
        }
    }

}
