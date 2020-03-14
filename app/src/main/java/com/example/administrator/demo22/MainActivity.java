package com.example.administrator.demo22;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo22.Utils.HttpUtil;
import com.example.administrator.demo22.Utils.Title;
import com.example.administrator.demo22.Utils.TitleAdapter;
import com.example.administrator.demo22.Utils.Utility;
import com.example.administrator.demo22.Utils.Utils;
import com.example.administrator.demo22.Utils.newList;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int  ITEM_SOCIETY= 1;
    private TextView tvJifen,tvTongji,tvDays,tvVideo;
    private CircleImageView circle;
    private DrawerLayout drawerLayout;
    private List<Title> titleList = new ArrayList<Title>();
    private ListView listView;
    private TitleAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private Button btn_local;
    private static final File USER_ICON = new File(Environment.getExternalStorageDirectory(), "user_icon.jpg");
    private static final int CODE_PHOTO_REQUEST = 1;
    private static final int CODE_CAMERA_REQUEST = 2;
    private static final int CODE_PHOTO_CLIP = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        initView();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.uefa);
        }
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("欧洲足坛");

        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        listView = (ListView)findViewById(R.id.listview);
        adapter = new TitleAdapter(this,R.layout.list_view_item, titleList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Title title = titleList.get(position);
                intent.putExtra("title",actionBar.getTitle());
                intent.putExtra("uri",title.getUrl());
                startActivity(intent);
            }
        });



        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                int itemName = parseString((String)actionBar.getTitle());
                requestNew(itemName);
            }
        });

        requestNew(ITEM_SOCIETY);

    }

    private void initView() {
        drawerLayout=findViewById(R.id.dl_left);
        btn_local=findViewById(R.id.btn_local);
        btn_local.setOnClickListener(this);
        tvJifen=findViewById(R.id.tv_jifen);
        tvJifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,IntegralActivity.class);
                startActivity(intent);
            }
        });
        tvTongji=findViewById(R.id.tv_tongji);
        tvTongji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,PlayerActivity.class);
                startActivity(intent);
            }
        });
        tvDays=findViewById(R.id.tv_days);
        tvDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DaysActivity.class);
                startActivity(intent);
            }
        });
        tvVideo=findViewById(R.id.tv_video);
        tvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,VideoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showDrawerLayout() {
        if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.openDrawer(Gravity.LEFT);
        } else {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }
    /**
     *  判断是否是当前页面,如果不是则 请求处理数据
     */
    private void handleCurrentPage(String text, int item){
        ActionBar actionBar = getSupportActionBar();
        if (!text.equals(actionBar.getTitle().toString())){
            actionBar.setTitle(text);
            requestNew(item);
            refreshLayout.setRefreshing(true);
        }
    }


    /**
     * 请求处理数据
     */
    public void requestNew(int itemName){

        // 根据返回到的 URL 链接进行申请和返回数据
        String address = response(itemName);    // key
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "新闻加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final newList newlist = Utility.parseJsonWithGson(responseText);
                final int code = newlist.code;
                final String msg = newlist.msg;
                if (code == 200){
                    titleList.clear();
                    for (Utils utils:newlist.newsList){
                        Title title = new Title(utils.title,utils.description,utils.source,utils.picUrl, utils.url);
                        titleList.add(title);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            listView.setSelection(0);
                            refreshLayout.setRefreshing(false);
                        };
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "数据错误返回",Toast.LENGTH_SHORT).show();
                            refreshLayout.setRefreshing(false);
                        }
                    });
                }



            }
        });


    }

    /**
     * 输入不同的类型选项，返回对应的 URL 链接
     */
    private String response(int itemName){
        String address = "http://api.tianapi.com/usermake/index?key=5aac28838fb01e1afd6a60b973be1d7c&num=10&urlid=242";
        switch(itemName) {
            case ITEM_SOCIETY:
                break;
        }
        return address;
    }

    /**
     * 通过 actionbar.getTitle() 的参数，返回对应的 ItemName
     */
    private int parseString(String text){
        if (text.equals("欧洲足坛")){
            return ITEM_SOCIETY;
        }

        return ITEM_SOCIETY;
    }

    /**
     * 对返回键进行处理，如果侧边栏打开则关闭侧边栏，否则关闭 activity
     */
    @Override
    public void onBackPressed() {
            finish();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_LONG).show();
            return;
        }
        switch (requestCode) {
            case CODE_CAMERA_REQUEST:
                if (USER_ICON.exists()) {
                    photoClip(Uri.fromFile(USER_ICON));
                }
                break;
            case CODE_PHOTO_REQUEST:
                if (data != null) {
                    photoClip(data.getData());
                }
                break;
            case CODE_PHOTO_CLIP:
                if (data != null) {
                    setImageToHeadView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_local:
                getPicFromLocal();
                break;
            default:
                break;
        }
    }
    private void getPicFromLocal() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, CODE_PHOTO_REQUEST);
    }

    private void photoClip(Uri uri) {
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CODE_PHOTO_CLIP);
    }

    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            circle.setImageBitmap(photo);
        }
    }

}