package zhike.com.mylistview.bean;


import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/16.
 */

public class ProductBean implements  Serializable {


    /**
     * id : 8
     * origin : 0
     * sales_amount : 472
     * price : 26.90
     * market_price : 46.90
     * name : 韩国 Innis-free/悦诗-风吟绿茶洗面奶 清洁保湿补水控油洁面乳女
     * cover : http://img03.taobaocdn.com/bao/uploaded/i3/TB1Yr5tOXXXXXXxXVXXXXXXXXXX_!!0-item_pic.jpg
     * tag : 美妆、洗面奶
     * url : https://item.taobao.com/item.htm?id=541339604871
     * official_coupon :
     * start_time : 1481328000
     */

    private String ppd;
    private String taoke_code;

    public String getTaoke_code() {
        return taoke_code;
    }

    public void setTaoke_code(String taoke_code) {
        this.taoke_code = taoke_code;
    }

    public String getPpd() {
        return ppd;
    }

    public void setPpd(String ppd) {
        this.ppd = ppd;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    private int dig = 0;
    private int status;
    private long end_time;
    private String caption;
    private int id;
    private int origin;
    private int sales_amount;
    private String price;
    private String market_price;
    private String name = "";
    private String cover;
    private String tag;
    private String url;
    private String official_coupon;
    private long start_time;
    private int itemtype;
    private float shop_coupon_price = 0;
    private int fragmentType = 0;
    private double official_coupon_price = 0;
    private String shop_coupon;
    private int view;
    private int isdelete;
    private int create_time;
    private int islike;
    private int isFirst = 0;
    private int isRemind;
    private int goods_id;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
    }

    public int getIslike() {
        return islike;
    }

    public void setIslike(int islike) {
        this.islike = islike;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    private int update_time;
    private int refresh_time;
    private int is_owner;
    private int category_id;
    private int brand = 0;
    private float shop_coupon_rate = -1;
    private String shop_coupon_rest;

    public String getShop_coupon_rest() {
        return shop_coupon_rest;
    }

    public void setShop_coupon_rest(String shop_coupon_rest) {
        this.shop_coupon_rest = shop_coupon_rest;
    }

    private String shop_coupon_count;

    public float getShop_coupon_rate() {
        return shop_coupon_rate;
    }

    public void setShop_coupon_rate(float shop_coupon_rate) {
        this.shop_coupon_rate = shop_coupon_rate;
    }


    public String getShop_coupon_count() {
        return shop_coupon_count;
    }

    public void setShop_coupon_count(String shop_coupon_count) {
        this.shop_coupon_count = shop_coupon_count;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public int getCategroy_id() {
        return category_id;
    }

    public void setCategroy_id(int category_id) {
        this.category_id = category_id;
    }

    public int getIs_owner() {
        return is_owner;
    }

    public void setIs_owner(int is_owner) {
        this.is_owner = is_owner;
    }

    public int getRefresh_time() {
        return refresh_time;
    }

    public void setRefresh_time(int refresh_time) {
        this.refresh_time = refresh_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDig() {
        return dig;
    }

    public void setDig(int dig) {
        this.dig = dig;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public String getShop_coupon() {
        return shop_coupon;
    }

    public void setShop_coupon(String shop_coupon) {
        this.shop_coupon = shop_coupon;
    }

    public double getOfficial_coupon_price() {
        return official_coupon_price;
    }

    public void setOfficial_coupon_price(double official_coupon_price) {
        this.official_coupon_price = official_coupon_price;
    }

    public float getShop_coupon_price() {
        return shop_coupon_price;
    }

    public void setShop_coupon_price(float shop_coupon_price) {
        this.shop_coupon_price = shop_coupon_price;
    }

    public int getFragmentType() {
        return fragmentType;
    }

    public void setFragmentType(int fragmentType) {
        this.fragmentType = fragmentType;
    }


    public int getItemtype() {
        return itemtype;
    }

    private String item_id = "";

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setJSONObject(JSONObject jsonObject) {
        this.end_time = jsonObject.optLong("end_time");
        this.shop_coupon = jsonObject.optString("shop_coupon");
        this.official_coupon_price = jsonObject.optInt("official_coupon_price");
        this.shop_coupon_price = jsonObject.optInt("shop_coupon_price");
        this.id = jsonObject.optInt("id");
        this.origin = jsonObject.optInt("origin");
        this.sales_amount = jsonObject.optInt("sales_amount");
        this.start_time = jsonObject.optLong("start_time");
        this.price = jsonObject.optString("price");
        this.market_price = jsonObject.optString("market_price");
        this.name = jsonObject.optString("name");
        this.cover = jsonObject.optString("cover");
        this.tag = jsonObject.optString("tag");
        this.url = jsonObject.optString("url");
        this.shop_coupon_rest = String.valueOf(jsonObject.optInt("shop_coupon_rest"));
        this.shop_coupon_rate = (float) jsonObject.optDouble("shop_coupon_rate");
        this.official_coupon = jsonObject.optString("official_coupon");
        this.create_time = jsonObject.optInt("create_time");
        this.caption = jsonObject.optString("caption");
        this.item_id = jsonObject.optString("item_id");
        this.refresh_time = jsonObject.optInt("refresh_time");
        this.is_owner = jsonObject.optInt("is_owner");
        this.category_id = jsonObject.optInt("category_id");
        this.isRemind = jsonObject.optInt("isRemind");
        this.dig = jsonObject.optInt("dig");
//        this.brand = jsonObject.optInt("brand");

        String[] urlid = url.split("id=");

    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setItemtype(int itemtype) {
        this.itemtype = itemtype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getSales_amount() {
        return sales_amount;
    }

    public void setSales_amount(int sales_amount) {
        this.sales_amount = sales_amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOfficial_coupon() {
        return official_coupon;
    }

    public void setOfficial_coupon(String official_coupon) {
        this.official_coupon = official_coupon;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }


    public void setIsRemind(int isRemind) {
        this.isRemind = isRemind;
    }

    public int getIsRemind() {
        return isRemind;
    }
}
