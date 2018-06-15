package com.androidapp.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.androidapp.banner.transformer.AccordionTransformer;
import com.androidapp.banner.transformer.BackgroundToForegroundTransformer;
import com.androidapp.banner.transformer.CubeInTransformer;
import com.androidapp.banner.transformer.CubeOutTransformer;
import com.androidapp.banner.transformer.DefaultTransformer;
import com.androidapp.banner.transformer.DepthPageTransformer;
import com.androidapp.banner.transformer.FlipHorizontalTransformer;
import com.androidapp.banner.transformer.FlipVerticalTransformer;
import com.androidapp.banner.transformer.ForegroundToBackgroundTransformer;
import com.androidapp.banner.transformer.RotateDownTransformer;
import com.androidapp.banner.transformer.RotateUpTransformer;
import com.androidapp.banner.transformer.ScaleInOutTransformer;
import com.androidapp.banner.transformer.StackTransformer;
import com.androidapp.banner.transformer.TabletTransformer;
import com.androidapp.banner.transformer.ZoomInTransformer;
import com.androidapp.banner.transformer.ZoomOutSlideTransformer;
import com.androidapp.banner.transformer.ZoomOutTranformer;

public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
