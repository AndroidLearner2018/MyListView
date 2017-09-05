package zhike.com.mylistview.adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import zhike.com.mylistview.R;
import zhike.com.mylistview.View.ConvertDpPx;
import zhike.com.mylistview.bean.ProductBean;
import zhike.com.mylistview.utils.ActivityHelper;
import zhike.com.mylistview.utils.Xutils;

/**
 * Created by Administrator on 2016/12/2.
 */

public class CollectionAdapter extends BaseAdapter implements View.OnClickListener {
    Activity mActivity;
    private List<ProductBean> detailBeans;
    private int position;

    public List<ProductBean> getListBeans() {
        return detailBeans;
    }

    public CollectionAdapter(Activity mActivity, List<ProductBean> detailBeans) {
        this.mActivity = mActivity;
        this.detailBeans = detailBeans;
    }

    public void setList(List<ProductBean> detailBeans) {
        this.detailBeans = detailBeans;
        notifyDataSetChanged();
    }

    public void addList(List<ProductBean> detailBeans) {
        this.detailBeans.addAll(detailBeans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return detailBeans.size();
    }

    @Override
    public ProductBean getItem(int i) {
        if (i < detailBeans.size()) {
            return detailBeans.get(i);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private int isVisibility, isChecked;

    public void setItemCheckboxVisibility(int isVisibility, int isChecked) {
        this.isVisibility = isVisibility;
        this.isChecked = isChecked;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder mHolder;
        this.position = position;
        ProductBean bean = (ProductBean) getItem(position);
        if (convertView == null) {

            convertView = LayoutInflater.from(mActivity).inflate(R.layout.list_item_collect_goods, null);
            mHolder = new ViewHolder();
            mHolder.rl_share = (RelativeLayout) convertView.findViewById(R.id.rl_share);
            mHolder.img_share = (ImageView) convertView.findViewById(R.id.img_share);
            mHolder.item_title = (TextView) convertView.findViewById(R.id.tvGoodsName);
            mHolder.imageView = (ImageView) convertView.findViewById(R.id.imgCover);
            mHolder.ll_tag_item1_home = (LinearLayout) convertView.findViewById(R.id.tagView);
            mHolder.tv_price = (TextView) convertView.findViewById(R.id.tvPrice);
            mHolder.tv_oldprice = (TextView) convertView.findViewById(R.id.tvMarketPrice);
            mHolder.tvCommom = (TextView) convertView.findViewById(R.id.tvCommom);
            mHolder.cb_collection_item = (CheckBox) convertView.findViewById(R.id.cb_collection_item);
            if (isVisibility == 1) {
                if (mHolder.cb_collection_item != null && !mHolder.cb_collection_item.isShown()) {
                    mHolder.cb_collection_item.setVisibility(View.VISIBLE);
                }
            } else {
                if (mHolder.cb_collection_item != null && mHolder.cb_collection_item.isShown()) {
                    mHolder.cb_collection_item.setVisibility(View.GONE);
                }
            }
            if (isChecked == 1) {
                if (mHolder.cb_collection_item != null) {
                    mHolder.cb_collection_item.setChecked(true);
                }
            } else {
                if (mHolder.cb_collection_item != null) {
                    mHolder.cb_collection_item.setChecked(false);
                }
            }
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
            mHolder.rl_share.setVisibility(View.GONE);
            if (isVisibility == 1) {
                if (mHolder.cb_collection_item != null && !mHolder.cb_collection_item.isShown()) {
                    mHolder.cb_collection_item.setVisibility(View.VISIBLE);

                }
            } else {
                if (mHolder.cb_collection_item != null) {
                    mHolder.cb_collection_item.setVisibility(View.GONE);
                }
            }
            if (isChecked == 1) {
                if (mHolder.cb_collection_item != null) {
                    mHolder.cb_collection_item.setChecked(true);
                }
            } else {
                if (mHolder.cb_collection_item != null) {
                    mHolder.cb_collection_item.setChecked(false);
                }
            }
        }
        //设置资源

        //普通用户fragment的item的填充数据
        mHolder.item_title.setText(bean.getName());
        Xutils.getInstance().bindCommonImage(mHolder.imageView, bean.getCover()+"_120x120.jpg", true);
        mHolder.tv_oldprice.setText("¥" + bean.getMarket_price());
        mHolder.tv_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mHolder.tv_price.setText("¥" + bean.getPrice());

        setTag(bean.getTag(), mHolder.ll_tag_item1_home);
        int startTime = bean.getRefresh_time();
        int systemTime = (int) (System.currentTimeMillis() / 1000);
        int diffrence = systemTime - startTime;
        mHolder.tvCommom.setText("");
        mHolder.img_share.setOnClickListener(new popAction(position));
        if (bean.getIslike() == 1) {
            mHolder.cb_collection_item.setChecked(true);
        } else {
            mHolder.cb_collection_item.setChecked(false);

        }

//        mHolder.cb_collection_item.setOnCheckedChangeListener(new CheckBoxChecked(position));
        mHolder.cb_collection_item.setOnClickListener(new CheckBoxClicked(position));
        return convertView;
    }

    public class CheckBoxClicked implements View.OnClickListener {
        int position;

        public CheckBoxClicked(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            ProductBean productBean = getItem(position);
            CheckBox checkBox = (CheckBox) v;
            if (productBean != null) {
                if (checkBox.isChecked()) {

                    productBean.setIslike(1);

                } else {
                    productBean.setIslike(0);

                }
            }

        }
    }
//
//    public class CheckBoxChecked implements CompoundButton.OnCheckedChangeListener {
//        int position;
//
//        public CheckBoxChecked(int position) {
//            this.position = position;
//        }
//
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            ProductBean productBean=getItem(position);
//            if (productBean!=null) {
//                if (isChecked) {
//                    if (productBean.getIsSeletAll()==1) {
//                        productBean.setIslike(1);
//                    }
//                } else {
//                    productBean.setIslike(0);
//                    productBean.setIsSeletAll(0);
//                }
//            }
//
//        }
//    }

    public class popAction implements View.OnClickListener {
        int position;


        public popAction(int position) {
            this.position = position;

        }

        @Override
        public void onClick(View v) {



        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {




        }

    }

    //编码方法
    private String UrlEncode(String taoke_code) {
        String encodeStr = null;
        try {
            encodeStr = URLEncoder.encode(taoke_code, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private PopupWindow popShare;
    private int share_postion;




    private PopupWindow mPopupWindow;
    private int index;//索引
    private int pushgoods_id = -1;
    private ImageView imageViewup, imageViewbottom;
    private Button btnDeleteGoods;
    private TextView tv_edit_number;
    private GridView gridView_edit;


    //用户不感兴趣接口调用，删除商品
    private void postDisinclineGoods(Map maps) {
        maps.put("goods_id", "" + pushgoods_id);
        ActivityHelper.showTips(mActivity, "删除成功");
        detailBeans.remove(index);
        notifyDataSetChanged();
        mPopupWindow.dismiss();

    }




    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mActivity.getWindow().setAttributes(lp);
    }

    public class ViewHolder {
        LinearLayout ll_tag_item1_home;
        //title
        TextView item_title, tv_price, tv_oldprice, tvCommom;
        //图片源
        ImageView imageView;
        RelativeLayout rl_share;
        CheckBox cb_collection_item;
        ImageView img_share;


    }


    //设置tag字段
    private void setTag(String tag, LinearLayout ll_tag) {
        if (!TextUtils.isEmpty(tag)) {
//            tv_describ.setText(tag);
            String tags[] = tag.split("、");

//            View tv_tag_layout=LayoutInflater.from(activity).inflate(R.layout.tv_tag,null);
            ll_tag.removeAllViews();
            for (int i = 0; i < tags.length; i++) {
//                TextView textView = (TextView) tv_tag_layout.findViewById(R.id.tv_tag);
                TextView textView = new TextView(mActivity);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                textView.setBackgroundResource(R.drawable.tag_background);
                textView.setText(tags[i]);
                textView.setTextColor(0xff888888);
                int padleft = ConvertDpPx.dip2px(mActivity, 10);
                int padright = ConvertDpPx.dip2px(mActivity, 10);
                int padtop = ConvertDpPx.dip2px(mActivity, 2);
                int padBottom = ConvertDpPx.dip2px(mActivity, 2);
                int margin = ConvertDpPx.dip2px(mActivity, 5);
                textView.setPadding(padleft, padtop, padright, padBottom);
                textView.setMaxLines(1);
                textView.setTextSize(11);
                params.setMargins(0, 0, margin, 0);
                textView.setLayoutParams(params);
                ll_tag.addView(textView);
            }

        } else {
//            tv_describ.setText("");
            ll_tag.removeAllViews();
        }
    }


}
