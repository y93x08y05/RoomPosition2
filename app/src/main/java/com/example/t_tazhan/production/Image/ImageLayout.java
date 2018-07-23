package com.example.t_tazhan.production.Image;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.t_tazhan.production.R;

import java.util.ArrayList;

import static com.example.t_tazhan.production.MainActivity.locationX;
import static com.example.t_tazhan.production.MainActivity.locationY;

public class ImageLayout extends FrameLayout implements View.OnClickListener {

    public static ArrayList<PointSimple> points;

    public static FrameLayout layoutPoints;

    ImageView imgBg;

    public static Context mContext;

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {

        mContext = context;

        View imgPointLayout = inflate(context, R.layout.layout_imgview_point, this);

        imgBg = imgPointLayout.findViewById(R.id.imgBg);
        layoutPoints = imgPointLayout.findViewById(R.id.layouPoints);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
    public void setImgBg(int width, int height) {
        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = width;
        lp.height = height;
        imgBg.setLayoutParams(lp);
        ViewGroup.LayoutParams lp1 = layoutPoints.getLayoutParams();
        lp1.width = width;
        lp1.height = height;
        layoutPoints.setLayoutParams(lp1);
        addPoints(width, height);

    }

    public void setPoints(ArrayList<PointSimple> points) {

        this.points = points;
    }

    public void addPoints(int width, int height) {

        layoutPoints.removeAllViews();

        for (int i = 0; i < points.size(); i++) {
            LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_point, this, false);
            ImageView imageView = view.findViewById(R.id.imgPoint);
            imageView.setTag(i);
            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = (int)(width/30 * 2 * 7 - width/30 * 2 * 0.5);
            layoutParams.topMargin = (int)(height/50 * 2 * 20 - height/50 * 2 * 0.5);
            System.out.println("locationX" + layoutParams.leftMargin + "locationY" + layoutParams.topMargin);
            imageView.setOnClickListener(this);

            layoutPoints.addView(view, layoutParams);
        }
    }

    @Override
    public void onClick(View view) {
        int pos = (int) view.getTag();
        Toast.makeText(getContext(), "pos : " + pos, Toast.LENGTH_SHORT).show();
    }
}
