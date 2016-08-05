package vikram.slideshow;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    ViewFlipper flipper;
    LinearLayout indicatorLyt;
    ImageView page_indicator[];
    int count = 0;
    int lastPosition = 0;
    Context mContext;
    int images[] = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4};
    private GestureDetector mGestureDetector;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        indicatorLyt = (LinearLayout) findViewById(R.id.indicatorLyt);

        flipper.removeAllViews();
        for (int i = 0; i < 4; i++) {
            RelativeLayout relativeLayout = new RelativeLayout(mContext);
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.image_slide, relativeLayout, false);

            ImageView imgView = (ImageView) view.findViewById(R.id.img_view);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView desc = (TextView) view.findViewById(R.id.desc);
            imgView.setBackgroundResource(images[i]);
            imgView.setPadding(5,5,5,5);

            title.setText("Title "+(i+1));
            desc.setText("Description "+(i+1));
            flipper.addView(view);
        }

        count = 0;
        count = flipper.getChildCount();
        indicatorLyt.removeAllViews();
        page_indicator = new ImageView[count];
        for (int i = 0; i < count; i++) {
            page_indicator[i] = new ImageView(mContext);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                page_indicator[i].setBackground(mContext.getResources().getDrawable(R.drawable.ic_off));
            } else {
                page_indicator[i].setBackgroundResource(R.drawable.ic_off);
            }
            indicatorLyt.addView(page_indicator[i]);
        }

        page_indicator[0].setBackgroundResource(R.drawable.ic_on);
        flipper.startFlipping();
        flipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                setCircle();
            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {

            }
        });
      /*  CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);*/
    }

    private void setCircle() {
        for (int i = 0; i < count; i++) {
            page_indicator[i].setBackgroundResource(R.drawable.ic_off);
        }
        page_indicator[flipper.getDisplayedChild()].setBackgroundResource(R.drawable.ic_on);
    }

    /*class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                flipper.setInAnimation(MainActivity.this, R.anim.push_in);
                flipper.setOutAnimation(MainActivity.this, R.anim.push_out);
                flipper.showNext();
                setCircle();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                flipper.setInAnimation(MainActivity.this, R.anim.right_in);
                flipper.setOutAnimation(MainActivity.this, R.anim.right_out);
                flipper.showPrevious();
                setCircle();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }*/
    
}
