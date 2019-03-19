package com.siyuan.enjoyreading.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.androidapp.fragment.BaseTabFragment;
import com.androidapp.upgrade.UpdateAppManager;
import com.androidapp.utils.StatusBarUtil;
import com.androidapp.widget.AppAlertDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.api.Urls;
import com.siyuan.enjoyreading.ui.activity.SocializCircleActivity;
import com.siyuan.enjoyreading.ui.activity.currency.AppWebActivity;
import com.siyuan.enjoyreading.ui.activity.login.PassWordLoginActivity;
import com.siyuan.enjoyreading.ui.activity.pcenter.CouponActivity;
import com.siyuan.enjoyreading.ui.activity.pcenter.PersonalWallet;
import com.siyuan.enjoyreading.ui.activity.pcenter.SettingActivity;
import com.siyuan.enjoyreading.ui.activity.pcenter.SettingFavorite;
import com.siyuan.enjoyreading.ui.activity.pcenter.SettingLeavingMessage;
import com.siyuan.enjoyreading.ui.activity.pcenter.SettingsDownloadActivity;
import com.siyuan.enjoyreading.ui.activity.pcenter.SettingsMyInfoActivity;
import com.siyuan.enjoyreading.util.GlideCircleTransform;
import com.siyuan.enjoyreading.util.OkGoUpdateHttpUtil;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class PCenterFragment extends BaseTabFragment {

    @Override
    protected void loadData(boolean force) {
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //状态栏透明和间距处理
        StatusBarUtil.immersive(getActivity());

        view.findViewById(R.id.my_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PersonalWallet.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.authentication_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AppAlertDialog dialog = new AppAlertDialog(getContext(), false);
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

        view.findViewById(R.id.socializcircle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, SocializCircleActivity.class));
            }
        });
        Glide.with(getContext())
                .load(R.mipmap.image_avatar_5)
                .apply(new RequestOptions().placeholder(R.drawable.iv_default_photo).transform(new GlideCircleTransform(getContext())))
                .into((ImageView) view.findViewById(R.id.ivSettingAvatar));
        view.findViewById(R.id.item_setting).setOnClickListener(mOnClickListener);
        view.findViewById(R.id.setting_download).setOnClickListener(mOnClickListener);
        view.findViewById(R.id.setting_favorite).setOnClickListener(mOnClickListener);
        view.findViewById(R.id.setting_leaving_message).setOnClickListener(mOnClickListener);
        view.findViewById(R.id.setting_share_reward).setOnClickListener(mOnClickListener);
        view.findViewById(R.id.setting_inviting_friends).setOnClickListener(mOnClickListener);
        view.findViewById(R.id.ivSettingAvatar).setOnClickListener(mOnClickListener);
        view.findViewById(R.id.my_redpack).setOnClickListener(mOnClickListener);
        view.findViewById(R.id.ll_userinfo_area).setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_setting:
                    startActivity(new Intent(mContext, SettingActivity.class));
                    break;
                case R.id.my_redpack:
                    startActivity(new Intent(mContext, CouponActivity.class));
                    /*ActionSheet.OnSheetItemClickListener listener = new ActionSheet.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {

                        }
                    };
                    ActionSheet actionSheet = new ActionSheet(getContext());
                    actionSheet.builder().addSheetItem("你好", R.color.black, listener).addSheetItem("你好a", R.color.black, listener).setCancelable(false).show();*/
                    /*LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(getContext())
                            .setMessage("加载中...")
                            .setCancelable(true)
                            .setCancelOutside(true);
                    LoadingDialog dialog = loadBuilder.create();
                    dialog.show();*/
                    break;
                case R.id.setting_download:
                    startActivity(new Intent(mContext, SettingsDownloadActivity.class));
                    break;
                case R.id.setting_favorite:
                    startActivity(new Intent(mContext, SettingFavorite.class));
                    break;
                case R.id.setting_leaving_message:
                    startActivity(new Intent(mContext, SettingLeavingMessage.class));
                    break;
                case R.id.setting_share_reward:
                    doStartActivity(AppWebActivity.class, AppWebActivity.getBundle(Urls.H5.INVITING_FRIENDS, "分享有赏"));
                    break;
                case R.id.setting_inviting_friends:
                    doStartActivity(AppWebActivity.class, AppWebActivity.getBundle(Urls.H5.SHARE_REWARD, "邀请好友"));
                    break;
                case R.id.ivSettingAvatar:
                    startActivity(new Intent(mContext, SettingsMyInfoActivity.class));
                    break;
                case R.id.ll_userinfo_area:
                    startActivity(new Intent(mContext, PassWordLoginActivity.class));
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.act_personal_center;
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
