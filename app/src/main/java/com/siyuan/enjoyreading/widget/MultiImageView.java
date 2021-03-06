package com.siyuan.enjoyreading.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.androidapp.utils.DensityUtil;
import com.androidapp.utils.ImageLoaderUtils;
import com.androidapp.widget.AppRoundImageView;
import com.siyuan.enjoyreading.R;

import java.io.File;
import java.util.List;

/**
 * des:显示1~N张图片的View
 * Created by xsf
 * on 2016.07.11:11
 */
public class MultiImageView extends LinearLayout {
    public static int MAX_WIDTH = 0;

    // 照片的Url列表
    private List<String> imagesList;

    /**
     * 长度 单位为Pixel
     **/
    private int pxOneMaxWandH;  // 单张图最大允许宽高
    private int pxTwoH;  // 单张图最大允许宽高
    private int pxThreeH = 0;// 多张图的宽高
    private int pxImagePadding = 0;

    private int MAX_PER_ROW_COUNT = 3;// 每行显示最大数

    private LayoutParams oneParams;
    private LayoutParams moreTwo, threeParams;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public MultiImageView(Context context) {
        super(context);
        pxImagePadding = DensityUtil.dip2px(getContext(), 3);// 图片间的间距
    }

    public MultiImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        pxImagePadding = DensityUtil.dip2px(getContext(), 3);// 图片间的间距
    }

    public void setList(List<String> lists) throws IllegalArgumentException {
        if (lists == null) {
            throw new IllegalArgumentException("imageList is null...");
        }
        imagesList = lists;

        if (MAX_WIDTH > 0) {
            pxThreeH = (MAX_WIDTH - pxImagePadding * 3) / 3; //解决右侧图片和内容对不齐问题
            pxTwoH = (MAX_WIDTH - pxImagePadding * 2) / 2; //解决右侧图片和内容对不齐问题
            pxOneMaxWandH = MAX_WIDTH * 2 / 3;
            initImageLayoutParams();
        }

        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MAX_WIDTH == 0) {
            int width = measureWidth(widthMeasureSpec);
            if (width > 0) {
                MAX_WIDTH = width;
                if (imagesList != null && imagesList.size() > 0) {
                    setList(imagesList);
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            // result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
            // + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private void initImageLayoutParams() {
        int wrap = LayoutParams.WRAP_CONTENT;
        int match = LayoutParams.MATCH_PARENT;
        oneParams = new LayoutParams(match, wrap);

        threeParams = new LayoutParams(pxThreeH, pxThreeH);
        threeParams.setMargins(pxImagePadding, 0, pxImagePadding, 0);

        moreTwo = new LayoutParams(pxTwoH, pxTwoH);
        moreTwo.setMargins(pxImagePadding, 0, pxImagePadding, 0);
    }

    // 根据imageView的数量初始化不同的View布局,还要为每一个View作点击效果
    private void initView() {
        this.setOrientation(VERTICAL);
        this.removeAllViews();
        if (MAX_WIDTH == 0) {
            //为了触发onMeasure()来测量MultiImageView的最大宽度，MultiImageView的宽设置为match_parent
            addView(new View(getContext()));
            return;
        }

        if (imagesList == null || imagesList.size() == 0) {
            return;
        }

        if (imagesList.size() == 1) {
            addView(createImageView(0, 1));
        } else {
            int allCount = imagesList.size();
            if (allCount == 4 ) {
                MAX_PER_ROW_COUNT = 2;
            } else {
                MAX_PER_ROW_COUNT = 3;
            }
            int rowCount = allCount / MAX_PER_ROW_COUNT
                    + (allCount % MAX_PER_ROW_COUNT > 0 ? 1 : 0);// 行数
            for (int rowCursor = 0; rowCursor < rowCount; rowCursor++) {
                LinearLayout rowLayout = new LinearLayout(getContext());
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);

                rowLayout.setLayoutParams(oneParams);
                if (rowCursor != 0) {
                    rowLayout.setPadding(0, pxImagePadding, 0, 0);
                }

                int columnCount = allCount % MAX_PER_ROW_COUNT == 0 ? MAX_PER_ROW_COUNT
                        : allCount % MAX_PER_ROW_COUNT;//每行的列数
                if (rowCursor != rowCount - 1) {
                    columnCount = MAX_PER_ROW_COUNT;
                }
                addView(rowLayout);

                int rowOffset = rowCursor * MAX_PER_ROW_COUNT;// 行偏移
                for (int columnCursor = 0; columnCursor < columnCount; columnCursor++) {
                    int position = columnCursor + rowOffset;
                    rowLayout.addView(createImageView(position, MAX_PER_ROW_COUNT));
                }
            }
        }
    }

    private ImageView createImageView(int position, int column) {
        String url = imagesList.get(position);
        AppRoundImageView imageView = new AppRoundImageView(getContext());
        if (column == 1) {
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            imageView.setMaxHeight(pxOneMaxWandH);
            imageView.setLayoutParams(oneParams);
        } else if (column == 2) {
            imageView.setScaleType(ScaleType.CENTER_CROP);
            imageView.setLayoutParams(moreTwo);
        } else {
            imageView.setScaleType(ScaleType.CENTER_CROP);
            imageView.setLayoutParams(threeParams);
        }

        imageView.setTag(R.string.zone_img_position, position);
        imageView.setId(url.hashCode());
        imageView.setOnClickListener(mImageViewOnClickListener);
        ImageLoaderUtils.display(getContext(), imageView, getImageUrl(url));
        return imageView;
    }

    // 图片点击事件
    private OnClickListener mImageViewOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, (Integer) view.getTag(R.string.zone_img_position));
            }
        }
    };

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private static String BASE_PHOTO_URL = "";

    public static String getImageUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            if (url.contains("http") || new File(url).isFile()) {
                return url;
            } else {
                return BASE_PHOTO_URL + url;
            }
        } else {
            return "";
        }
    }
}
