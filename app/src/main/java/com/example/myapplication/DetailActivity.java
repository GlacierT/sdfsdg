package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DetailActivity extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> avtors = new ArrayList<>();
    ArrayList<String> riks = new ArrayList<>();

    private String loadJSONfromAssets() {
        String json = null;
        try{
            InputStream is = getAssets().open("user.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            JSONObject obj = new JSONObject(loadJSONfromAssets());
            JSONArray userArray = obj.getJSONArray("user");
            for(int i=0; i<userArray.length(); i++){
                JSONObject userDetail = userArray.getJSONObject(i);
                names.add(userDetail.getString("value1"));
                avtors.add(userDetail.getString("avtor1"));
                riks.add(userDetail.getString("rik"));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent in = getIntent();
        int index = in.getIntExtra("com.example.myapplication.ITEM_INDEX", -1);

        if(index > -1){
            int pic = getImg(index);
            ImageView img = (ImageView) findViewById(R.id.imageView);
            scaleImg(img, pic);
            String name = names.get(index);
            String avtor = avtors.get(index);
            String rik = riks.get(index);
            TextView textViewName = (TextView) findViewById(R.id.textViewName);
            textViewName.setText(name);
            TextView textViewAvtor = (TextView) findViewById(R.id.textViewAvtor);
            textViewAvtor.setText(avtor);
            TextView textViewRik = (TextView) findViewById(R.id.textViewRik);
            textViewRik.setText(rik);

        }
    }

    private int getImg(int index){
        switch (index){
            case 0: return R.drawable.kobzar;
            case 1: return R.drawable.nich;
            case 2: return R.drawable.zp;
            case 3: return R.drawable.kateruna;
            case 4: return R.drawable.poka;
            case 5: return R.drawable.kogo;
            case 6: return R.drawable.drive;
            case 7: return R.drawable.biy;
            default: return -1;
        }
    }

    private void scaleImg(ImageView img, int pic){
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidht = options.outWidth;
        int screenWidht = screen.getWidth();

        if (imgWidht > screenWidht){
            int ratio = Math.round( (float)imgWidht / (float) screenWidht);
            options.inSampleSize = ratio;
        }

        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);
    }

}