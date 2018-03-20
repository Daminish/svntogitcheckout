package com.capco.technologies.living.presentation.findhome;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.location.City;
import com.capco.technologies.living.domain.model.location.ICity;
import com.capco.technologies.living.domain.model.location.ILocation;
import com.capco.technologies.living.presentation.ui.listener.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 08/01/18.
 */

public class LocationAdapter<T extends ILocation> extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private List<T> locations = new ArrayList<>();

    private OnRecyclerViewItemClickListener<T> onItemClickListener;

    public LocationAdapter(List<T> locations) {
        this.locations = locations;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setLocation(List<T> locations) {this.locations = locations;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location_list, parent, false);
        LocationAdapter.LocationViewHolder viewHolder = new LocationAdapter.LocationViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LocationAdapter.LocationViewHolder holder, int position) {
        holder.bindData(locations.get(position), position);
    }


    @Override
    public int getItemCount() {
        return locations.size();
    }


    class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvCity;

        public LocationViewHolder(View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.tv_location_name);
            itemView.setOnClickListener(this);
        }

        public void bindData(T location, int position) {
            tvCity.setText(location.getName());
            itemView.setTag(location);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(itemView, (T) itemView.getTag(),getAdapterPosition());
            }
        }
    }

}
