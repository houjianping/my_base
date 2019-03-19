package com.androidapp.share.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.androidapp.share.bean.ShareContent;
import com.androidapp.share.bean.ShareEnum;
import com.androidapp.share.dialog.OnShareItemClickListener;
import com.androidapp.share.dialog.ShareDialog;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;

public class ShareHelper {

    private Activity mContext;
    private ShareCallback mShareCallback;
    private ShareDialog mShareDialog;
    private ShareContent mShareContent;
    private int mDefaultLogo;
    private ShareEnum mCurrentShareEnum;

    public ShareHelper(Activity context, String shareTitle, int appLogo) {
        mContext = context;
        mDefaultLogo = appLogo;
        mShareCallback = null;
        init(shareTitle);
    }

    private void init(String shareTitle) {
        mShareDialog = new ShareDialog(mContext)
                .title(shareTitle)
                .orientation(ShareDialog.HORIZONTAL)
                .inflateMenu(new OnShareItemClickListener() {
                    @Override
                    public void click(ShareEnum item) {
                        mCurrentShareEnum = item;
                        switch (item) {
                            case qq:
                                doShareAction(SHARE_MEDIA.QQ);
                                break;
                            case qzone:
                                doShareAction(SHARE_MEDIA.QZONE);
                                break;
                            case weibo:
                                doShareAction(SHARE_MEDIA.SINA);
                                break;
                            case wechat:
                                doShareAction(SHARE_MEDIA.WEIXIN);
                                break;
                            case moments:
                                doShareAction(SHARE_MEDIA.WEIXIN_CIRCLE);
                                break;
                            default:
                                break;
                        }
                    }
                });
    }

    public void show(ShareContent shareContent) {
        mShareContent = shareContent;
        mShareDialog.show();
    }

    public void setShareCallback(ShareCallback shareCallback) {
        mShareCallback = shareCallback;
    }

    /**
     * 使用友盟进行分享--分享指定平台
     */
    private void doShareAction(SHARE_MEDIA shareMedia) {
        UMImage imgUrl;
        if (!TextUtils.isEmpty(mShareContent.getLogo())) {
            imgUrl = new UMImage(mContext, mShareContent.getLogo());
        } else {
            imgUrl = new UMImage(mContext, mDefaultLogo);
        }
        imgUrl.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色

        ShareAction shareAction = new ShareAction(mContext);

        if (SHARE_MEDIA.SMS == shareMedia) {
            UMWeb umWeb = new UMWeb(mShareContent.getUrl());
            umWeb.setTitle(mShareContent.getTitle());//标题
            umWeb.setThumb(imgUrl);  //缩略图
            umWeb.setDescription(mShareContent.getText());//描述
        } else if (mShareContent.getShareObject() == 0) { // 网页分享
            UMWeb umWeb = new UMWeb(mShareContent.getUrl());
            umWeb.setTitle(mShareContent.getTitle());//标题
            umWeb.setThumb(imgUrl);  //缩略图
            umWeb.setDescription(mShareContent.getText());//描述
            shareAction.withMedia(umWeb);
        } else if (mShareContent.getShareObject() == 1) { //图片分享
            UMImage image = new UMImage(mContext, mShareContent.getUrl());
            image.compressStyle = UMImage.CompressStyle.SCALE;
            image.compressStyle = UMImage.CompressStyle.QUALITY;
            image.setTitle(mShareContent.getTitle());//标题
            image.setDescription(mShareContent.getText());
            shareAction.withMedia(image);
        } else if (mShareContent.getShareObject() == 2) {
            UMVideo video = new UMVideo(mShareContent.getUrl());
            video.setTitle(mShareContent.getTitle());//视频的标题
            video.setThumb(imgUrl);//视频的缩略图
            video.setDescription(mShareContent.getText());//视频的描述
            shareAction.withMedia(video);
        }
        shareAction.withText(mShareContent.getText());
        shareAction.setPlatform(shareMedia);
        shareAction.setCallback(mUMShareListener);
        shareAction.share();
    }

    private UMShareListener mUMShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            if (mShareCallback != null) {
                mShareCallback.onShareStart(mCurrentShareEnum);
            }
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            if (mShareCallback != null) {
                mShareCallback.onShareSuccess(mCurrentShareEnum);
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            if (mShareCallback != null) {
                mShareCallback.onShareFailed(mCurrentShareEnum);
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            if (mShareCallback != null) {
                mShareCallback.onShareCancel(mCurrentShareEnum);
            }
        }
    };

    /**
     * 分享回调函数
     */
    public interface ShareCallback {
        /**
         * 开始分享
         */
        void onShareStart(ShareEnum shareEnum);

        /**
         * 分享成功
         */
        void onShareSuccess(ShareEnum shareEnum);

        /**
         * 分享失败
         */
        void onShareFailed(ShareEnum shareEnum);

        /**
         * 取消分享
         */
        void onShareCancel(ShareEnum shareEnum);
    }
}
