package com.scwang.refreshlayout.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.androidapp.base.fragment.BaseTabFragment;
import com.androidapp.base.utils.StatusBarUtil;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.util.DensityUtil;
import com.androidapp.widget.CustomAlertDialog;
import com.androidapp.widget.LoadingDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.ui.coll.ActCollapsingToolBar;
import com.scwang.refreshlayout.util.GlideCircleTransform;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class PersonalMainFragment extends BaseTabFragment {

    private int mOffset = 0;
    private int mScrollY = 0;
    private String mUpdateUrl = "https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/json/json.txt";

    @Override
    protected void loadData(boolean force) {
        if (!force)
            return;
        /*new UpdateAppManager
                .Builder()
                //当前Activity
                .setActivity(getActivity())
                //更新地址
                .setUpdateUrl(mUpdateUrl)
                //实现httpManager接口的对象
                .setHttpManager(new OkGoUpdateHttpUtil())
                .build()
                .update();*/
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        //状态栏透明和间距处理
        StatusBarUtil.immersive(getActivity());
        StatusBarUtil.setPaddingSmart(getActivity(), toolbar);

        final View parallax = view.findViewById(R.id.parallax);
        final View buttonBar = view.findViewById(R.id.buttonBarLayout);
        final NestedScrollView scrollView = (NestedScrollView) view.findViewById(R.id.scrollView);
        final RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);

        view.findViewById(R.id.my_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent intent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(intent, 100);*/
                Intent intent = new Intent(mContext, ActCollapsingToolBar.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.authentication_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomAlertDialog dialog = new CustomAlertDialog(getContext(), false);
                dialog.setTitle("关注");
                dialog.setMessage("点击了关注点击了关注点击了关注点击了关注", getResources().getColor(R.color.black_main));
                dialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        view.findViewById(R.id.my_redpack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(getContext())
                        .setMessage("加载中...")
                        .setCancelable(true)
                        .setCancelOutside(true);
                LoadingDialog dialog = loadBuilder.create();
                dialog.show();
            }
        });

        view.findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(PersonalMainFragment.this)
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(-1)
                        .maxSelectNum(9)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .selectionMode(false ?
                                PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .enableCrop(true)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .cropCompressQuality(90)// 裁剪压缩质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .rotateEnabled(false) // 裁剪是否可旋转图片
                        .videoMaxSecond(20)//显示多少秒以内的视频or音频也可适用
                        .recordVideoSecond(20)//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private int lastScrollY = 0;
            private int h = DensityUtil.dp2px(50);
            private int color = /*ContextCompat.getColor(getContext(), R.color.colorPrimary) & 0x00ffffff*/ 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    buttonBar.setAlpha(1f * mScrollY / h);
                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    parallax.setTranslationY(mOffset - mScrollY);
                }
                lastScrollY = scrollY;
            }
        });
        buttonBar.setAlpha(0);
        toolbar.setBackgroundColor(0);
        Glide.with(getContext())
                .load(R.mipmap.image_avatar_5)
                .apply(new RequestOptions().placeholder(R.drawable.iv_default_photo).transform(new GlideCircleTransform(getContext())))
                .into((ImageView) view.findViewById(R.id.ivSettingAvatar));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_main_test3;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        Log.i("图片-----》", media.getPath());
                    }
                    break;
            }
        }
    }
}
