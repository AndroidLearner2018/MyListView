package zhike.com.mylistview.View;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhike.com.mylistview.R;


public class XListViewHeader extends LinearLayout {
    private LinearLayout mContainer;
    //	private ImageView mArrowImageView;
//	private ProgressBar mProgressBar;
    private TextView mHintTextView;
    private int mState = STATE_NORMAL;
    private ImageView mIvVoiceAnim;
    private AnimationDrawable mImageAnim;
    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;

    private final int ROTATE_ANIM_DURATION = 180;

    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;
    public final static int STATE_COMPLETED = 3;

    public XListViewHeader(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XListViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        // 初始情况，设置下拉刷新view高度�?
        @SuppressWarnings("deprecation")
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, 0);
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_part_community_header, null);
        addView(mContainer, lp);
        setGravity(Gravity.BOTTOM);

//		mArrowImageView = (ImageView) findViewById(R.id.xlistview_header_arrow);
        mHintTextView = (TextView) findViewById(R.id.xlistview_header_hint_textview);
//        mHintTextView.setTextColor(getResources().getColor(R.color.color_broke_item_text));
//		mProgressBar = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);
        mIvVoiceAnim = (ImageView) findViewById(R.id.listview_header_anim);

        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }

    public void setState(int state) {
        if (state == mState)
            return;

//		if (state == STATE_REFRESHING) { // 显示进度
//			mArrowImageView.clearAnimation();
//			mArrowImageView.setVisibility(View.INVISIBLE);
//			mProgressBar.setVisibility(View.VISIBLE);
//		} else { // 显示箭头图片
//			mArrowImageView.setVisibility(View.VISIBLE);
//			mProgressBar.setVisibility(View.INVISIBLE);
//		}

        switch (state) {
            case STATE_NORMAL:
                mIvVoiceAnim.setVisibility(View.VISIBLE);
                mHintTextView.setVisibility(View.GONE);
                if (mState == STATE_READY) {
//				mArrowImageView.startAnimation(mRotateDownAnim);
                }
                if (mState == STATE_REFRESHING) {
//				mArrowImageView.clearAnimation();
                }
//			mHintTextView.setText(R.string.xlistview_header_hint_normal);
                break;
            case STATE_READY:
                if (mState != STATE_READY) {
//				mArrowImageView.clearAnimation();
//				mArrowImageView.startAnimation(mRotateUpAnim);
//				mHintTextView.setText(R.string.xlistview_header_hint_ready);
                }
                break;
            case STATE_REFRESHING:
//			mHintTextView.setText(R.string.xlistview_header_hint_loading);
                break;
            case STATE_COMPLETED:
                mIvVoiceAnim.setVisibility(View.GONE);
                mHintTextView.setVisibility(View.VISIBLE);
                mHintTextView.setText("刷新成功");
                break;
            default:

        }

        mState = state;
    }

    public void setVisiableHeight(int height) {
        if (height < 0)
            height = 0;
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    public int getVisiableHeight() {
        return mContainer.getHeight();
    }

    /**
     * 效果
     *
     */
//	public void startAnim() {
//		mImageAnim = (AnimationDrawable) mIvVoiceAnim.getBackground();
//		mIvVoiceAnim.setVisibility(View.VISIBLE);
//		mImageAnim.start();
//	}

}
