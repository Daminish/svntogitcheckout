package com.capco.technologies.living.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.capco.technologies.living.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by test on 04/01/18.
 */

public class ViewPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener
{

    private Context mContext; //  context
    private LayoutInflater mInflater;
    private LinkedList<View> mViews;
    private List<Drawable> mList=new ArrayList<>();
    private ViewPager mViewPager;

    public ViewPagerAdapter(Context context, List<Drawable> list, ViewPager viewPager)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
        mList=list;

        if (list != null)
        {
            // Whether or not more than 1 individual ， To initialize the first （index:0）
            mViews = new LinkedList<View>();
            ImageView view = (ImageView) mInflater.inflate(R.layout.for_view_pager, null);
            Drawable drawable = list.get(list.size() - 1);
            view.setImageDrawable(drawable);

            mViews.add(view);

            if( list.size() > 1) { // More than 1 To cycle
                for (Drawable d : list) {
                    ImageView v = (ImageView) mInflater.inflate(R.layout.for_view_pager, null);
                    v.setImageDrawable(d);
                    mViews.add(v);
                }
                // The last one （index:N+1）
                view = (ImageView) mInflater.inflate(R.layout.for_view_pager, null);
                drawable = mList.get(0);
                view.setImageDrawable(drawable);
                mViews.add(view);
            }
        }
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(mViews.get(position));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        if ( mViews.size() > 1) { // More than 1， Cycle jump
            if ( position < 1) { // First before ， Jump to the end （N）
                position = mList.size(); // Notice here is mList， Instead of mViews
                mViewPager.setCurrentItem(position, false);
            } else if ( position > mList.size()) { // After the last ， Jump to the top （1）
                mViewPager.setCurrentItem(1, false); //false: Animation that does not display the jump process
                position = 1;
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}
