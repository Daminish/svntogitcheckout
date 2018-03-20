package com.capco.technologies.living.presentation.budgeting;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.BudgetMenu;
import com.capco.technologies.living.presentation.ui.listener.OnRecyclerViewItemClickListener;

import java.util.List;

public class EstimateBudgetMenuAdapter extends RecyclerView.Adapter<EstimateBudgetMenuAdapter.BudgetMenuViewHolder> {


    private OnRecyclerViewItemClickListener<BudgetMenu> onItemClickListener;

    @Override
    public BudgetMenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_budgeting, viewGroup, false);
        BudgetMenuViewHolder vh = new BudgetMenuViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BudgetMenuViewHolder holder, int position) {
        holder.bindData(budgetItems.get(position));
    }

    @Override
    public int getItemCount() {
        return budgetItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class BudgetMenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView budgetItemName;
        TextView budgetItemValue;
        ImageView budgetItemImg;


        BudgetMenuViewHolder(View itemView) {
            super(itemView);
            budgetItemName = itemView.findViewById(R.id.estimateBudgetTitle);
            budgetItemValue = itemView.findViewById(R.id.estimateBudget);
            budgetItemImg = itemView.findViewById(R.id.budgetingImg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, (BudgetMenu) view.getTag(), getAdapterPosition());
            }

        }


        public void bindData(BudgetMenu menu) {
            budgetItemName.setText(menu.name);
            budgetItemValue.setText(TextUtils.isEmpty(menu.value) ? "" : menu.value);
            budgetItemImg.setImageResource(menu.image);
            this.itemView.setTag(menu);
        }


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    List<BudgetMenu> budgetItems;

    public EstimateBudgetMenuAdapter(List<BudgetMenu> budgetItems, OnRecyclerViewItemClickListener<BudgetMenu> onItemClickListener) {
        this.budgetItems = budgetItems;
        this.onItemClickListener = onItemClickListener;
    }
}