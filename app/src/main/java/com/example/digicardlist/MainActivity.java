package com.example.digicardlist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Card> cards;

    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSearchBar();

        try {
            search("st1-");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickSearch(View view){
        EditText editText = findViewById(R.id.search_bar);
        String searchStr = editText.getText().toString();
        try {
            search(searchStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createShowCard(Card card, int pos){
        RelativeLayout rl = new RelativeLayout(this);

        ImageView iv = new ImageView(this);
        iv.setImageResource(getResources().getIdentifier(card.cardImgSrc,"raw", this.getPackageName()));//이미지 설정

        TextView tv = new TextView(this);
        tv.setText(Integer.toString(pos + (page-1)*24));
        tv.setId(R.id.card_id);

        rl.addView(iv, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        rl.addView(tv, new RelativeLayout.LayoutParams(0,0));

        if (card.cardPr){ //페러렐 레어
            TextView tvPR = new TextView(this);
            tvPR.setText("PR");
            tvPR.setBackgroundColor(Color.parseColor(card.getColorStr()));
            tvPR.setTextColor(Color.parseColor("#ffffff"));
            tvPR.setPadding(5,0,5,0);
            rl.addView(tvPR, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
        }

        int width = 0;
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();

        int colPos = pos%4;
        int rowPos = pos/4;

        param.width = width;
        param.height = height;
        param.columnSpec = GridLayout.spec(colPos, 1,1);
        param.rowSpec = GridLayout.spec(rowPos, 1,1);
        param.setMargins(margin,margin, margin, margin);

        GridLayout gridLayout = findViewById(R.id.search_grid);
        gridLayout.addView(rl,param);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int card_id = Integer.parseInt(((TextView)view.findViewById(R.id.card_id)).getText().toString());
                showFragement(card_id);
            }
        });
        return;
    }


    public void clearView(){
        GridLayout gridLayout = findViewById(R.id.search_grid);
        gridLayout.removeAllViews();

        int width = 0;
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());

        for(int i =0;i<4;i++){
            ImageView v = new ImageView(this);

            int colPos = i;
            int rowPos = 0;
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            param.width = width;
            param.height = height;
            param.columnSpec = GridLayout.spec(colPos, 1,1);
            param.rowSpec = GridLayout.spec(rowPos, 1,1);

            gridLayout.addView(v,param);
        }
    }

    public void search(String searchStr) throws IOException {
        cards = new ArrayList<>();
        InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.digimon_card_info_utf));
        BufferedReader reader = new BufferedReader(is);
        String line;
        reader.readLine();
        while((line = reader.readLine())!=null){
            String[] cardInfo = line.split("\\|");
            boolean search_ch = false;
            if(cardInfo[1].toUpperCase().contains(searchStr.toUpperCase())) search_ch = true;
            if(cardInfo[2].contains(searchStr)) search_ch = true;
            if(cardInfo[18].toUpperCase().contains(searchStr.toUpperCase())) search_ch = true;

            if(search_ch){
                Card card = new Card(cardInfo);
                cards.add(card);
            }
        }

        page = 1;
        showPage();
    }

    public void showPage(){
        clearView();

        int pos = 0;
        for(int i = (page-1)*24;i<Math.min(page*24,cards.size());i++,pos++){
            createShowCard(cards.get(i), pos);
        }

        int maxPage = Math.max((int)Math.ceil((cards.size()-1)/24.0f),1);
        TextView tv = findViewById(R.id.text_page);
        tv.setText(page + " / " + maxPage);

        findViewById(R.id.text_page_button1).setEnabled(page != 1);
        findViewById(R.id.text_page_button2).setEnabled(page != maxPage);
    }

    public void goNextPage(View view){
        int maxPage = (int)Math.ceil(cards.size()/24.0f);
        page = Math.min(page+1,maxPage);
        showPage();
    }

    public void goPrevPage(View view){
        page = Math.max(1,page-1);
        showPage();
    }

    public void showFragement(int card_id){
        Card card = cards.get(card_id);

        CardInfo e = CardInfo.getInstance();
        e.setCard(card, this);
        e.show(getSupportFragmentManager(), CardInfo.TAG_EVENT_DIALOG);
    }

    public void setSearchBar(){
        EditText e = findViewById(R.id.search_bar);
        e.setOnKeyListener(new EditText.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER){
                    EditText editText = findViewById(R.id.search_bar);
                    String searchStr = editText.getText().toString();
                    try {
                        search(searchStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }
}
