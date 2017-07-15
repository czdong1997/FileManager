package com.example.cslab.filemanager;

import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import static android.R.attr.paddingStart;
import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity {

    List<Map<String,Object>> list = new ArrayList<>();
    TextView textView ;

    private FileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Globals.init(this);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        final TextView textView = (TextView)findViewById(R.id.path);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        File file = Environment.getExternalStorageDirectory();
        loadFile(file);
        final FileAdapter fileAdapter = new FileAdapter(list);
        recyclerView.setAdapter(fileAdapter);
        fileAdapter.setOnItemClickListener(new FileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("this", "onItemClick: "+position);
                Toast.makeText(MainActivity.this,"Hello World"+position,Toast.LENGTH_SHORT).show();

                Map<String,Object> map = list.get(position);
                String fullPath = map.get("fullPath").toString();
                File file = new File(fullPath);
                if ((boolean)map.get("isDir")){
                    list.clear();
                    if (!fullPath.equals(Environment.getExternalStorageDirectory().getAbsolutePath())){
                        Map<String,Object> map1 = new HashMap<String, Object>();
                        map1.put("extName","open_dir");
                        map1.put("fullPath",file.getParentFile().getAbsolutePath());
                        map1.put("name","返回上一级");
                        map1.put("isDir",true);
                        list.add(map1);
                    }
                    loadFile(file);
                    fileAdapter.notifyDataSetChanged();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("提示信息");
                    builder.setMessage("文件大小为：" +  file.length());
                    builder.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.create().show();
                }
            }
        });
        textView.setText(file.getAbsolutePath());
    }



    public void loadFile(File file){
        File[] files = file.listFiles();
        for (int i=0;i<files.length;i++) {
            File f = files[i];
            Map<String, Object> map = new HashMap<>();
            String fileName = f.getName();
            map.put("name", fileName);
            map.put("fullPath", f.getAbsolutePath());
            if (f.isDirectory()) {
                map.put("extName", "close_dir");
                map.put("isDir", true);
            } else {
                map.put("extName", fileName.substring(fileName.lastIndexOf(".") + 1));
                map.put("isDir", false);
            }
            list.add(map);
        }
    }
}
