package com.capco.technologies.living.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.capco.technologies.living.R;
import com.capco.technologies.living.datamodel.FindHomeLandingPageData;
import com.capco.technologies.living.presentation.findhome.AreaGuideActivity;


import java.util.List;

/**
 * Created by test on 05/01/18.
 */

public class FindHomeAdapter extends RecyclerView.Adapter<FindHomeAdapter.FindHomeItemHolder> {

    private FindHomeItemHolder findHomeItemHolder;
    private List<FindHomeLandingPageData> findHomeItems;
    private Context context;


    public FindHomeAdapter(Context context, List<FindHomeLandingPageData> findHomeItems) {
        this.context=context;
        this.findHomeItems=findHomeItems;
    }

    @Override
    public FindHomeItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_find_home_lading_list_item, parent, false);
        findHomeItemHolder = new FindHomeAdapter.FindHomeItemHolder(view);
        return findHomeItemHolder;
    }

    @Override
    public void onBindViewHolder(FindHomeItemHolder holder, int position) {

        holder.fh_itemName.setText(findHomeItems.get(position).itemName);
        //holder.fh_itemImg.setImageResource(findHomeItems.get(position).itemImg);

    }

    @Override
    public int getItemCount() {
        return findHomeItems.size();
    }


    class FindHomeItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView fh_itemName;
        ImageView fh_itemImg;


        FindHomeItemHolder(View itemView)
        {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            fh_itemName = (TextView) itemView.findViewById(R.id.fh_textView);
            fh_itemImg = (ImageView) itemView.findViewById(R.id.fh_imageView);
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() == 0) {
                Intent intent = new Intent(context, AreaGuideActivity.class);
                context.startActivity(intent);
            }
        }


    }
}
