package com.scwang.refreshlayout.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.banner.Banner;
import com.androidapp.banner.listener.OnBannerClickListener;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.banner.loader.ImageLoader;
import com.androidapp.base.activity.BaseListActivity;
import com.androidapp.base.adapter.BaseQuickAdapter;
import com.androidapp.base.adapter.BaseViewHolder;
import com.androidapp.share.bean.ShareContent;
import com.androidapp.share.bean.ShareEnum;
import com.androidapp.share.util.ShareUtil;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.refreshlayout.R;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends BaseListActivity {

    private BaseQuickAdapter mBaseQuickAdapter;

    @Override
    protected void initView() {
        super.initView();
        //添加Header
        View header = LayoutInflater.from(this).inflate(R.layout.listitem_movie_header, mRecyclerView, false);
        Banner banner = (Banner) header;
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(BANNER_ITEMS);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                Toast.makeText(getBaseContext(), "si=" + i, Toast.LENGTH_SHORT).show();
            }
        });
        banner.start();
        addHeaderView(banner, 0);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleBar.setTitle("测试列表");
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.more);
        imageView.setPadding(0,0,15,0);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil shareUtil = new ShareUtil(TestActivity.this,"分享标题", R.mipmap.ic_launcher);
                shareUtil.setShareCallback(new ShareUtil.ShareCallback() {
                    @Override
                    public void onShareStart(ShareEnum shareEnum) {
                    }
                    @Override
                    public void onShareSuccess(ShareEnum shareEnum) {
                    }
                    @Override
                    public void onShareFailed(ShareEnum shareEnum) {
                    }
                    @Override
                    public void onShareCancel(ShareEnum shareEnum) {
                    }
                });
                ShareContent shareContent = new ShareContent();
                shareContent.setUrl("");
                shareContent.setTitle("");
                shareContent.setLogo("");
                shareContent.setText("");
                shareContent.setShareObject(1);
                shareUtil.show(shareContent);
            }
        });
        mTitleBar.setRightView(imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final List<Movie> movies = new Gson().fromJson(JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
        }.getType());
        mBaseQuickAdapter.replaceData(movies);
    }

    @Override
    protected OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

    @Override
    protected OnLoadMoreListener getOnLoadMoreListener() {
        return mOnLoadMoreListener;
    }

    @Override
    protected BaseQuickAdapter getListViewAdapter() {
        if (mBaseQuickAdapter == null) {
            mBaseQuickAdapter = new QuickAdapter();
        }
        return mBaseQuickAdapter;
    }

    private OnRefreshListener mOnRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.getLayout().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mBaseQuickAdapter.getItemCount() < 2) {
                        List<Movie> movies = new Gson().fromJson(JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
                        }.getType());
                        mBaseQuickAdapter.replaceData(movies);
                    }
                    mRefreshLayout.finishRefresh();
                }
            }, 2000);
        }
    };

    private OnLoadMoreListener mOnLoadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            final List<Movie> movies = new Gson().fromJson(JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
            }.getType());
            mBaseQuickAdapter.addData(movies);
            refreshLayout.finishLoadMoreWithNoMoreData();
        }
    };

    public class QuickAdapter extends BaseQuickAdapter<Movie, BaseViewHolder> {
        public QuickAdapter() {
            super(R.layout.listitem_movie_item);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, Movie item) {
            viewHolder.setText(R.id.lmi_title, item.filmName)
                    .setText(R.id.lmi_actor, item.actors)
                    .setText(R.id.lmi_grade, item.grade)
                    .setText(R.id.lmi_describe, item.shortinfo).addOnClickListener(R.id.movie_item);
            Glide.with(mContext).load(item.picaddr).into((ImageView) viewHolder.getView(R.id.lmi_avatar));
        }
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(((BannerItem) path).pic);
        }
    }

    public static class Movie {
        public String actors;
        public String filmName;
        public String grade;
        public String info;
        public String picaddr;
        public String shortinfo;
    }

    public static class BannerItem {

        public int pic;
        public String title;

        public BannerItem() {
        }

        public BannerItem(String title, int pic) {
            this.pic = pic;
            this.title = title;
        }
    }

    public static List<BannerItem> BANNER_ITEMS = new ArrayList<BannerItem>() {{
        add(new BannerItem("最后的骑士", R.mipmap.image_movie_header_48621499931969370));
        add(new BannerItem("三生三世十里桃花", R.mipmap.image_movie_header_12981501221820220));
        add(new BannerItem("豆福传", R.mipmap.image_movie_header_12231501221682438));
    }};


    public static String JSON_MOVIES = "[" +
            "{\"actors\":\"丹尼斯·威缇可宁|Emma|Nikki|Jiayao|Wang|Maggie|Mao|Gang-yun|Sa\",\"filmName\":\"神灵寨\",\"grade\":\"5.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg\",\"releasedate\":\"2017-07-31\",\"shortinfo\":\"父亲忽病危 新娘真够黑\",\"type\":\"剧情|喜剧\"}," +
            "{\"actors\":\"刘亦菲|杨洋|彭子苏|严屹宽|罗晋\",\"filmName\":\"三生三世十里桃花\",\"grade\":\"9.2\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg\",\"releasedate\":\"2017-08-03\",\"shortinfo\":\"虐心姐弟恋 颜值要逆天\",\"type\":\"剧情|爱情|奇幻\"}," +
            "{\"actors\":\"尹航|代旭|李晨浩|衣云鹤|张念骅\",\"filmName\":\"谁是球王\",\"grade\":\"10.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3750.jpg\",\"releasedate\":\"2017-08-03\",\"shortinfo\":\"足球变人生 再战可辉煌\",\"type\":\"剧情|喜剧\"}," +
            "{\"actors\":null,\"filmName\":\"大象林旺之一夜成名\",\"grade\":\"10.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3757.jpg\",\"releasedate\":\"2017-08-04\",\"shortinfo\":\"大象参二战 一生好伙伴\",\"type\":\"动作|动画|战争|冒险\"}," +
            "{\"actors\":\"薛凯琪|陈意涵|张钧甯|迈克·泰森\",\"filmName\":\"闺蜜2：无二不作\",\"grade\":\"8.3\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3776.jpg\",\"releasedate\":\"2017-08-04\",\"shortinfo\":\"闺蜜团出战 会一会新娘\",\"type\":\"喜剧|爱情\"}," +
            "{\"actors\":\"彭禺厶|王萌|周凯文|曹琦|孟子叶\",\"filmName\":\"诡井\",\"grade\":\"5.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3824.jpg\",\"releasedate\":\"2017-08-04\",\"shortinfo\":\"午夜深井中 怨魂欲现形\",\"type\":\"恐怖|惊悚\"}," +
            "{\"actors\":\"旺卓措|刘承宙|高欣生|段楠|来钰\",\"filmName\":\"荒野加油站\",\"grade\":\"5.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3821.jpg\",\"releasedate\":\"2017-08-04\",\"shortinfo\":\"夜半拉乘客 结果遇不测\",\"type\":\"惊悚|悬疑\"}," +
            "{\"actors\":\"刘佩琦|曹云金|罗昱焜\",\"filmName\":\"龙之战\",\"grade\":\"5.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3778.jpg\",\"releasedate\":\"2017-08-04\",\"shortinfo\":\"持倭刀屹立 抗外敌救国\",\"type\":\"动作|战争|历史\"}," +
            "{\"actors\":\"金巴|曲尼次仁|夏诺.扎西敦珠|索朗尼玛|益西旦增\",\"filmName\":\"皮绳上的魂\",\"grade\":\"5.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3801.jpg\",\"releasedate\":\"2017-08-04\",\"shortinfo\":\"走完朝圣路 又上降魔旅\",\"type\":\"剧情\"}," +
            "{\"actors\":\"严丽祯|李晔|王衡|李传缨|李心仪\",\"filmName\":\"玩偶奇兵\",\"grade\":\"10.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3779.jpg\",\"releasedate\":\"2017-08-04\",\"shortinfo\":\"玩偶战数码 一头两个大\",\"type\":\"动画|冒险|奇幻\"}," +
            "{\"actors\":\"斯蒂芬·马布里|吴尊|何冰|郑秀妍|王庆祥\",\"filmName\":\"我是马布里\",\"grade\":\"0.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3810.jpg\",\"releasedate\":\"2017-08-04\",\"shortinfo\":\"吴尊助冠军 热血灌篮魂\",\"type\":\"剧情|运动\"}," +
            "{\"actors\":\"周鹏雨|穆建荣|陈泽帆|鹿露|宋星成\",\"filmName\":\"原罪的羔羊\",\"grade\":\"5.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3802.jpg\",\"releasedate\":\"2017-08-04\",\"shortinfo\":\"古镇来戏班 往事不一般\",\"type\":\"悬疑\"}," +
            "{\"actors\":\"王大陆|张天爱|任达华|盛冠森|王迅\",\"filmName\":\"鲛珠传\",\"grade\":\"7.1\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3777.jpg\",\"releasedate\":\"2017-08-04\",\"shortinfo\":\"改编热IP 杠杠号召力\",\"type\":\"喜剧|动作|奇幻\"}," +
            "{\"actors\":\"成龙|罗伯特·雷德福\",\"filmName\":\"地球：神奇的一天\",\"grade\":\"10.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3803.jpg\",\"releasedate\":\"2017-08-11\",\"shortinfo\":\"史诗纪录片 十年再相见\",\"type\":\"纪录片\"}," +
            "{\"actors\":\"刘德华|舒淇|杨祐宁|张静初|让·雷诺|曾志伟|沙溢\",\"filmName\":\"侠盗联盟\",\"grade\":\"10.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3592.jpg\",\"releasedate\":\"2017-08-11\",\"shortinfo\":\"侠盗三剑客 越洋逃恐吓\",\"type\":\"动作|冒险\"}," +
            "{\"actors\":\"廖凡|李易峰|万茜|李纯|张国柱\",\"filmName\":\"心理罪\",\"grade\":\"10.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3795.jpg\",\"releasedate\":\"2017-08-11\",\"shortinfo\":\"侦探两搭档 真相背后藏\",\"type\":\"悬疑|犯罪\"}," +
            "{\"actors\":\"徐瑞阳|赵倩|姜启杨|徐万学|韩靓|韦安\",\"filmName\":\"隐隐惊马槽之绝战女僵尸\",\"grade\":\"5.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3825.jpg\",\"releasedate\":\"2017-08-11\",\"shortinfo\":\"阴兵来借道 尸占惊马槽\",\"type\":\"惊悚|动作|冒险|悬疑\"}," +
            "{\"actors\":\"宋睿|王良|张佳浩|叶常清\",\"filmName\":\"左眼阴阳\",\"grade\":\"10.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3804.jpg\",\"releasedate\":\"2017-08-11\",\"shortinfo\":\"左眼见到鬼 是诡还是魅\",\"type\":\"恐怖|惊悚|悬疑\"}," +
            "{\"actors\":null,\"filmName\":\"二十二\",\"grade\":\"10.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3811.jpg\",\"releasedate\":\"2017-08-14\",\"shortinfo\":\"二战女俘虏 讲述心中苦\",\"type\":\"纪录片\"}," +
            "{\"actors\":\"郭富城|王千源|刘涛|余皑磊|冯嘉怡\",\"filmName\":\"破·局\",\"grade\":\"5.0\",\"picaddr\":\"http://app.infunpw.com/commons/images/cinema/cinema_films/3812.jpg\",\"releasedate\":\"2017-08-18\",\"shortinfo\":\"影帝硬碰硬 迷局谁怕谁\",\"type\":\"动作|犯罪\"}" +
            "]";
}