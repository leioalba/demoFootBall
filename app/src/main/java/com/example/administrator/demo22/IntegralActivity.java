package com.example.administrator.demo22;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo22.Fragment.EnglandFragment;
import com.example.administrator.demo22.Fragment.FranceFragment;
import com.example.administrator.demo22.Fragment.GermanyFragment;
import com.example.administrator.demo22.Fragment.ItalyFragment;
import com.example.administrator.demo22.Fragment.SpainFragment;

import java.util.ArrayList;
import java.util.List;

public class IntegralActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager vp;
    private ImageView ivBack;
    private TextView tvEngland,tvFrance,tvGermany,tvSpain,tvItaly;
    private EnglandFragment englandFragment;
    private FranceFragment franceFragment;
    private GermanyFragment germanyFragment;
    private ItalyFragment italyFragment;
    private SpainFragment spainFragment;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        initView();
        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(),fragmentList);
        vp.setOffscreenPageLimit(2);
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);
        tvEngland.setTextColor(Color.parseColor("#1ba0e1"));
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        tvEngland=findViewById(R.id.tv_int_England);
        tvEngland.setOnClickListener(this);
        tvFrance=findViewById(R.id.tv_int_france);
        tvFrance.setOnClickListener(this);
        tvGermany=findViewById(R.id.tv_int_Germany);
        tvGermany.setOnClickListener(this);
        tvItaly=findViewById(R.id.tv_int_Italy);
        tvItaly.setOnClickListener(this);
        tvSpain=findViewById(R.id.tv_int_Spain);
        tvSpain.setOnClickListener(this);
        vp=findViewById(R.id.vp);
        englandFragment = new EnglandFragment();
        franceFragment = new FranceFragment();
        spainFragment = new SpainFragment();
        germanyFragment = new GermanyFragment();
        italyFragment = new ItalyFragment();
        fragmentList.add(englandFragment);
        fragmentList.add(franceFragment);
        fragmentList.add(germanyFragment);
        fragmentList.add(italyFragment);
        fragmentList.add(spainFragment);
        ivBack=findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IntegralActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.tv_int_England:
                    vp.setCurrentItem(0,true);
                    break;
                case R.id.tv_int_france:
                    vp.setCurrentItem(1,true);
                    break;
                case R.id.tv_int_Germany:
                    vp.setCurrentItem(2,true);
                    break;
                case R.id.tv_int_Italy:
                    vp.setCurrentItem(3,true);
                    break;
                case R.id.tv_int_Spain:
                    vp.setCurrentItem(4,true);
            }
        }

    public class FragmentAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
    private void changeTextColor(int position){
        if(position == 0){
            tvEngland.setTextColor(Color.parseColor("#1ba0e1"));
            tvSpain.setTextColor(Color.parseColor("#000000"));
            tvItaly.setTextColor(Color.parseColor("#000000"));
            tvGermany.setTextColor(Color.parseColor("#000000"));
            tvFrance.setTextColor(Color.parseColor("#000000"));
        }else if (position == 1){
            tvEngland.setTextColor(Color.parseColor("#000000"));
            tvSpain.setTextColor(Color.parseColor("#000000"));
            tvItaly.setTextColor(Color.parseColor("#000000"));
            tvGermany.setTextColor(Color.parseColor("#000000"));
            tvFrance.setTextColor(Color.parseColor("#1ba0e1"));
        }else if (position == 2){
            tvEngland.setTextColor(Color.parseColor("#000000"));
            tvSpain.setTextColor(Color.parseColor("#000000"));
            tvItaly.setTextColor(Color.parseColor("#000000"));
            tvGermany.setTextColor(Color.parseColor("#1ba0e1"));
            tvFrance.setTextColor(Color.parseColor("#000000"));
        }else if (position == 3){
            tvEngland.setTextColor(Color.parseColor("#000000"));
            tvSpain.setTextColor(Color.parseColor("#000000"));
            tvItaly.setTextColor(Color.parseColor("#1ba0e1"));
            tvGermany.setTextColor(Color.parseColor("#000000"));
            tvFrance.setTextColor(Color.parseColor("#000000"));
        }else if (position == 4){
            tvEngland.setTextColor(Color.parseColor("#000000"));
            tvSpain.setTextColor(Color.parseColor("#1ba0e1"));
            tvItaly.setTextColor(Color.parseColor("#000000"));
            tvGermany.setTextColor(Color.parseColor("#000000"));
            tvFrance.setTextColor(Color.parseColor("#000000"));
        }
    }
}
