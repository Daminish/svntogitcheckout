package com.capco.technologies.living.presentation.budgeting.step3;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.Expense;
import com.capco.technologies.living.domain.model.IExpenseHeader;
import com.capco.technologies.living.domain.model.IExpenseItem;
import com.capco.technologies.living.presentation.ui.listener.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sachin on 07/01/18.
 */

public class BudgetExpenseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private List<Expense> expenses = new ArrayList<>();

    private OnRecyclerViewItemClickListener<Expense> onItemClickListener;

    public BudgetExpenseAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<Expense> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == TYPE_HEADER) {
            viewHolder = new ViewHolderHeader(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_budget_list, parent, false));
        } else {
            viewHolder = new ViewHolderItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_budget_list, parent, false));
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderHeader) {
            ((ViewHolderHeader) holder).bindData((IExpenseHeader) expenses.get(position), position);
        } else {
            ((ViewHolderItem) holder).bindData((IExpenseItem) expenses.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    @Override
    public int getItemViewType(int position) {
        return expenses.get(position) instanceof IExpenseHeader ? TYPE_HEADER : TYPE_ITEM;
    }

    class ViewHolderHeader extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        AppCompatImageView icon;

        public ViewHolderHeader(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_item_budget_expense_header_title);
            icon = itemView.findViewById(R.id.iv_item_budget_expense_header_icon);
        }

        public void bindData(IExpenseHeader expense, int position) {
            name.setText(expense.getName());
            itemView.setTag(expense);
            itemView.setTag(R.string.item_click_tag, position);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(itemView, (IExpenseHeader) itemView.getTag(), getAdapterPosition());
            }
        }
    }


    class ViewHolderItem extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView icon;
        TextView name;
        TextView amount;

        public ViewHolderItem(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_item_budget_expense_name);
            amount = itemView.findViewById(R.id.tv_item_budget_expense_amount);
            icon = itemView.findViewById(R.id.iv_item_budget_expense_edit);
        }

        public void bindData(IExpenseItem expense, int position) {
            name.setText(expense.getName());
            amount.setText(String.format(itemView.getContext().getString(R.string.amount), expense.getAmount()));
            itemView.setTag(expense);
            itemView.setTag(R.string.item_click_tag, position);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(itemView, (IExpenseItem) itemView.getTag(), getAdapterPosition());
            }
        }
    }

}
