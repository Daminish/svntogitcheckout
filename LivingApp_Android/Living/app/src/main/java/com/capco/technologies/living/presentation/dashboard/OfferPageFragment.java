package com.capco.technologies.living.presentation.dashboard;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.Offer;
import com.capco.technologies.living.presentation.base.BaseFragment;
import com.capco.technologies.living.presentation.ui.custom.roudedimageview.RoundedImageView;

/**
 * Created by sachin on 06/01/18.
 */

public class OfferPageFragment extends BaseFragment {

    private Offer offer = null;


    public static OfferPageFragment newInstance(Offer offer) {

        OfferPageFragment fragment = new OfferPageFragment();
        fragment.offer = offer;

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_dashboard_offer, container, false);


        RoundedImageView imageView = view.findViewById(R.id.img_offer_poster);

        TextView title = view.findViewById(R.id.tv_offer_title);
        TextView desc = view.findViewById(R.id.tv_offer_short_desc);


        int imageId = container.getResources().getIdentifier(offer.imageUrl, "drawable", container.getContext().getPackageName());
        RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeResource(getResources(), imageId));
        dr.setCornerRadius(500);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), imageId));
        title.setText(offer.title);
        desc.setText(offer.shortDesctiption);


        return view;
    }
}
