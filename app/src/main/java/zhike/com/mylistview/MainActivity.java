package zhike.com.mylistview;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhike.com.mylistview.View.DocumentListView;
import zhike.com.mylistview.adapter.CollectionAdapter;
import zhike.com.mylistview.bean.ProductBean;
import zhike.com.mylistview.utils.ActivityHelper;
import zhike.com.mylistview.utils.Xutils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private TextView tv_delete, tv_around;
    private DocumentListView mListView;
    private CheckBox cb_sellectAll, cb_biangi;
    private RelativeLayout ll_edit;
    private LinearLayout ll_no_product;
    private boolean shareSate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        initListener();
        initcollectionData();
    }

    @Override
    public void onResume() {
        super.onResume();


    }


    @Override
    public void onPause() {
        super.onPause();


    }

    private CollectionAdapter collectionAdapter;
    private ArrayList<ProductBean> list = new ArrayList<>();
    private int page = 1;
    private boolean isInit = true;
    private void initcollectionData() {
        isInit = false;
        page = 1;
        int total = 0;
        String data="{\"total\":25,\"pages\":1,\"page\":1,\"count\":25,\"list\":[{\"id\":120781,\"origin\":0,\"sales_amount\":370,\"price\":\"18.38\",\"market_price\":\"21.38\",\"name\":\"大学生宿舍上铺床帘下铺床幔女寝室含支架遮光布帘子蚊帐宿舍神器\",\"cover\":\"http:\\/\\/img2.tbcdn.cn\\/tfscom\\/i2\\/2794969253\\/TB2Wy9IdH1K.eBjSsphXXcJOXXa_!!2794969253.jpg\",\"tag\":\"\",\"item_id\":\"527181051038\",\"url\":\"https:\\/\\/item.taobao.com\\/item.htm?spm=a1z10.3-c.w4002-14281974509.25.3ff19e11dnjV2z&id=527181051038\",\"shop_coupon\":\"https:\\/\\/market.m.taobao.com\\/apps\\/aliyx\\/coupon\\/detail.html?wh_weex=true&activityId=8a20aa6df5ea48219b4b18abf91a4645&sellerId=2794969253\",\"shop_coupon_price\":3,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504173286,\"create_time\":1504173312,\"refresh_time\":1504173312,\"caption\":\"\"},{\"id\":47237,\"origin\":1,\"sales_amount\":6438,\"price\":\"58.00\",\"market_price\":\"68.00\",\"name\":\"POLVCDG\\/铂典 蓝牙耳机挂耳式跑步头戴双入耳4.1无线运动苹果耳塞\",\"cover\":\"http:\\/\\/img4.tbcdn.cn\\/tfscom\\/i2\\/TB1Uk4KRVXXXXaZapXXXXXXXXXX_!!0-item_pic.jpg\",\"tag\":\"\",\"item_id\":\"536073141232\",\"url\":\"https:\\/\\/detail.tmall.com\\/item.htm?id=536073141232\",\"shop_coupon\":\"https:\\/\\/market.m.taobao.com\\/apps\\/aliyx\\/coupon\\/detail.html?wh_weex=true&activityId=a84d0bd5ddf247b48c14e3721b96c5e2&sellerId=1856864527\",\"shop_coupon_price\":10,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504173415,\"create_time\":1504316739,\"refresh_time\":1504316739,\"caption\":\"\"},{\"id\":121861,\"origin\":0,\"sales_amount\":0,\"price\":\"49.00\",\"market_price\":\"79.00\",\"name\":\"凉鞋女夏2017新款韩版一字扣带粗跟单鞋中跟露趾时尚百搭罗马女鞋\",\"cover\":\"http:\\/\\/img.alicdn.com\\/bao\\/uploaded\\/i2\\/TB1zGppRXXXXXXSXXXXYXGcGpXX_M2.SS2\",\"tag\":\"\",\"item_id\":\"550219765608\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=550219765608\",\"shop_coupon\":\"https:\\/\\/taoquan.taobao.com\\/coupon\\/unify_apply.htm?sellerId=914441265&activityId=6df2f19e79334e1481dbb14d2dcbde22\",\"shop_coupon_price\":30,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504110600,\"create_time\":1504255184,\"refresh_time\":1504255184,\"caption\":\"满68元减30元\"},{\"id\":121920,\"origin\":1,\"sales_amount\":13,\"price\":\"98.00\",\"market_price\":\"158.00\",\"name\":\"百莉缇2017春季韩版运动鞋女夏百搭平底跑步鞋透气粉色休闲鞋学生\",\"cover\":\"http:\\/\\/img4.tbcdn.cn\\/tfscom\\/i3\\/2112158538\\/TB2JM36kmBjpuFjy1XdXXaooVXa_!!2112158538.jpg\",\"tag\":\"\",\"item_id\":\"547553696916\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=547553696916\",\"shop_coupon\":\"http:\\/\\/shop.m.taobao.com\\/shop\\/coupon.htm?seller_id=2112158538&activity_id=833a849152c84235a691d98be2da3c0c\",\"shop_coupon_price\":60,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504183860,\"create_time\":1504318490,\"refresh_time\":1504318490,\"caption\":\" 158\"},{\"id\":121925,\"origin\":1,\"sales_amount\":98,\"price\":\"89.00\",\"market_price\":\"119.00\",\"name\":\"Swanson斯旺森 美国进口PABA胶囊120粒 头发综合营养男女呵护发质\",\"cover\":\"http:\\/\\/img2.tbcdn.cn\\/tfscom\\/i4\\/2587278627\\/TB27GZfcMxlpuFjSszgXXcJdpXa-2587278627.jpg\",\"tag\":\"\",\"item_id\":\"537817816933\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=537817816933\",\"shop_coupon\":\"https:\\/\\/market.m.taobao.com\\/apps\\/aliyx\\/coupon\\/detail.html?wh_weex=true&activityId=3eba2f2e888b48cb83d735418fd3285e&sellerId=2587278627\",\"shop_coupon_price\":30,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504197000,\"create_time\":1504259522,\"refresh_time\":1504259522,\"caption\":\"满80元减20元\"},{\"id\":121916,\"origin\":0,\"sales_amount\":1171,\"price\":\"51.10\",\"market_price\":\"56.10\",\"name\":\"2017韩版秋冬季新款棉衣女中长款修身显瘦棉袄保暖大毛领棉服外套\",\"cover\":\"http:\\/\\/img.alicdn.com\\/bao\\/uploaded\\/i2\\/2566561877\\/TB2R87tX4f9F1JjSZFyXXXnOFXa_!!2566561877.jpg\",\"tag\":\"\",\"item_id\":\"556770059945\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=556770059945\",\"shop_coupon\":\"https:\\/\\/taoquan.taobao.com\\/coupon\\/unify_apply.htm?sellerId=2566561877&activityId=e76b493968cd4db5916dfeb56cb29bcc\",\"shop_coupon_price\":5,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504258411,\"create_time\":1504258404,\"refresh_time\":1504258404,\"caption\":\"满55元减5元\"},{\"id\":97213,\"origin\":1,\"sales_amount\":720,\"price\":\"14.90\",\"market_price\":\"29.90\",\"name\":\"8袋装洗衣机清洗剂全自动滚筒波轮除垢除异味除臭洗衣机槽清洁剂\",\"cover\":\"http:\\/\\/img1.tbcdn.cn\\/tfscom\\/i3\\/TB14WNdSpXXXXXZapXXXXXXXXXX_!!0-item_pic.jpg\",\"tag\":\"\",\"item_id\":\"555503907994\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=555503907994\",\"shop_coupon\":\"http:\\/\\/shop.m.taobao.com\\/shop\\/coupon.htm?seller_id=749640315&activity_id=fceafee63f7347c5b4ca65b43a249860\",\"shop_coupon_price\":15,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504458081,\"create_time\":1504488287,\"refresh_time\":1504488287,\"caption\":\" 28\"},{\"id\":64272,\"origin\":1,\"sales_amount\":1214,\"price\":\"29.90\",\"market_price\":\"59.90\",\"name\":\"静芙沁润保湿百搭精油面部护理复方按摩精油深层补水保湿提亮肤色\",\"cover\":\"http:\\/\\/img4.tbcdn.cn\\/tfscom\\/i1\\/1135021508\\/TB2bGLUmNxmpuFjSZFNXXXrRXXa_!!1135021508.jpg\",\"tag\":\"\",\"item_id\":\"547671148944\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=547671148944\",\"shop_coupon\":\"https:\\/\\/market.m.taobao.com\\/apps\\/aliyx\\/coupon\\/detail.html?wh_weex=true&activityId=b15b2a6ff11642fb938b2f8db49f60a5&sellerId=1135021508\",\"shop_coupon_price\":30,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504197000,\"create_time\":1504257316,\"refresh_time\":1504257316,\"caption\":\"满48元减25元\"},{\"id\":100226,\"origin\":0,\"sales_amount\":1502,\"price\":\"33.00\",\"market_price\":\"36.00\",\"name\":\"天堂伞正品太阳伞防紫外线黑胶超强防晒晴雨两用折叠女韩版遮阳伞\",\"cover\":\"http:\\/\\/img3.tbcdn.cn\\/tfscom\\/i4\\/764903049\\/TB2yl7tXBPCIuJjSZPcXXccYFXa_!!764903049.jpg\",\"tag\":\"\",\"item_id\":\"528297520846\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=528297520846\",\"shop_coupon\":\"http:\\/\\/shop.m.taobao.com\\/shop\\/coupon.htm?seller_id=764903049&activity_id=0ad5f4bb617c4798b0435dc90b7921ff\",\"shop_coupon_price\":3,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504112470,\"create_time\":1504256744,\"refresh_time\":1504256744,\"caption\":\" 30\"},{\"id\":100546,\"origin\":1,\"sales_amount\":3819,\"price\":\"9.90\",\"market_price\":\"29.90\",\"name\":\"杰士邦超凡黄金持久装避孕套超薄情趣男用女用成人用品安全套byt\",\"cover\":\"http:\\/\\/img4.tbcdn.cn\\/tfscom\\/i2\\/3327964786\\/TB1C.1yb.UIL1JjSZFrXXb3xFXa_!!0-item_pic.jpg\",\"tag\":\"\",\"item_id\":\"555115744036\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=555115744036\",\"shop_coupon\":\"https:\\/\\/market.m.taobao.com\\/apps\\/aliyx\\/coupon\\/detail.html?wh_weex=true&activityId=3b0495e53b0b404984e2cd5afcf7e50a&sellerId=3327964786\",\"shop_coupon_price\":20,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504197000,\"create_time\":1504493970,\"refresh_time\":1504493970,\"caption\":\"满29元减20元\"},{\"id\":123004,\"origin\":1,\"sales_amount\":139,\"price\":\"28.00\",\"market_price\":\"78.00\",\"name\":\"奢博士本田冠道雨刮器哥瑞锋范CRV奥德赛杰德缤智雅阁无骨雨刷\",\"cover\":\"http:\\/\\/img.alicdn.com\\/bao\\/uploaded\\/i4\\/TB1KqoRQXXXXXcCXVXXXXXXXXXX_!!0-item_pic.jpg\",\"tag\":\"\",\"item_id\":\"547417126206\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=547417126206\",\"shop_coupon\":\"https:\\/\\/taoquan.taobao.com\\/coupon\\/unify_apply.htm?sellerId=3171741277&activityId=2d874344375a4985b9ec5895635f82f1\",\"shop_coupon_price\":50,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504110600,\"create_time\":1504502209,\"refresh_time\":1504502209,\"caption\":\"满70元减50元\"},{\"id\":123110,\"origin\":1,\"sales_amount\":674,\"price\":\"139.00\",\"market_price\":\"189.00\",\"name\":\"与狼共舞卫衣男2017秋装新款男士圆领韩版印花套头长袖T恤潮 6615\",\"cover\":\"http:\\/\\/img2.tbcdn.cn\\/tfscom\\/i3\\/1015611434\\/TB1oBLiaiERMeJjSspiXXbZLFXa_!!0-item_pic.jpg\",\"tag\":\"\",\"item_id\":\"557071111580\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=557071111580\",\"shop_coupon\":\"http:\\/\\/shop.m.taobao.com\\/shop\\/coupon.htm?seller_id=1015611434&activity_id=930b191878234a8ca7ba96a724e4781a\",\"shop_coupon_price\":50,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504492888,\"create_time\":1504503258,\"refresh_time\":1504503258,\"caption\":\" 189\"},{\"id\":123158,\"origin\":0,\"sales_amount\":1547,\"price\":\"158.00\",\"market_price\":\"178.00\",\"name\":\"亿力家用吸尘器强力吸尘机大功率地毯手持式工业大功率除螨超静音\",\"cover\":\"http:\\/\\/img2.tbcdn.cn\\/tfscom\\/i2\\/1870681477\\/TB2BXr2l80kpuFjSsziXXa.oVXa_!!1870681477.jpg\",\"tag\":\"\",\"item_id\":\"534241927609\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=534241927609\",\"shop_coupon\":\"https:\\/\\/taoquan.taobao.com\\/coupon\\/unify_apply.htm?sellerId=1870681477&activityId=8d97103d2bdc42d090547795adb280f5\",\"shop_coupon_price\":20,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1501000200,\"create_time\":1504503957,\"refresh_time\":1504503957,\"caption\":\"满178元减20元\"},{\"id\":123201,\"origin\":1,\"sales_amount\":1535,\"price\":\"148.00\",\"market_price\":\"158.00\",\"name\":\"风衣女中长款双排扣2017韩版春季新款春秋宽松休闲卡其色学生外套\",\"cover\":\"http:\\/\\/img.alicdn.com\\/bao\\/uploaded\\/i2\\/TB1q2_3QXXXXXbpXXXXXXXXXXXX_!!0-item_pic.jpg\",\"tag\":\"\",\"item_id\":\"547151430985\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=547151430985\",\"shop_coupon\":\"https:\\/\\/taoquan.taobao.com\\/coupon\\/unify_apply.htm?sellerId=2269238568&activityId=9fb3a856ab324fe6993352d20fe7825a\",\"shop_coupon_price\":10,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504504592,\"create_time\":1504504568,\"refresh_time\":1504504568,\"caption\":\"满118元减10元\"},{\"id\":123220,\"origin\":0,\"sales_amount\":21,\"price\":\"10.40\",\"market_price\":\"15.40\",\"name\":\"加厚和室棉麻布艺蒲团坐垫瑜伽禅修飘窗阳台地板榻榻米办公室椅垫\",\"cover\":\"http:\\/\\/img.alicdn.com\\/bao\\/uploaded\\/i2\\/2833947104\\/TB2474RrHBkpuFjy1zkXXbSpFXa_!!2833947104.jpg\",\"tag\":\"\",\"item_id\":\"554986564465\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=554986564465\",\"shop_coupon\":\"https:\\/\\/taoquan.taobao.com\\/coupon\\/unify_apply.htm?sellerId=3324380224&activityId=6edb0a6c0e7842a38f75418f46f67838\",\"shop_coupon_price\":5,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504456200,\"create_time\":1504509792,\"refresh_time\":1504509792,\"caption\":\"满6元减5元\"},{\"id\":123248,\"origin\":0,\"sales_amount\":1,\"price\":\"6.41\",\"market_price\":\"9.41\",\"name\":\"迷你家用封口机小型家用封口机食品袋塑料袋手压封口机新款\",\"cover\":\"http:\\/\\/img.alicdn.com\\/bao\\/uploaded\\/i4\\/TB1AW3hKFXXXXXNXpXXXXXXXXXX_!!2-item_pic.png\",\"tag\":\"\",\"item_id\":\"524704629199\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=524704629199\",\"shop_coupon\":\"https:\\/\\/taoquan.taobao.com\\/coupon\\/unify_apply.htm?sellerId=543373197&activityId=7020735b01394976a711848d328b6737\",\"shop_coupon_price\":3,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504510293,\"create_time\":1504510268,\"refresh_time\":1504510268,\"caption\":\"满5元减3元\"},{\"id\":123245,\"origin\":0,\"sales_amount\":4438,\"price\":\"7.80\",\"market_price\":\"10.80\",\"name\":\"儿童发饰韩国公主超仙女童宝宝婴儿发圈发夹头绳头花橡小女孩皮筋\",\"cover\":\"http:\\/\\/img2.tbcdn.cn\\/tfscom\\/i3\\/1734632867\\/TB2dLNYv88lpuFjSspaXXXJKpXa_!!1734632867.jpg\",\"tag\":\"\",\"item_id\":\"554635927916\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=554635927916\",\"shop_coupon\":\"https:\\/\\/taoquan.taobao.com\\/coupon\\/unify_apply.htm?sellerId=1734632867&activityId=e4f4011ebab74595843e7fe70e2de503\",\"shop_coupon_price\":3,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504456200,\"create_time\":1504510229,\"refresh_time\":1504510229,\"caption\":\"满10元减3元\"},{\"id\":123688,\"origin\":1,\"sales_amount\":59,\"price\":\"65.00\",\"market_price\":\"68.00\",\"name\":\"2016新款休闲简约迷你双肩包女韩版潮旅行小背包pu皮学院复古书包\",\"cover\":\"http:\\/\\/img.alicdn.com\\/bao\\/uploaded\\/i4\\/TB1.UgSKVXXXXatXXXXXXXXXXXX_!!0-item_pic.jpg\",\"tag\":\"\",\"item_id\":\"525344657870\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=525344657870\",\"shop_coupon\":\"https:\\/\\/taoquan.taobao.com\\/coupon\\/unify_apply.htm?sellerId=760894810&activityId=a3f97562fc1948019f47607ea751c55a\",\"shop_coupon_price\":3,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504578217,\"create_time\":1504578218,\"refresh_time\":1504578218,\"caption\":\"满59元减3元\"},{\"id\":87939,\"origin\":1,\"sales_amount\":26797,\"price\":\"11.80\",\"market_price\":\"14.80\",\"name\":\"穿月大号桃木梳子可爱檀木梳香卷发按摩梳防静电刻字脱发送女神\",\"cover\":\"http:\\/\\/img2.tbcdn.cn\\/tfscom\\/i4\\/TB1fafmQVXXXXa4XVXXYXGcGpXX_M2.SS2\",\"tag\":\"\",\"item_id\":\"534624073260\",\"url\":\"https:\\/\\/detail.tmall.com\\/item.htm?id=534624073260\",\"shop_coupon\":\"https:\\/\\/market.m.taobao.com\\/apps\\/aliyx\\/coupon\\/detail.html?wh_weex=true&activityId=04e62ec5823743818f27bbdb17f15d9c&sellerId=2452866850\",\"shop_coupon_price\":3,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504578391,\"create_time\":1504578457,\"refresh_time\":1504578457,\"caption\":\"\"},{\"id\":120011,\"origin\":0,\"sales_amount\":4267,\"price\":\"39.00\",\"market_price\":\"49.00\",\"name\":\"百搭透气宝宝网鞋儿童软底室内防滑男童幼儿园鞋女童小白鞋运动鞋\",\"cover\":\"http:\\/\\/img2.tbcdn.cn\\/tfscom\\/i2\\/2895987644\\/TB2aJPIa_cCL1FjSZFPXXXZgpXa_!!2895987644.jpg\",\"tag\":\"\",\"item_id\":\"544623408976\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=544623408976\",\"shop_coupon\":\"http:\\/\\/shop.m.taobao.com\\/shop\\/coupon.htm?seller_id=2895987644&activity_id=223717aa4ef54398903bc459104bbf9e\",\"shop_coupon_price\":10,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504543836,\"create_time\":1504578079,\"refresh_time\":1504578079,\"caption\":\" 49\"},{\"id\":13081,\"origin\":1,\"sales_amount\":11257,\"price\":\"69.00\",\"market_price\":\"109.00\",\"name\":\"闲功夫澳洲进口家庭牛排套餐团购11片新鲜牛肉菲力黑椒刀叉包邮\",\"cover\":\"http:\\/\\/img2.tbcdn.cn\\/tfscom\\/i3\\/3016963225\\/TB2aCLUbCmK.eBjSZPfXXce2pXa_!!3016963225.jpg\",\"tag\":\"\",\"item_id\":\"540955321344\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=540955321344\",\"shop_coupon\":\"http:\\/\\/shop.m.taobao.com\\/shop\\/coupon.htm?seller_id=3016963225&activity_id=64f8e9332c2741bfabcf3d914d8f5746\",\"shop_coupon_price\":40,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504573805,\"create_time\":1504578190,\"refresh_time\":1504578190,\"caption\":\" 109\"},{\"id\":122633,\"origin\":0,\"sales_amount\":15229,\"price\":\"11.88\",\"market_price\":\"14.88\",\"name\":\"周大人成人纸尿裤中大码ML号男女士纸尿片护理垫尿床垫老年尿不湿\",\"cover\":\"http:\\/\\/img2.tbcdn.cn\\/tfscom\\/i2\\/2158141426\\/TB21e6ja_ZRMeJjSsppXXXrEpXa_!!2158141426.jpg\",\"tag\":\"\",\"item_id\":\"44401518952\",\"url\":\"https:\\/\\/item.taobao.com\\/item.htm?id=44401518952\",\"shop_coupon\":\"https:\\/\\/market.m.taobao.com\\/apps\\/aliyx\\/coupon\\/detail.html?wh_weex=true&activityId=77337ffea70d4a13a0827d640f7ac162&sellerId=2158141426\",\"shop_coupon_price\":3,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504338702,\"create_time\":1504338745,\"refresh_time\":1504338745,\"caption\":\"3元优惠券\"},{\"id\":52790,\"origin\":1,\"sales_amount\":13748,\"price\":\"29.00\",\"market_price\":\"49.00\",\"name\":\"恩诺童儿童水杯 吸管杯宝宝学饮杯带手柄鸭嘴杯 婴儿水杯防漏防摔\",\"cover\":\"http:\\/\\/img2.tbcdn.cn\\/tfscom\\/i3\\/TB1k6elSpXXXXbqXXXXXXXXXXXX_!!0-item_pic.jpg\",\"tag\":\"\",\"item_id\":\"44763941771\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=44763941771\",\"shop_coupon\":\"https:\\/\\/market.m.taobao.com\\/apps\\/aliyx\\/coupon\\/detail.html?wh_weex=true&activityId=d15d20175a0d4e0face1ec7027c376ae&sellerId=1862989607\",\"shop_coupon_price\":20,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504400470,\"create_time\":1504487723,\"refresh_time\":1504487723,\"caption\":\" 35\"},{\"id\":90347,\"origin\":1,\"sales_amount\":57640,\"price\":\"29.00\",\"market_price\":\"69.00\",\"name\":\"卡帝乐鳄鱼男士皮带自动扣真皮商务青年休闲中年韩版潮牛皮裤腰带\",\"cover\":\"http:\\/\\/img4.tbcdn.cn\\/tfscom\\/i4\\/356292772\\/TB2sys8aUF7MKJjSZFLXXcMBVXa_!!356292772.jpg\",\"tag\":\"\",\"item_id\":\"553696147508\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=553696147508\",\"shop_coupon\":\"http:\\/\\/shop.m.taobao.com\\/shop\\/coupon.htm?seller_id=356292772&activity_id=1db32d39fa764416a97d2c2f497b0797\",\"shop_coupon_price\":40,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1504517428,\"create_time\":1504575356,\"refresh_time\":1504575356,\"caption\":\" 65\"},{\"id\":79732,\"origin\":0,\"sales_amount\":13833,\"price\":\"33.90\",\"market_price\":\"38.90\",\"name\":\"正品羽毛球拍双拍耐打男女超轻2支装成人初学进攻型健身单拍ymqp\",\"cover\":\"http:\\/\\/img2.tbcdn.cn\\/tfscom\\/i1\\/TB1I.MzRVXXXXctaXXXXXXXXXXX_!!0-item_pic.jpg\",\"tag\":\"\",\"item_id\":\"552803680567\",\"url\":\"http:\\/\\/item.taobao.com\\/item.htm?id=552803680567\",\"shop_coupon\":\"http:\\/\\/shop.m.taobao.com\\/shop\\/coupon.htm?seller_id=784474264&activity_id=64aa6fc64e1e4351ac60381e01e967a5\",\"shop_coupon_price\":10,\"official_coupon\":\"\",\"official_coupon_price\":0,\"start_time\":1499060541,\"create_time\":1501743372,\"refresh_time\":1501743372,\"caption\":\" 30\"}]}";
        try {
            JSONObject jsonObject = new JSONObject(data);
            total = jsonObject.optInt("total");
            int pages = jsonObject.optInt("pages");
            String pagelist = jsonObject.optString("list");
            Gson gson = new Gson();
            list = gson.fromJson(pagelist, new TypeToken<List<ProductBean>>() {
            }.getType());
            collectionAdapter.setList(list);
            isInit = true;
            mListView.stopRefresh();

                mListView.setPullLoadEnable(false);

            if (total == 0) {
                //
                ll_no_product.setVisibility(View.VISIBLE);
//                            ActivityHelper.showTips(MainActivity.this, "你还没有收藏商品");
            } else {
                if (ll_no_product != null) {
                    ll_no_product.setVisibility(View.GONE);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void initListener() {
        tv_around.setOnClickListener(this);
        cb_biangi.setOnCheckedChangeListener(this);
        cb_sellectAll.setOnCheckedChangeListener(this);
        tv_delete.setOnClickListener(this);
        mListView.setXListViewListener(new DocumentListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                if (isInit) {
                    isInit = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            initcollectionData();
                        }
                    }, 1500);
                }
            }

            @Override
            public void onLoadMore() {
                if (isInit) {
                    moreData();
                }
            }

        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                if (!shareSate) {
//
//                    Intent intent = new Intent(MainActivity.this, AliSdkTransactionActivity.class);
//                    if (position > 0) {
//                        //将category_id作为商品设置接口的参数
//                        RelativeLayout rl_share = (RelativeLayout) view.findViewById(R.id.rl_share);
//                        if (rl_share.isShown()) {
//                            rl_share.setVisibility(View.GONE);
//                        } else {
//                            intent.putExtra("goods_id", ((collectionAdapter.getItem(position - 1))).getId());
//                            startActivity(intent);
//                        }
//
//
//                    }
//                }
                shareSate = false;
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            //            private View del
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                    view.setVisibility(View.VISIBLE);
                shareSate = true;
                if (collectionAdapter.getItem(position - 1) instanceof ProductBean) {
                    int total = parent.getChildCount();
                    for (int i = 1; i <= total; i++) {
                        if (parent.getChildAt(i - 1) instanceof RelativeLayout) {
                            RelativeLayout rl_share2 = (RelativeLayout) parent.getChildAt(i - 1).findViewById(R.id.rl_share);
                            if (rl_share2 != null && rl_share2.isShown()) {
                                rl_share2.setVisibility(View.GONE);
                            }
                        }

                    }
                    RelativeLayout rl_share = (RelativeLayout) view.findViewById(R.id.rl_share);
                    rl_share.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });


    }

    private void moreData() {
        ArrayList<ProductBean> list = new ArrayList<ProductBean>();
        try {
            int total = 0;
            JSONObject jsonObject = new JSONObject("");
            total = jsonObject.optInt("total");
            int pages = jsonObject.optInt("pages");
            String pagelist = jsonObject.optString("list");
            Gson gson = new Gson();
            list = gson.fromJson(pagelist, new TypeToken<List<ProductBean>>() {
            }.getType());
            collectionAdapter.addList(list);
            isInit = true;
            mListView.stopRefresh();
            if (page < pages) {
                mListView.setPullLoadEnable(true);
            } else {
                mListView.setPullLoadEnable(false);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void findView() {
        tv_around = (TextView) findViewById(R.id.tv_go_to);
        ll_no_product = (LinearLayout) findViewById(R.id.ll_no_product);
        mListView = (DocumentListView) findViewById(R.id.listview_collection);
        cb_biangi = (CheckBox) findViewById(R.id.cb_biangi);
        cb_sellectAll = (CheckBox) findViewById(R.id.cb_sellectAll);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        ll_edit = (RelativeLayout) findViewById(R.id.ll_edit);
        mListView.setPullLoadEnable(false);
        list = new ArrayList<>();
        collectionAdapter = new CollectionAdapter(this, list);
        mListView.setAdapter(collectionAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_delete:
                List<ProductBean> list = collectionAdapter.getListBeans();
                List<Integer> listislike = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    ProductBean productBean = list.get(i);
                    if (productBean.getIslike() == 1) {
                        listislike.add(productBean.getId());
                    }
                }
                deleteCollection(listislike);
                break;
            case R.id.tv_go_to:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.cb_biangi:
                break;
        }

    }

    private void deleteCollection(List<Integer> listislike) {
        Map<String, String> map = new HashMap<>();
        String goods_id = "[";
        for (int i = 0; i < listislike.size(); i++) {
            goods_id = goods_id.concat((int) listislike.get(i) + ",");
        }
        if (goods_id.endsWith(",")) {
            goods_id = goods_id.substring(0, goods_id.length() - 1);
            goods_id = goods_id.concat("]");
        }
        List<ProductBean>listp= collectionAdapter.getListBeans();
        for (int i = 0; i <listislike.size() ; i++) {
            for (int j = 0; j <listp.size() ; j++) {
                if (listp.get(j).getId()==listislike.get(i)){
                    listp.remove(j);
                }
            }
        }
        collectionAdapter.setList(listp);

        ActivityHelper.showTips(MainActivity.this, "删除成功");
//        initcollectionData();
        cb_sellectAll.setChecked(false);


    }

    public void onClickback(View view) {
        finish();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_biangi:
                if (isChecked) {
                    cb_biangi.setText("完成");
                    ll_edit.setVisibility(View.VISIBLE);
                    cb_sellectAll.setChecked(false);
                    int count = mListView.getChildCount();
                    collectionAdapter.setItemCheckboxVisibility(1, 0);
                    for (int i = 0; i < count; i++) {
                        if (mListView.getChildAt(i) instanceof RelativeLayout) {
                            CheckBox checkBox = (CheckBox) mListView.getChildAt(i).findViewById(R.id.cb_collection_item);
                            if (checkBox != null && !checkBox.isShown()) {
                                checkBox.setVisibility(View.VISIBLE);
                                checkBox.setChecked(false);
                            }
                        }
                    }
                } else {
                    cb_biangi.setText("编辑");
                    ll_edit.setVisibility(View.GONE);
                    int count = mListView.getChildCount();
                    collectionAdapter.setItemCheckboxVisibility(0, 0);
                    for (int i = 0; i < count; i++) {
                        if (mListView.getChildAt(i) instanceof RelativeLayout) {
                            CheckBox checkBox = (CheckBox) mListView.getChildAt(i).findViewById(R.id.cb_collection_item);
                            if (checkBox != null && checkBox.isShown()) {
                                checkBox.setVisibility(View.GONE);
                            }
                        }
                    }
                }
                break;
            case R.id.cb_sellectAll:
                int count = mListView.getChildCount();
                if (isChecked) {
                    collectionAdapter.setItemCheckboxVisibility(1, 1);
                    for (int i = 0; i < count; i++) {
                        if (mListView.getChildAt(i) instanceof RelativeLayout) {
                            CheckBox checkBox = (CheckBox) mListView.getChildAt(i).findViewById(R.id.cb_collection_item);
                            if (checkBox != null && checkBox.isShown()) {
                                checkBox.setChecked(true);
                            }
                        }
                    }
                    List<ProductBean> list = collectionAdapter.getListBeans();
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setIslike(1);

                    }
                } else {
                    collectionAdapter.setItemCheckboxVisibility(1, 0);
                    for (int i = 0; i < count; i++) {
                        if (mListView.getChildAt(i) instanceof RelativeLayout) {
                            CheckBox checkBox = (CheckBox) mListView.getChildAt(i).findViewById(R.id.cb_collection_item);
                            if (checkBox != null && checkBox.isShown()) {
                                checkBox.setChecked(false);
                            }
                        }
                    }
                    List<ProductBean> list = collectionAdapter.getListBeans();
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setIslike(0);

                    }
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}