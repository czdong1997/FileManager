package com.example.cslab.filemanager;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by z.z on 2017/7/14.
 */

public class Globals{
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static Map<String, Integer> imgsMap = new HashMap<>();

    public static void init(Activity a){
        SCREEN_HEIGHT = a.getWindowManager().getDefaultDisplay().getWidth();
        SCREEN_HEIGHT = a.getWindowManager().getDefaultDisplay().getHeight();

        imgsMap.put("open_dir", R.mipmap.open_dir);
        imgsMap.put("close_dir",R.mipmap.close_dir);
        imgsMap.put("txt", R.mipmap.txt_file);
        imgsMap.put("mp4", R.mipmap.mp4_file);
        imgsMap.put("api", R.mipmap.mp4_file);
        imgsMap.put("mp3", R.mipmap.mp3_file);
        imgsMap.put("jpg", R.mipmap.image_file);
        imgsMap.put("npg", R.mipmap.image_file);
        imgsMap.put("bmp", R.mipmap.image_file);
    }

}
