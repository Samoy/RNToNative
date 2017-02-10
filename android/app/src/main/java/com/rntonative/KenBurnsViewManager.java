package com.rntonative;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Samoy on 2017/2/10.
 */

public class KenBurnsViewManager extends SimpleViewManager<KenBurnsView> {

    private static final String REACT_CLASS = "KenBurnsView";
    private ThemedReactContext mContext;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected KenBurnsView createViewInstance(ThemedReactContext reactContext) {
        mContext = reactContext;
        KenBurnsView view = new KenBurnsView(mContext);
        try {
            InputStream ims = mContext.getAssets().open("beijing.jpg");
            Drawable drawable = Drawable.createFromStream(ims, null);
            view.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    @ReactProp(name = "imgSource")
    public void setSource(KenBurnsView view, String source) {
        try {
            InputStream ims = mContext.getAssets().open(source);
            Drawable drawable = Drawable.createFromStream(ims, null);
            view.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
