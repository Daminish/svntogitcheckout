package com.capco.technologies.living.presentation.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.capco.technologies.living.R;

/**
 * Created by sachin on 06/01/18.
 */

public class BaseActivity extends AppCompatActivity {

    Dialog overlayDialog;
    Toolbar toolbar;


    public void onCreate(@Nullable Bundle savedInstanceState, int layoutResId) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResId);
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }


//    protected void setTitle(String title) {
//        if (toolbar != null) {
//            toolbar.setTitle(title);
//        }
//    }

    public void showOverlayDialog(boolean isShow) {

        if (isShow) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

//        if (isShow) {
//
//            hideOverlayDialog();
//
//
//            overlayDialog = new Dialog(activity, android.R.style.Theme_Panel);
//            overlayDialog.setCancelable(true);
//            overlayDialog.show();
//        } else {
//            hideOverlayDialog();
//        }
    }

    private void hideOverlayDialog() {
        if (overlayDialog != null && overlayDialog.isShowing()) {
            overlayDialog.dismiss();
            overlayDialog = null;
        }
    }


    protected void hideKeyboard(View view) {

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    protected void setBannerText(String bannerText) {
        TextView textView = findViewById(R.id.txt_page_banner);

        boolean nullBannerText = TextUtils.isEmpty(bannerText);
        if (textView != null) {
            textView.setVisibility(nullBannerText ? View.GONE : View.VISIBLE);
            textView.setText(nullBannerText ? "" : bannerText);
        }
    }


}
