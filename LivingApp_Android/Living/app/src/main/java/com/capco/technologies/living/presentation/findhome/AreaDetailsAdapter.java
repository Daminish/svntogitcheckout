package com.capco.technologies.living.presentation.findhome;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.AreaDetailMenu;
import com.capco.technologies.living.presentation.ui.listener.OnRecyclerViewItemClickListener;

import java.util.List;

/**
 * Created by Shreyas Bhondve on 1/9/2018.
 */

public class AreaDetailsAdapter extends RecyclerView.Adapter<AreaDetailsAdapter.AreaDetailMenuViewHolder> {

    private OnRecyclerViewItemClickListener<AreaDetailMenu> onItemClickListener;

    @Override
    public AreaDetailsAdapter.AreaDetailMenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_item_list_dialog_item, viewGroup, false);
        AreaDetailsAdapter.AreaDetailMenuViewHolder vh = new AreaDetailsAdapter.AreaDetailMenuViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AreaDetailsAdapter.AreaDetailMenuViewHolder holder, int position) {
        holder.bindData(areaDetailMenus.get(position));
    }

    @Override
    public int getItemCount() {
        return areaDetailMenus.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class AreaDetailMenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView budgetItemName;
        ImageView budgetItemImg;


        AreaDetailMenuViewHolder(View itemView) {
            super(itemView);
            budgetItemName = itemView.findViewById(R.id.areaText);
            budgetItemImg = itemView.findViewById(R.id.areaImg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, (AreaDetailMenu) view.getTag(),getAdapterPosition());
            }

        }


        public void bindData(AreaDetailMenu menu) {
            budgetItemName.setText(menu.name);
            budgetItemImg.setImageResource(menu.image);
            this.itemView.setTag(menu);
        }


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    List<AreaDetailMenu> areaDetailMenus;

    public AreaDetailsAdapter(List<AreaDetailMenu> areaDetailMenus, OnRecyclerViewItemClickListener<AreaDetailMenu> onItemClickListener) {
        this.areaDetailMenus = areaDetailMenus;
        this.onItemClickListener = onItemClickListener;
    }
}