package com.siyuan.enjoyreading.api;

import android.util.Log;

import com.androidapp.utils.ToastUtils;
import com.google.gson.annotations.SerializedName;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.siyuan.enjoyreading.api.request.HttpRequest;
import com.siyuan.enjoyreading.api.request.JsonCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Urls {

    private static boolean isDevMode() {
        return true;
    }

    public static String getBaseUrl() {
        String BASE_URL_ONLINE = "";
        String BASE_URL_DEV = "";
        return isDevMode() ? BASE_URL_DEV : BASE_URL_ONLINE;
    }

    public static class H5 {
        // 分享赚钱
        public static String SHARE_REWARD = "";
        // 邀请好友
        public static String INVITING_FRIENDS = "";
        //联系我们
        public static String CONTACT_US = "";
        //服务条款
        public static String TERMS_OF_SERVICE = "http://m.lrts.me/h5/help/privacy_android?uid=253157424&mparam=9UIOzbldSimtgacznv75IXLcvSwtmfTI1RwOh1a1lL7CTwSPwxJeIeHp46c0g6X%2BVFuayucMrs2merUidWz6eoFr0LyGhSW7K%2BTAcGWgJQc%3D";
        //帮助与反馈
        public static String FEEDBACK_CONTROL = "http://m.lrts.me/h5/help/index";
    }

    public static void test() {
        HttpRequest httpRequest = new HttpRequest();
        Map<String, String> params = new HashMap<>();
        params.put("scope", "103");
        params.put("format", "json");
        params.put("appid", "379020");
        params.put("bk_key", "关键字关键字");
        params.put("bk_length", "600");
        httpRequest.asyncGet("http://baike.baidu.com/api/openapi/BaikeLemmaCardApi", params, new JsonCallback<Info>() {
            @Override
            public void onSuccess(Response<Info> response) {
                Log.e("","#############onSuccess##############" + response.body().getErrno());
            }
        });
    }

    public static class Info {
        public int getErrno() {
            return errno;
        }

        public void setErrno(int errno) {
            this.errno = errno;
        }

        private int errno;
        private int id;
        private int subLemmaId;
        private int newLemmaId;
        private String key;
        private String desc;
        private String title;
        private String image;
        private String src;
        private int imageHeight;
        private int imageWidth;
        private String isSummaryPic;
        @SerializedName("abstract")
        private String abstractX;
        private String url;
        private String wapUrl;
        private int hasOther;
        private String totalUrl;
        private String logo;
        private String copyrights;
        private String customImg;
        private List<CardBean> card;
        private List<?> moduleIds;
        private List<String> catalog;
        private List<String> wapCatalog;
        private List<?> redirect;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSubLemmaId() {
            return subLemmaId;
        }

        public void setSubLemmaId(int subLemmaId) {
            this.subLemmaId = subLemmaId;
        }

        public int getNewLemmaId() {
            return newLemmaId;
        }

        public void setNewLemmaId(int newLemmaId) {
            this.newLemmaId = newLemmaId;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        public String getIsSummaryPic() {
            return isSummaryPic;
        }

        public void setIsSummaryPic(String isSummaryPic) {
            this.isSummaryPic = isSummaryPic;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWapUrl() {
            return wapUrl;
        }

        public void setWapUrl(String wapUrl) {
            this.wapUrl = wapUrl;
        }

        public int getHasOther() {
            return hasOther;
        }

        public void setHasOther(int hasOther) {
            this.hasOther = hasOther;
        }

        public String getTotalUrl() {
            return totalUrl;
        }

        public void setTotalUrl(String totalUrl) {
            this.totalUrl = totalUrl;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCopyrights() {
            return copyrights;
        }

        public void setCopyrights(String copyrights) {
            this.copyrights = copyrights;
        }

        public String getCustomImg() {
            return customImg;
        }

        public void setCustomImg(String customImg) {
            this.customImg = customImg;
        }

        public List<CardBean> getCard() {
            return card;
        }

        public void setCard(List<CardBean> card) {
            this.card = card;
        }

        public List<?> getModuleIds() {
            return moduleIds;
        }

        public void setModuleIds(List<?> moduleIds) {
            this.moduleIds = moduleIds;
        }

        public List<String> getCatalog() {
            return catalog;
        }

        public void setCatalog(List<String> catalog) {
            this.catalog = catalog;
        }

        public List<String> getWapCatalog() {
            return wapCatalog;
        }

        public void setWapCatalog(List<String> wapCatalog) {
            this.wapCatalog = wapCatalog;
        }

        public List<?> getRedirect() {
            return redirect;
        }

        public void setRedirect(List<?> redirect) {
            this.redirect = redirect;
        }

        public static class CardBean {
            /**
             * key : m25_nameC
             * name : 中文名
             * value : ["关键字"]
             * format : ["关键字"]
             */

            private String key;
            private String name;
            private List<String> value;
            private List<String> format;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getValue() {
                return value;
            }

            public void setValue(List<String> value) {
                this.value = value;
            }

            public List<String> getFormat() {
                return format;
            }

            public void setFormat(List<String> format) {
                this.format = format;
            }
        }
    }
}
