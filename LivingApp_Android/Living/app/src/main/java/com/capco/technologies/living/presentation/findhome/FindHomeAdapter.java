package com.capco.technologies.living.presentation.findhome;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.AreaDetailMenu;
import com.capco.technologies.living.domain.model.FindHomeMenu;
import com.capco.technologies.living.presentation.ui.listener.OnRecyclerViewItemClickListener;

import java.util.List;

/**
 * Created by Shreyas Bhondve on 1/9/2018.
 */

public class FindHomeAdapter extends RecyclerView.Adapter<FindHomeAdapter.FindHomeMenuViewHolder> {

    private OnRecyclerViewItemClickListener<FindHomeMenu> onItemClickListener;


    @Override
    public FindHomeAdapter.FindHomeMenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.for_find_home_lading_list_item, viewGroup, false);
        FindHomeAdapter.FindHomeMenuViewHolder vh = new FindHomeAdapter.FindHomeMenuViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(FindHomeAdapter.FindHomeMenuViewHolder holder, int position) {
        holder.bindData(findHomeMenus.get(position));
    }

    @Override
    public int getItemCount() {
        return findHomeMenus.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class FindHomeMenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView findHomeItemName;
        ImageView findHomeItemImg;


        FindHomeMenuViewHolder(View itemView) {
            super(itemView);
            findHomeItemName = itemView.findViewById(R.id.fh_textView);
            findHomeItemImg = itemView.findViewById(R.id.fh_imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, (FindHomeMenu) view.getTag(),getAdapterPosition());
            }

        }


        public void bindData(FindHomeMenu menu) {
            findHomeItemName.setText(menu.name);
            findHomeItemImg.setImageResource(menu.image);
            this.itemView.setTag(menu);
        }


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    List<FindHomeMenu> findHomeMenus;

    public FindHomeAdapter(List<FindHomeMenu> findHomeMenus, OnRecyclerViewItemClickListener<FindHomeMenu> onItemClickListener) {
        this.findHomeMenus = findHomeMenus;
        this.onItemClickListener = onItemClickListener;
    }
}
