package com.example.digicardlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.print("hello world");
        System.out.print("test");
        System.out.print("push");

        createShowCard();

        showFragement();
    }

    public void createShowCard(){
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.omega);

        int width = 0;
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();

        param.width = width;
        param.height = height;
        param.columnSpec = GridLayout.spec(2, 1,1);
        param.rowSpec = GridLayout.spec(0, 1,1);
        param.setMargins(margin,margin, margin, margin);
        GridLayout gridLayout = findViewById(R.id.search_grid);
        gridLayout.addView(iv,param);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragement();
            }
        });
    }

    public void showFragement(){
        Card card = new Card("ST1-03","디지몬카드","3","아구몬","성장기","백신종","파충류형","2000","Lv.2에서 0","-","-","[자신의 턴] 이 디지몬의 DP를 +1000한다.","-","스타터덱1","img_test");
        CardInfo e = CardInfo.getInstance();
        e.setCard(card, this);
        e.show(getSupportFragmentManager(), CardInfo.TAG_EVENT_DIALOG);
    }

}