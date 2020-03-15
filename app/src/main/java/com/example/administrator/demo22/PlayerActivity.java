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
import com.example.administrator.demo22.Fragment.Player1Fragment;
import com.example.administrator.demo22.Fragment.Player2Fragment;
import com.example.administrator.demo22.Fragment.Player3Fragment;
import com.example.administrator.demo22.Fragment.Player4Fragment;
import com.example.administrator.demo22.Fragment.Player5Fragment;
import com.example.administrator.demo22.Fragment.SpainFragment;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager vp;
    private ImageView ivBack;
    private TextView tvEngland,tvFrance,tvGermany,tvSpain,tvItaly;
    private Player1Fragment player1Fragment;
    private Player2Fragment player2Fragment;
    private Player3Fragment player3Fragment;
    private Player4Fragment player4Fragment;
    private Player5Fragment player5Fragment;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
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
        tvEngland=findViewById(R.id.tv_player_England);
        tvEngland.setOnClickListener(this);
        tvFrance=findViewById(R.id.tv_player_france);
        tvFrance.setOnClickListener(this);
        tvGermany=findViewById(R.id.tv_player_Germany);
        tvGermany.setOnClickListener(this);
        tvItaly=findViewById(R.id.tv_player_Italy);
        tvItaly.setOnClickListener(this);
        tvSpain=findViewById(R.id.tv_player_Spain);
        tvSpain.setOnClickListener(this);
        vp=findViewById(R.id.vp_player);
        player1Fragment = new Player1Fragment();
        player2Fragment = new Player2Fragment();
        player3Fragment = new Player3Fragment();
        player4Fragment = new Player4Fragment();
        player5Fragment = new Player5Fragment();
        fragmentList.add(player1Fragment);
        fragmentList.add(player2Fragment);
        fragmentList.add(player3Fragment);
        fragmentList.add(player4Fragment);
        fragmentList.add(player5Fragment);
        ivBack=findViewById(R.id.iv_player_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PlayerActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_player_England:
                vp.setCurrentItem(0,true);
                break;
            case R.id.tv_player_france:
                vp.setCurrentItem(1,true);
                break;
            case R.id.tv_player_Germany:
                vp.setCurrentItem(2,true);
                break;
            case R.id.tv_player_Italy:
                vp.setCurrentItem(3,true);
                break;
            case R.id.tv_player_Spain:
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