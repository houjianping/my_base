package com.siyuan.enjoyreading.api;

import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.entity.BannerItem;
import com.siyuan.enjoyreading.entity.WebInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ApiConfig {

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
            "{\"actors\":\"刘佩琦|曹云金|罗昱焜\",\"filmName\":\"龙之战\",\"grade\":\"5.0\",\"picaddr\":\"http://d.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c6e453882a9f16fdfaaf516729.jpg\",\"releasedate\":\"2017-08-04\",\"shortinfo\":\"持倭刀屹立 抗外敌救国\",\"type\":\"动作|战争|历史\"}," +
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

    public static final String JSON_ZONE_LIST = "[{\"appointUserid\":0,\"createTime\":1471943240000,\"icon\":\"Image/20160819/1471570856669.jpeg\",\"takeTimes\":\"12\",\"goodjobCount\":0,\"replys\":[],\"replyCount\":0,\"pictures\":\"\",\"type\":\"0\",\"goodjobs\":[],\"isvalid\":\"0\",\"content\":\"不他苦苦他哭哭啼啼call call call把啦啦啦比较不同不把它徒步把基础八级bat tata table tata club吃茶比如步步B app啊app奔驰才承认失败哈哈啊哈哈哈哈怕拆改才此白菜练车教练同谋偷偷某透明Jack ta CT tab BL ta她不\\n他卡路里途径\\n\\n他夫妇开疆拓土拒绝\",\"id\":15,\"appointUserNickname\":\"\",\"nickName\":\"锋\",\"address\":\"\",\"userId\":10000,\"longitude\":\"0\",\"latitude\":\"0\"},{\"appointUserid\":0,\"createTime\":1471942968000,\"icon\":\"Image/20160819/1471570856669.jpeg\",\"takeTimes\":\"12\"," +
            "\"goodjobCount\":0,\"replys\":[],\"replyCount\":0,\"pictures\":\"Image/20160823/1471942933390.1471865953838.jpeg;Image/20160823/1471942933488.jpeg;Image/20160823/1471942934413.jianshu.haruki.jpeg;Image/20160823/1471942935325.jpeg\",\"type\":\"0\",\"goodjobs\":[],\"isvalid\":\"0\",\"content\":\"开疆拓土吧练车教练了他不踏踏步步同步步步步步啊不踏步踏踏踏我的中断向量表了肯定有不踏踏步踏步步步步步踏踏踏踏来不他催促Ella参考参考咔咔卡啊啦啦啦巴卡啦啦啦比较啦啦啦陈康灵长卡拉but actually不不不步步卡布他旅途么也门类不啊啦啦啦啦啦啊拉里邋遢了啊Java TVB他不拉开啊啦啦啦啦啦啦参考参考步步他踏步步\",\"id\":14,\"appointUserNickname\":\"\",\"nickName\":\"锋\",\"address\":\"\",\"userId\":10000,\"longitude\":\"0\",\"latitude\":\"0\"},{\"appointUserid\":0,\"createTime\":1471398857000,\"icon\":\"Image/20160817/1471398965572.jpeg\",\"takeTimes\":\"14\",\"goodjobCount\":1,\"replys\":[{\"id\":22,\"content\":\"肥死你\",\"createTime\":1471399614000,\"appointUserid\":0,\"publishId\":13,\"appointUserNickname\":\"\",\"userId\":10005,\"pictures\":\"\",\"userNickname\":\"燕子\"},{\"id\":23,\"content\":\"肥死你\",\"createTime\":1471406871000,\"appointUserid\":0,\"publishId\":13,\"appointUserNickname\":\"\",\"userId\":10000,\"pictures\":\"\",\"userNickname\":\"锋\"},{\"id\":24,\"content\":\"肥死你\",\"createTime\":1471489658000," +
            "\"appointUserid\":0,\"publishId\":13,\"appointUserNickname\":\"\",\"userId\":10002,\"pictures\":\"\",\"userNickname\":\"小鹏\"}],\"replyCount\":3,\"pictures\":\"Image/20160817/1471398852032.jpeg;Image/20160817/1471398852069.jpeg\",\"type\":\"0\",\"goodjobs\":[{\"id\":11,\"createTime\":1471406833000,\"publishId\":13,\"userId\":10000,\"userNickname\":\"锋\"}]," +
            "\"isvalid\":\"0\",\"content\":\"么么\",\"id\":13,\"appointUserNickname\":\"\",\"nickName\":\"雷菁\",\"address\":\"\",\"userId\":10013,\"longitude\":\"0\",\"latitude\":\"0\"},{\"appointUserid\":0,\"createTime\":1471398806000,\"icon\":\"Image/20160817/1471398965572.jpeg\",\"takeTimes\":\"14\",\"goodjobCount\":1,\"replys\":[],\"replyCount\":0,\"pictures\":\"Image/20160817/1471398798359.jpeg;Image/20160817/1471398798394.jpeg;Image/20160817/1471398798435.jpeg;Image/20160817/1471398799094.jpeg;Image/20160817/1471398800487.jpeg;Image/20160817/1471398800809.jpeg;Image/20160817/1471398801197.jpeg;Image/20160817/1471398801527.jpeg;Image/20160817/1471398801867.jpeg\",\"type\":\"0\",\"goodjobs\":[{\"id\":12,\"createTime\":1471406839000,\"publishId\":12,\"userId\":10000,\"userNickname\":\"锋\"}],\"isvalid\":\"0\",\"content\":\"吃吃吃\",\"id\":12,\"appointUserNickname\":\"\",\"nickName\":\"雷菁\",\"address\":\"\",\"userId\":10013,\"longitude\":\"0\",\"latitude\":\"0\"},{\"appointUserid\":0,\"createTime\":1471394956000,\"icon\":\"\",\"takeTimes\":\"0\",\"goodjobCount\":1,\"replys\":[],\"replyCount\":0," +
            "\"pictures\":\"Image/20160817/1471394954041.jpeg\",\"type\":\"0\",\"goodjobs\":[{\"id\":10,\"createTime\":1471401148000,\"publishId\":11,\"userId\":10000,\"userNickname\":\"锋\"}],\"isvalid\":\"0\",\"content\":\"你好牛逼\",\"id\":11,\"appointUserNickname\":\"\",\"nickName\":\"carter\",\"address\":\"\",\"userId\":10102,\"longitude\":\"0\",\"latitude\":\"0\"},{\"appointUserid\":0,\"createTime\":1471233432000,\"icon\":\"\",\"takeTimes\":\"0\",\"goodjobCount\":2,\"replys\":[{\"id\":11,\"content\":\"啾啾啾\",\"createTime\":1471233460000,\"appointUserid\":0,\"publishId\":9,\"appointUserNickname\":\"\",\"userId\":10102,\"pictures\":\"\",\"userNickname\":\"carter\"}],\"replyCount\":1,\"pictures\":\"Image/20160815/1471233430776.jpeg\",\"type\":\"0\"," +
            "\"goodjobs\":[{\"id\":7,\"createTime\":1471233446000,\"publishId\":9,\"userId\":10102,\"userNickname\":\"carter\"},{\"id\":15,\"createTime\":1472006199000,\"publishId\":9,\"userId\":10000,\"userNickname\":\"锋\"}],\"isvalid\":\"0\",\"content\":\"陈v刚回家\",\"id\":9,\"appointUserNickname\":\"\",\"nickName\":\"carter\",\"address\":\"\",\"userId\":10102,\"longitude\":\"0\",\"latitude\":\"0\"},{\"appointUserid\":0,\"createTime\":1471229159000,\"icon\":\"Image/20160819/1471570856669.jpeg\",\"takeTimes\":\"12\",\"goodjobCount\":1,\"replys\":[],\"replyCount\":0,\"pictures\":\"Image/20160815/1471229143095.jpeg;Image/20160815/1471229143130.jpeg\",\"type\":\"0\",\"goodjobs\":[{\"id\":17,\"createTime\":1472006209000,\"publishId\":7,\"userId\":10000,\"userNickname\":\"锋\"}]," +
            "\"isvalid\":\"0\",\"content\":\"莫练车教练\",\"id\":7,\"appointUserNickname\":\"\",\"nickName\":\"锋\",\"address\":\"\",\"userId\":10000,\"longitude\":\"0\",\"latitude\":\"0\"},{\"appointUserid\":0,\"createTime\":1471227441000,\"icon\":\"Image/20160819/1471570856669.jpeg\",\"takeTimes\":\"12\",\"goodjobCount\":1,\"replys\":[],\"replyCount\":0,\"pictures\":\"Image/20160815/1471227434250.jpeg;Image/20160815/1471227434373.jpeg\",\"type\":\"0\",\"goodjobs\":[{\"id\":6,\"createTime\":1471227450000,\"publishId\":6,\"userId\":10000,\"userNickname\":\"锋\"}],\"isvalid\":\"0\",\"content\":\"know与练车教练了了魔力好归宿\",\"id\":6,\"appointUserNickname\":\"\",\"nickName\":\"锋\",\"address\":\"\",\"userId\":10000,\"longitude\":\"0\",\"latitude\":\"0\"},{\"appointUserid\":0,\"createTime\":1471224271000,\"icon\":\"Image/20160819/1471570856669.jpeg\",\"takeTimes\":\"12\",\"goodjobCount\":1,\"replys\":[],\"replyCount\":0,\"pictures\":\"Image/20160815/1471224256630.jpg;Image/20160815/1471224256945.png\",\"type\":\"0\",\"goodjobs\":[{\"id\":14,\"createTime\":1471406854000,\"publishId\":4,\"userId\":10000,\"userNickname\":\"锋\"}],\"isvalid\":\"0\",\"content\":\"墨玉兔就那么重要\",\"id\":4,\"appointUserNickname\":\"\",\"nickName\":\"锋\",\"address\":\"宝轩酒店\",\"userId\":10000,\"longitude\":\"113.2686712109507\",\"latitude\":\"23.123064640399328\"}]";
    /**
     * 图片
     */
    private static String[] Urls = {"http://d.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c6e453882a9f16fdfaaf516729.jpg", "http://h.hiphotos.baidu.com/image/pic/item/30adcbef76094b36db47d2e4a1cc7cd98c109de6.jpg", "http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c27dc0dcdd52a6059242da6cc.jpg"
            , "http://c.hiphotos.baidu.com/image/h%3D200/sign=d21f63f99d3df8dcb93d8891fd1072bf/78310a55b319ebc415951b978026cffc1f1716ca.jpg", "http://d.hiphotos.baidu.com/image/pic/item/54fbb2fb43166d22dc28839a442309f79052d265.jpg"
            , "http://c.hiphotos.baidu.com/image/pic/item/03087bf40ad162d9d0e7560313dfa9ec8a13cda7.jpg", "http://g.hiphotos.baidu.com/image/h%3D200/sign=16f4ef3e35adcbef1e3479069cae2e0e/6d81800a19d8bc3e7763d030868ba61ea9d345e5.jpg"
            , "http://g.hiphotos.baidu.com/image/pic/item/8d5494eef01f3a29a3b0e6c49b25bc315c607cbb.jpg", "http://c.hiphotos.baidu.com/image/h%3D200/sign=548da2d73f6d55fbdac671265d224f40/a044ad345982b2b7a2b8f7cd33adcbef76099b90.jpg"
            , "http://g.hiphotos.baidu.com/image/pic/item/7acb0a46f21fbe09359315d16f600c338644ad22.jpg", "http://h.hiphotos.baidu.com/image/h%3D200/sign=9d4948d52c738bd4db21b531918a876c/6a600c338744ebf85db15337dbf9d72a6159a7f1.jpg"
            , "http://e.hiphotos.baidu.com/image/h%3D200/sign=7683f02abc096b639e1959503c328733/203fb80e7bec54e74a142d1bbb389b504fc26a3e.jpg"};

    public static String RECHARGE_LIST = "[{" +
            "\"id\": 0," +
            "\"title\": \"60\"," +
            "\"content\": \"6\"" +
            "},{" +
            "\"id\": 1," +
            "\"title\": \"180\"," +
            "\"content\": \"18\"" +
            "},{" +
            "\"id\": 2," +
            "\"title\": \"300\"," +
            "\"content\": \"30\"" +
            "},{" +
            "\"id\": 3," +
            "\"title\": \"680\"," +
            "\"content\": \"68\"" +
            "},{" +
            "\"id\": 4," +
            "\"title\": \"1680\"," +
            "\"content\": \"168\"" +
            "},{" +
            "\"id\": 5," +
            "\"title\": \"5800\"," +
            "\"content\": \"580\"" +
            "}]";
    public static String JSON_VIDEO_LIST = "[{" +
            "\"sizeHD\": 0," +
            "\"videoTopic\": {" +
            "\"ename\": \"T1460515714780\"," +
            "\"tname\": \"资讯第一站\"," +
            "\"alias\": \"寻找最好的视频分享给大家。\"," +
            "\"topic_icons\": \"http://img2.cache.netease.com/m/newsapp/topic_icons/T1460515714780.png\"," +
            "\"tid\": \"T1460515714780\"" +
            "}," +
            "\"mp4Hd_url\": null," +
            "\"description\": \"67名电信诈骗犯罪嫌疑人自非洲被包机押回国\"," +
            "\"title\": \"67名电信诈骗嫌疑人被押回国\"," +
            "\"mp4_url\": \"http://flv3.bn.netease.com/videolib3/1604/14/LSwHa2712/SD/LSwHa2712-mobile.mp4\"," +
            "\"vid\": \"VBJLS9PPK\"," +
            "\"cover\": \"http://vimg3.ws.126.net/image/snapshot/2016/4/F/M/VBJLSB3FM.jpg\"," +
            "\"sizeSHD\": 0," +
            "\"playersize\": 0," +
            "\"ptime\": \"2016-04-14 08:09:01\"," +
            "\"m3u8_url\": \"http://flv.bn.netease.com/videolib3/1604/14/LSwHa2712/SD/movie_index.m3u8\"," +
            "\"topicImg\": \"http://vimg2.ws.126.net/image/snapshot/2016/3/I/F/VBI035SIF.jpg\"," +
            "\"votecount\": 191676," +
            "\"length\": 236," +
            "\"videosource\": \"新媒体\"," +
            "\"m3u8Hd_url\": null," +
            "\"sizeSD\": 10266," +
            "\"topicSid\": \"VBI035SID\"," +
            "\"playCount\": 603," +
            "\"replyCount\": 192660," +
            "\"replyBoard\": \"video_bbs\"," +
            "\"replyid\": \"BJLS9PPK008535RB\"," +
            "\"topicName\": \"资讯第一站\"," +
            "\"sectiontitle\": \"\"," +
            "\"topicDesc\": \"寻找最好的视频分享给大家。\"" +
            "}, {" +
            "\"sizeHD\": 0," +
            "\"videoTopic\": {" +
            "\"ename\": \"T1460515714780\"," +
            "\"tname\": \"资讯第一站\"," +
            "\"alias\": \"寻找最好的视频分享给大家。\"," +
            "\"topic_icons\": \"http://img2.cache.netease.com/m/newsapp/topic_icons/T1460515714780.png\"," +
            "\"tid\": \"T1460515714780\"" +
            "}," +
            "\"mp4Hd_url\": null," +
            "\"description\": \"女孩被父亲殴打了2个小时，脸部被打得面目全非。\"," +
            "\"title\": \"11岁女孩被生父殴打2小时致死\"," +
            "\"mp4_url\": \"http://flv3.bn.netease.com/videolib3/1604/14/vrWgN3162/SD/vrWgN3162-mobile.mp4\"," +
            "\"vid\": \"VBJLSNUJO\"," +
            "\"cover\": \"http://vimg2.ws.126.net/image/snapshot/2016/4/E/V/VBJLSQ7EV.jpg\"," +
            "\"sizeSHD\": 0," +
            "\"playersize\": 0," +
            "\"ptime\": \"2016-04-14 08:16:44\"," +
            "\"m3u8_url\": \"http://flv.bn.netease.com/videolib3/1604/14/vrWgN3162/SD/movie_index.m3u8\"," +
            "\"topicImg\": \"http://vimg1.ws.126.net/image/snapshot/2016/3/I/F/VBI035SIF.jpg\"," +
            "\"votecount\": 1297," +
            "\"length\": 112," +
            "\"videosource\": \"新媒体\"," +
            "\"m3u8Hd_url\": null," +
            "\"sizeSD\": 4872," +
            "\"topicSid\": \"VBI035SID\"," +
            "\"playCount\": 165612," +
            "\"replyCount\": 1488," +
            "\"replyBoard\": \"video_bbs\"," +
            "\"replyid\": \"BJLSNUJO008535RB\"," +
            "\"topicName\": \"资讯第一站\"," +
            "\"sectiontitle\": \"\"," +
            "\"topicDesc\": \"寻找最好的视频分享给大家。\"" +
            "}, {" +
            "\"sizeHD\": 0," +
            "\"videoTopic\": {" +
            "\"ename\": \"T1460515715597\"," +
            "\"tname\": \"番茄在现场\"," +
            "\"alias\": \"第一时间让大家了解真相。\"," +
            "\"topic_icons\": \"http://img2.cache.netease.com/m/newsapp/topic_icons/T1460515715597.png\"," +
            "\"tid\": \"T1460515715597\"" +
            "}," +
            "\"mp4Hd_url\": null," +
            "\"description\": \"平时受的委屈太多了，一提到罚款就能哭出来。\"," +
            "\"title\": \"实拍大车司机哭诉被交警罚钱\"," +
            "\"mp4_url\": \"http://flv3.bn.netease.com/videolib3/1604/13/ewfvW8416/SD/ewfvW8416-mobile.mp4\"," +
            "\"vid\": \"VBJJLG6NF\"," +
            "\"cover\": \"http://vimg1.ws.126.net/image/snapshot/2016/4/F/A/VBJJLSJFA.jpg\"," +
            "\"sizeSHD\": 0," +
            "\"playersize\": 0," +
            "\"ptime\": \"2016-04-13 11:31:42\"," +
            "\"m3u8_url\": \"http://flv.bn.netease.com/videolib3/1604/13/ewfvW8416/SD/movie_index.m3u8\"," +
            "\"topicImg\": \"http://vimg3.ws.126.net/image/snapshot/2016/3/C/N/VBI038VCN.jpg\"," +
            "\"votecount\": 14381," +
            "\"length\": 57," +
            "\"videosource\": \"新媒体\"," +
            "\"m3u8Hd_url\": null," +
            "\"sizeSD\": 2479," +
            "\"topicSid\": \"VBI038VCL\"," +
            "\"playCount\": 865979," +
            "\"replyCount\": 15719," +
            "\"replyBoard\": \"video_bbs\"," +
            "\"replyid\": \"BJJLG6NF008535RB\"," +
            "\"topicName\": \"番茄在现场\"," +
            "\"sectiontitle\": \"\"," +
            "\"topicDesc\": \"第一时间让大家了解真相。\"" +
            "}, {" +
            "\"sizeHD\": 4441," +
            "\"videoTopic\": {" +
            "\"ename\": \"T1460515715597\"," +
            "\"tname\": \"番茄在现场\"," +
            "\"alias\": \"第一时间让大家了解真相。\"," +
            "\"topic_icons\": \"http://img2.cache.netease.com/m/newsapp/topic_icons/T1460515715597.png\"," +
            "\"tid\": \"T1460515715597\"" +
            "}," +
            "\"mp4Hd_url\": \"http://flv3.bn.netease.com/videolib3/1604/13/lRYHb2242/HD/lRYHb2242-mobile.mp4\"," +
            "\"description\": \"女司机们开车真的要小心呀\"," +
            "\"title\": \"女司机开车上树下河样样通\"," +
            "\"mp4_url\": \"http://flv3.bn.netease.com/videolib3/1604/13/lRYHb2242/SD/lRYHb2242-mobile.mp4\"," +
            "\"vid\": \"VBJK2QB66\"," +
            "\"cover\": \"http://vimg1.ws.126.net/image/snapshot/2016/4/6/7/VBJK2QB67.jpg\"," +
            "\"sizeSHD\": 7654," +
            "\"playersize\": 0," +
            "\"ptime\": \"2016-04-13 15:24:25\"," +
            "\"m3u8_url\": \"http://flv.bn.netease.com/videolib3/1604/13/lRYHb2242/SD/movie_index.m3u8\"," +
            "\"topicImg\": \"http://vimg2.ws.126.net/image/snapshot/2016/3/C/N/VBI038VCN.jpg\"," +
            "\"votecount\": 44," +
            "\"length\": 63," +
            "\"videosource\": \"新媒体\"," +
            "\"m3u8Hd_url\": \"http://flv.bn.netease.com/videolib3/1604/13/lRYHb2242/HD/movie_index.m3u8\"," +
            "\"sizeSD\": 2740," +
            "\"topicSid\": \"VBI038VCL\"," +
            "\"playCount\": 110017," +
            "\"replyCount\": 59," +
            "\"replyBoard\": \"video_bbs\"," +
            "\"replyid\": \"BJK2QB66008535RB\"," +
            "\"topicName\": \"番茄在现场\"," +
            "\"sectiontitle\": \"\"," +
            "\"topicDesc\": \"第一时间让大家了解真相。\"" +
            "}, {" +
            "\"sizeHD\": 0," +
            "\"videoTopic\": {" +
            "\"ename\": \"T1460515715597\"," +
            "\"tname\": \"番茄在现场\"," +
            "\"alias\": \"第一时间让大家了解真相。\"," +
            "\"topic_icons\": \"http://img2.cache.netease.com/m/newsapp/topic_icons/T1460515715597.png\"," +
            "\"tid\": \"T1460515715597\"" +
            "}," +
            "\"mp4Hd_url\": null," +
            "\"description\": \"实拍男子成都街头暴打女子 无视周围群众劝阻\"," +
            "\"title\": \"实拍成都一男子当街暴打女子\"," +
            "\"mp4_url\": \"http://flv3.bn.netease.com/videolib3/1604/13/RqoAx0056/SD/RqoAx0056-mobile.mp4\"," +
            "\"vid\": \"VBJKA3MU6\"," +
            "\"cover\": \"http://vimg3.ws.126.net/image/snapshot/2016/4/1/O/VBJKA801O.jpg\"," +
            "\"sizeSHD\": 0," +
            "\"playersize\": 1," +
            "\"ptime\": \"2016-04-13 17:31:52\"," +
            "\"m3u8_url\": \"http://flv.bn.netease.com/videolib3/1604/13/RqoAx0056/SD/movie_index.m3u8\"," +
            "\"topicImg\": \"http://vimg1.ws.126.net/image/snapshot/2016/3/C/N/VBI038VCN.jpg\"," +
            "\"votecount\": 61," +
            "\"length\": 100," +
            "\"videosource\": \"新媒体\"," +
            "\"m3u8Hd_url\": null," +
            "\"sizeSD\": 4350," +
            "\"topicSid\": \"VBI038VCL\"," +
            "\"playCount\": 3327," +
            "\"replyCount\": 68," +
            "\"replyBoard\": \"video_bbs\"," +
            "\"replyid\": \"BJKA3MU6008535RB\"," +
            "\"topicName\": \"番茄在现场\"," +
            "\"sectiontitle\": \"\"," +
            "\"topicDesc\": \"第一时间让大家了解真相。\"" +
            "}, {" +
            "\"sizeHD\": 6415," +
            "\"videoTopic\": {" +
            "\"ename\": \"T1460515715597\"," +
            "\"tname\": \"番茄在现场\"," +
            "\"alias\": \"第一时间让大家了解真相。\"," +
            "\"topic_icons\": \"http://img2.cache.netease.com/m/newsapp/topic_icons/T1460515715597.png\"," +
            "\"tid\": \"T1460515715597\"" +
            "}," +
            "\"mp4Hd_url\": \"http://flv3.bn.netease.com/videolib3/1604/14/wIjLU2290/HD/wIjLU2290-mobile.mp4\"," +
            "\"description\": \"实拍女生教室遭多名同学殴打 被疯狂掌掴\"," +
            "\"title\": \"实拍女生教室遭多名同学殴打\"," +
            "\"mp4_url\": \"http://flv3.bn.netease.com/videolib3/1604/14/wIjLU2290/SD/wIjLU2290-mobile.mp4\"," +
            "\"vid\": \"VBJLS49R6\"," +
            "\"cover\": \"http://vimg3.ws.126.net/image/snapshot/2016/4/R/7/VBJLS49R7.jpg\"," +
            "\"sizeSHD\": 0," +
            "\"playersize\": 0," +
            "\"ptime\": \"2016-04-14 08:06:01\"," +
            "\"m3u8_url\": \"http://flv.bn.netease.com/videolib3/1604/14/wIjLU2290/SD/movie_index.m3u8\"," +
            "\"topicImg\": \"http://vimg1.ws.126.net/image/snapshot/2016/3/C/N/VBI038VCN.jpg\"," +
            "\"votecount\": 182," +
            "\"length\": 91," +
            "\"videosource\": \"新媒体\"," +
            "\"m3u8Hd_url\": \"http://flv.bn.netease.com/videolib3/1604/14/wIjLU2290/HD/movie_index.m3u8\"," +
            "\"sizeSD\": 3958," +
            "\"topicSid\": \"VBI038VCL\"," +
            "\"playCount\": 134652," +
            "\"replyCount\": 241," +
            "\"replyBoard\": \"video_bbs\"," +
            "\"replyid\": \"BJLS49R6008535RB\"," +
            "\"topicName\": \"番茄在现场\"," +
            "\"sectiontitle\": \"\"," +
            "\"topicDesc\": \"第一时间让大家了解真相。\"" +
            "}, {" +
            "\"sizeHD\": 0," +
            "\"videoTopic\": {" +
            "\"ename\": \"T1460515709416\"," +
            "\"tname\": \"感悟生活圈\"," +
            "\"alias\": \"生命的价值不依赖于我们作为。\"," +
            "\"topic_icons\": \"http://img2.cache.netease.com/m/newsapp/topic_icons/T1460515709416.png\"," +
            "\"tid\": \"T1460515709416\"" +
            "}," +
            "\"mp4Hd_url\": null," +
            "\"description\": \"看完就知道自己是怎么被骗的了吧\"," +
            "\"title\": \"女小贩1分钟内偷换两次秤\"," +
            "\"mp4_url\": \"http://flv3.bn.netease.com/videolib3/1604/12/RdTVA8808/SD/RdTVA8808-mobile.mp4\"," +
            "\"vid\": \"VBJHJ360D\"," +
            "\"cover\": \"http://vimg3.ws.126.net/image/snapshot/2016/4/0/E/VBJHJ360E.jpg\"," +
            "\"sizeSHD\": 0," +
            "\"playersize\": 0," +
            "\"ptime\": \"2016-04-12 16:11:09\"," +
            "\"m3u8_url\": \"http://flv.bn.netease.com/videolib3/1604/12/RdTVA8808/SD/movie_index.m3u8\"," +
            "\"topicImg\": \"http://vimg3.ws.126.net/image/snapshot/2016/3/U/L/VBI02L7UL.jpg\"," +
            "\"votecount\": 7," +
            "\"length\": 44," +
            "\"videosource\": \"新媒体\"," +
            "\"m3u8Hd_url\": null," +
            "\"sizeSD\": 1914," +
            "\"topicSid\": \"VBI02L7UJ\"," +
            "\"playCount\": 133," +
            "\"replyCount\": 8," +
            "\"replyBoard\": \"video_bbs\"," +
            "\"replyid\": \"BJHJ360D008535RB\"," +
            "\"topicName\": \"感悟生活圈\"," +
            "\"sectiontitle\": \"\"," +
            "\"topicDesc\": \"生命的价值不依赖于我们的所作所为。\"" +
            "}, {" +
            "\"sizeHD\": 0," +
            "\"videoTopic\": {" +
            "\"ename\": \"T1460515709416\"," +
            "\"tname\": \"感悟生活圈\"," +
            "\"alias\": \"生命的价值不依赖于我们作为。\"," +
            "\"topic_icons\": \"http://img2.cache.netease.com/m/newsapp/topic_icons/T1460515709416.png\"," +
            "\"tid\": \"T1460515709416\"" +
            "}," +
            "\"mp4Hd_url\": null," +
            "\"description\": \"老人公交车上呕吐 12岁女孩拿书皮接住呕吐物\"," +
            "\"title\": \"女孩拿书皮接住老人的呕吐物\"," +
            "\"mp4_url\": \"http://flv3.bn.netease.com/videolib3/1604/13/OXVSD3428/SD/OXVSD3428-mobile.mp4\"," +
            "\"vid\": \"VBJJ75TQP\"," +
            "\"cover\": \"http://vimg1.ws.126.net/image/snapshot/2016/4/V/G/VBJJ77LVG.jpg\"," +
            "\"sizeSHD\": 0," +
            "\"playersize\": 1," +
            "\"ptime\": \"2016-04-13 07:21:25\"," +
            "\"m3u8_url\": \"http://flv.bn.netease.com/videolib3/1604/13/OXVSD3428/SD/movie_index.m3u8\"," +
            "\"topicImg\": \"http://vimg3.ws.126.net/image/snapshot/2016/3/U/L/VBI02L7UL.jpg\"," +
            "\"votecount\": 29," +
            "\"length\": 83," +
            "\"videosource\": \"新媒体\"," +
            "\"m3u8Hd_url\": null," +
            "\"sizeSD\": 3610," +
            "\"topicSid\": \"VBI02L7UJ\"," +
            "\"playCount\": 24964," +
            "\"replyCount\": 41," +
            "\"replyBoard\": \"video_bbs\"," +
            "\"replyid\": \"BJJ75TQP008535RB\"," +
            "\"topicName\": \"感悟生活圈\"," +
            "\"sectiontitle\": \"\"," +
            "\"topicDesc\": \"生命的价值不依赖于我们的所作所为。\"" +
            "}, {" +
            "\"sizeHD\": 0," +
            "\"videoTopic\": {" +
            "\"ename\": \"T1460515709416\"," +
            "\"tname\": \"感悟生活圈\"," +
            "\"alias\": \"生命的价值不依赖于我们作为。\"," +
            "\"topic_icons\": \"http://img2.cache.netease.com/m/newsapp/topic_icons/T1460515709416.png\"," +
            "\"tid\": \"T1460515709416\"" +
            "}," +
            "\"mp4Hd_url\": null," +
            "\"description\": \"女子撒泼辱骂交警 掏一叠钱砸地上：找几副棺材等你\"," +
            "\"title\": \"女子辱骂交警掏钱砸地上\"," +
            "\"mp4_url\": \"http://flv3.bn.netease.com/videolib3/1604/13/MSoOk3700/SD/MSoOk3700-mobile.mp4\"," +
            "\"vid\": \"VBJJ7GDR0\"," +
            "\"cover\": \"http://vimg2.ws.126.net/image/snapshot/2016/4/5/N/VBJJ7I25N.jpg\"," +
            "\"sizeSHD\": 0," +
            "\"playersize\": 0," +
            "\"ptime\": \"2016-04-13 07:27:09\"," +
            "\"m3u8_url\": \"http://flv.bn.netease.com/videolib3/1604/13/MSoOk3700/SD/movie_index.m3u8\"," +
            "\"topicImg\": \"http://vimg1.ws.126.net/image/snapshot/2016/3/U/L/VBI02L7UL.jpg\"," +
            "\"votecount\": 471," +
            "\"length\": 97," +
            "\"videosource\": \"新媒体\"," +
            "\"m3u8Hd_url\": null," +
            "\"sizeSD\": 4219," +
            "\"topicSid\": \"VBI02L7UJ\"," +
            "\"playCount\": 188908," +
            "\"replyCount\": 584," +
            "\"replyBoard\": \"video_bbs\"," +
            "\"replyid\": \"BJJ7GDR0008535RB\"," +
            "\"topicName\": \"感悟生活圈\"," +
            "\"sectiontitle\": \"\"," +
            "\"topicDesc\": \"生命的价值不依赖于我们的所作所为。\"" +
            "}, {" +
            "\"sizeHD\": 32148," +
            "\"videoTopic\": {" +
            "\"ename\": \"T1460515709416\"," +
            "\"tname\": \"感悟生活圈\"," +
            "\"alias\": \"生命的价值不依赖于我们作为。\"," +
            "\"topic_icons\": \"http://img2.cache.netease.com/m/newsapp/topic_icons/T1460515709416.png\"," +
            "\"tid\": \"T1460515709416\"" +
            "}," +
            "\"mp4Hd_url\": \"http://flv3.bn.netease.com/videolib3/1604/13/NKCDf6444/HD/NKCDf6444-mobile.mp4\"," +
            "\"description\": \"监拍4岁男童幼儿园猝死 老师玩手机全然无觉\"," +
            "\"title\": \"监拍4岁男童幼儿园猝死\"," +
            "\"mp4_url\": \"http://flv3.bn.netease.com/videolib3/1604/13/NKCDf6444/SD/NKCDf6444-mobile.mp4\"," +
            "\"vid\": \"VBJJA0N0B\"," +
            "\"cover\": \"http://vimg2.ws.126.net/image/snapshot/2016/4/0/C/VBJJA0N0C.jpg\"," +
            "\"sizeSHD\": 0," +
            "\"playersize\": 0," +
            "\"ptime\": \"2016-04-13 08:11:00\"," +
            "\"m3u8_url\": \"http://flv.bn.netease.com/videolib3/1604/13/NKCDf6444/SD/movie_index.m3u8\"," +
            "\"topicImg\": \"http://vimg1.ws.126.net/image/snapshot/2016/3/U/L/VBI02L7UL.jpg\"," +
            "\"votecount\": 14," +
            "\"length\": 456," +
            "\"videosource\": \"新媒体\"," +
            "\"m3u8Hd_url\": \"http://flv.bn.netease.com/videolib3/1604/13/NKCDf6444/HD/movie_index.m3u8\"," +
            "\"sizeSD\": 19836," +
            "\"topicSid\": \"VBI02L7UJ\"," +
            "\"playCount\": 843," +
            "\"replyCount\": 17," +
            "\"replyBoard\": \"video_bbs\"," +
            "\"replyid\": \"BJJA0N0B008535RB\"," +
            "\"topicName\": \"感悟生活圈\"," +
            "\"sectiontitle\": \"\"," +
            "\"topicDesc\": \"生命的价值不依赖于我们的所作所为。\"" +
            "}]";

    /**
     * 获取随机图片串
     *
     * @param num
     * @return
     */
    public static String getRandomPhotoUrlString(int num) {
        StringBuilder sdbResult = new StringBuilder();
        if (num > 0) {
            String[] photoUrls = new String[num > 9 ? 9 : num];
            for (int i = 0; i < num; i++) {
                if (sdbResult.length() > 0) {
                    sdbResult.append(";").append(Urls[new Random().nextInt(Urls.length)]);
                } else {
                    sdbResult.append(Urls[new Random().nextInt(Urls.length)]);
                }

            }
        }
        return sdbResult.toString();
    }

    /**
     * 获取随机图片串
     *
     * @return
     */
    public static String getRandomPhotoUrl() {
        return Urls[new Random().nextInt(Urls.length)];
    }

    public static String JSON_WALLET_LIST = "[{" +
            "\"icon\": \"ada\"," +
            "\"title\": \"adad\"," +
            "\"summary\": \"ad\"," +
            "\"moneyDesc\": \"ada\"," +
            "\"time\": 1552719726000," +
            "\"statusDesc\": \"ada\"" +
            "},{" +
            "\"icon\": \"ada\"," +
            "\"title\": \"adad\"," +
            "\"summary\": \"ad\"," +
            "\"moneyDesc\": \"ada\"," +
            "\"time\": 1552719726000," +
            "\"statusDesc\": \"ada\"" +
            "},{" +
            "\"icon\": \"ada\"," +
            "\"title\": \"adad\"," +
            "\"summary\": \"ad\"," +
            "\"moneyDesc\": \"ada\"," +
            "\"time\": 1552719726000," +
            "\"statusDesc\": \"ada\"" +
            "},{" +
            "\"icon\": \"ada\"," +
            "\"title\": \"adad\"," +
            "\"summary\": \"ad\"," +
            "\"moneyDesc\": \"ada\"," +
            "\"time\": 1552719726000," +
            "\"statusDesc\": \"ada\"" +
            "},{" +
            "\"icon\": \"ada\"," +
            "\"title\": \"adad\"," +
            "\"summary\": \"ad\"," +
            "\"moneyDesc\": \"ada\"," +
            "\"time\": 1552719726000," +
            "\"statusDesc\": \"ada\"" +
            "},{" +
            "\"icon\": \"ada\"," +
            "\"title\": \"adad\"," +
            "\"summary\": \"ad\"," +
            "\"moneyDesc\": \"ada\"," +
            "\"time\": 1552719726000," +
            "\"statusDesc\": \"ada\"" +
            "},{" +
            "\"icon\": \"ada\"," +
            "\"title\": \"adad\"," +
            "\"summary\": \"ad\"," +
            "\"moneyDesc\": \"ada\"," +
            "\"time\": 1552719726000," +
            "\"statusDesc\": \"ada\"" +
            "},{" +
            "\"icon\": \"ada\"," +
            "\"title\": \"adad\"," +
            "\"summary\": \"ad\"," +
            "\"moneyDesc\": \"ada\"," +
            "\"time\": 1552719726000," +
            "\"statusDesc\": \"ada\"" +
            "}]";

    public static WebInfo getWebInfo() {
        String content = "<div class=\"widt_ad\" style=\"padding-bottom: 0px; padding-top: 0px; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;, arial, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;\"><img class=\"scrollLoading\" src=\"http://08.imgmini.eastday.com/mobile/20171213/20171213090938_5d204cbfe29043528eac687619b28f40_1.jpeg\" style=\"background-color:rgb(241, 240, 240); border:none; display:block; height:500.63888888889; margin-left:auto; margin-right:auto; max-width:100%; width:670px\" /></div>\n\n<p style=\"text-align:start\">&nbsp;</p>\n\n<p style=\"text-align:start\">据《每日邮报》12月12日报道，圣诞节快到了，美国一家小餐馆提前迎来了一位&ldquo;圣诞老人&rdquo;&mdash;&mdash;一位神秘的顾客吃了一顿17美元（约113元）的早餐后，给服务员留下了2000美元（约1.3万元）的小费。</p>\n\n<p style=\"text-align:start\">&nbsp;</p>\n\n<div class=\"widt_ad\" style=\"padding-bottom: 0px; padding-top: 0px; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;, arial, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;\"><img class=\"scrollLoading\" src=\"http://08.imgmini.eastday.com/mobile/20171213/20171213090938_5d204cbfe29043528eac687619b28f40_2.jpeg\" style=\"background-color:rgb(241, 240, 240); border:none; display:block; height:819px; margin-left:auto; margin-right:auto; max-width:100%; width:634px\" /></div>\n\n<p style=\"text-align:start\">&nbsp;</p>\n\n<p style=\"text-align:start\">在美国亚利桑那州斯科茨代尔市一家名为&ldquo;5 &amp; Diner&rdquo;的餐厅里，一位匿名人士在吃过一顿17.23美元的早餐后，给服务员们留下了2000美元的小费，并在账单上留下了一句话：请把小费分给所有餐馆工作人员，圣诞快乐。</p>\n\n<p style=\"text-align:start\">&nbsp;</p>\n\n<div class=\"widt_ad\" style=\"padding-bottom: 0px; padding-top: 0px; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;, arial, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;\"><img class=\"scrollLoading\" src=\"http://08.imgmini.eastday.com/mobile/20171213/20171213090938_5d204cbfe29043528eac687619b28f40_3.jpeg\" style=\"background-color:rgb(241, 240, 240); border:none; display:block; height:436px; margin-left:auto; margin-right:auto; max-width:100%; width:634px\" /></div>\n\n<p style=\"text-align:start\">&nbsp;</p>\n\n<p style=\"text-align:start\">餐馆服务员Delia Meeks说：&ldquo;我们立即把它交给了值班经理，她简直不敢相信，我们都很兴奋。这让餐馆每个人都有了很棒的一天。&rdquo;这顿饭的价格为17.23美元，顾客点了培根和鸡蛋作为早餐，小费是餐费的一百多倍。这家餐厅的9名员工每人分到了200多美元的小费。Meeks说，工作人员希望这位仁慈的陌生人能回来，这样他们就可以感谢他了。</p>\n";

        WebInfo info = new WebInfo();
        info.setUseWebUrl(true);
        info.setUrl("https://mil.sina.cn/2018-11-06/detail-ihmutuea7434493.d.html?from=wap");
        info.setTitle("神秘顾客花一百块吃早餐，扔给服务员一万三小费");
        info.setSource("东方头条");
        info.setDate(1541660938);
        info.setBody(content);
        return info;
    }
}
