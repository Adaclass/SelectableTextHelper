package com.jaeger.selectabletexthelper;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.OnSelectListener;
import com.jaeger.library.SelectableTextHelper;

public class MainActivity extends AppCompatActivity {

    private TextView mTvTest;

    private SelectableTextHelper mSelectableTextHelper;
    private LinearLayout llRoot;
    private TextView tvTest;
    private Drawable mSimileDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTest = (TextView) findViewById(R.id.tv_test);
        //mTvTest.setTextIsSelectable(true);
        mSimileDrawable = ContextCompat.getDrawable(this, R.drawable.smiley_0);
        mSimileDrawable.setBounds(0, 0, mSimileDrawable.getIntrinsicWidth(), mSimileDrawable.getIntrinsicHeight());

        String text = getResources().getString(R.string.long_text);
        String smiley = "[微笑]";
        SpannableString ss = new SpannableString(text);
        int start = text.indexOf(smiley);
        while (start >= 0) {
            ss.setSpan(new ImageSpan(mSimileDrawable), start, start + 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            start = text.indexOf(smiley, start + 4);
        }
        mTvTest.setText(ss);
        mSelectableTextHelper = new SelectableTextHelper.Builder(mTvTest)
                .setSelectedColor(getResources().getColor(R.color.selected_blue))
                .setCursorHandleSizeInDp(20)
                .setCursorHandleColor(getResources().getColor(R.color.cursor_handle_color))
                .build();

        mSelectableTextHelper.setSelectListener(new OnSelectListener() {
            @Override
            public void onTextSelected(CharSequence content) {

            }
        });
        initView();
    }

    private void initView() {
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        tvTest = (TextView) findViewById(R.id.tv_test);
    }
}
