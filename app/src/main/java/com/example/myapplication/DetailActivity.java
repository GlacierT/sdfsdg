package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent in = getIntent();
        int index = in.getIntExtra("com.example.myapplication.ITEM_INDEX", -1);

        if(index > -1){
            int pic = getImg(index);
            ImageView img = (ImageView) findViewById(R.id.imageView);
            scaleImg(img, pic);
            String name = getName(index);
            String avtor = getAvtor(index);
            String rik = getRik(index);
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

    private String getName(int index){
        switch (index){
            case 0: return "«Кобзар»";
            case 1: return "«Кінь. Ніч»";
            case 2: return "«Запорожці»";
            case 3: return "«Катерина»";
            case 4: return "«Прощавай, Караваджо!»";
            case 5: return "«Кого боїться Хьорст»";
            case 6: return "«DRIVE 9»";
            case 7: return "«Морський бій»";
            default: return "non";
        }
    }

    private String getAvtor(int index){
        switch (index){
            case 0: return "Автор: Олег Шупляк";
            case 1: return "Автор: Анатолій Криволап";
            case 2: return "Автор: Ілля Рєпін";
            case 3: return "Автор: Тарас Шевченко";
            case 4: return "Автор: Олександр Ройтбурд";
            case 5: return "Автор: Василь Цаголов";
            case 6: return "Автор: Оксана Мась";
            case 7: return "Автор: Максим Мамсіков";
            default: return "non";
        }
    }

    private String getRik(int index){
        switch (index){
            case 0: return "Дата створення: 1854 рік";
            case 1: return "Дата створення: 2009 рік";
            case 2: return "Дата створення: 1880-1891 рік";
            case 3: return "Дата створення: 1842 рік";
            case 4: return "Дата створення: 2008 рік";
            case 5: return "Дата створення: 2013 рік";
            case 6: return "Дата створення: 2008-2009 рік";
            case 7: return "Дата створення: 1999 рік";
            default: return "non";
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