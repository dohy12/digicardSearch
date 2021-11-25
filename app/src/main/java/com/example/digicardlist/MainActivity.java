package com.example.digicardlist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    static final String RETROFIT_TAG = "retrofitTest";
    ArrayList<Card> cards;

    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setSearchBar();

        searchFromApi("st1-");
    }

    public void searchFromApi(String search){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://54.180.112.140:8080/firstSpring2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService servicel = retrofit.create(RetrofitService.class);

        Call<Result> call = servicel.getResult(search);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()){
                    Result result = response.body();
                    Log.d(RETROFIT_TAG, "onResponse: 성공, 결과\n"+ result.toString());
                    List<DigiCard> digicardList = result.getDigiCard();
                    cards = new ArrayList<>();

                    for(int i=0;i<digicardList.size();i++){
                        Card card = new Card(digicardList.get(i));
                        cards.add(card);
                    }

                    page = 1;
                    showPage();
                }
                else{
                    Log.d(RETROFIT_TAG, "onResponse: 실패");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d(RETROFIT_TAG,"onFailure: "+ t.getMessage());
            }
        });
    }

    public void onClickSearch(View view){
        EditText editText = findViewById(R.id.search_bar);
        String searchStr = editText.getText().toString();
        searchFromApi(searchStr);
    }

    public ImgView_Card createShowCard(Card card, int pos){
        RelativeLayout rl = new RelativeLayout(this);

        ImageView iv = new ImageView(this);
        //iv.setImageResource(getResources().getIdentifier(card.cardImgSrc,"raw", this.getPackageName()));//이미지 설정

        TextView tv = new TextView(this);
        tv.setText(Integer.toString(pos + (page-1)*16));
        tv.setId(R.id.card_id);

        rl.addView(iv, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        rl.addView(tv, new RelativeLayout.LayoutParams(0,0));

        if (card.cardPr.toUpperCase().substring(0,1).equals("P")){ //페러렐 레어
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

        return new ImgView_Card(iv, card);

        //return new ImgView_Src(iv, card.cardImgSrc);
    }

    public void setImage(ArrayList<ImgView_Card> list){

        for(int i=0;i<list.size();i++) {
            ImgView_Card ivCard = list.get(i);
            String pr_str = (ivCard.card.cardPr.toUpperCase().substring(0,1).equals("P"))?"_"+ivCard.card.cardPr.toUpperCase():"";
            String img_str = "https://en.digimoncard.com/images/cardlist/card/" + ivCard.card.cardID + pr_str + ".png";

            Glide.with(this)
                    .load(img_str)
                    .override(100,150)
                    .thumbnail(0.3f)
                    .placeholder(R.drawable.card_back)
                    .error(R.drawable.card_back)
                    .into(ivCard.iv);
        }
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

    public void showPage(){
        clearView();

        ArrayList<ImgView_Card> imgView_srcs = new ArrayList<>();

        int pos = 0;
        for(int i = (page-1)*16;i<Math.min(page*16,cards.size());i++,pos++){
            imgView_srcs.add(createShowCard(cards.get(i), pos));
        }

        int maxPage = Math.max((int)Math.ceil((cards.size()-1)/16.0f),1);
        TextView tv = findViewById(R.id.text_page);
        tv.setText(page + " / " + maxPage);

        findViewById(R.id.text_page_button1).setEnabled(page != 1);
        findViewById(R.id.text_page_button2).setEnabled(page != maxPage);

        setImage(imgView_srcs);
    }

    public void goNextPage(View view){
        int maxPage = (int)Math.ceil(cards.size()/16.0f);
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
                    searchFromApi(searchStr);
                }
                return false;
            }
        });
    }

    private class ImgView_Card{
        public ImageView iv;
        public Card card;

        public ImgView_Card(ImageView iv, Card card){
            this.iv = iv;
            this.card = card;
        }

    }


}

