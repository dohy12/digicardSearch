package com.example.digicardlist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import org.w3c.dom.Text;

public class CardInfo extends DialogFragment implements View.OnClickListener{
    public static final String TAG_EVENT_DIALOG = "dialog_event";
    public Card card;

    public Context parentContext;

    public CardInfo(){

    }

    public static CardInfo getInstance(){
        CardInfo ins = new CardInfo();
        return ins;
    }

    public void setCard(Card card, Context context){
        this.parentContext = context;
        this.card = card;
    }

    public void setCardInfo(View v){
        ((TextView)v.findViewById(R.id.text_cardID)).setText(card.cardID);
        ((TextView)v.findViewById(R.id.text_cardKind)).setText(card.cardKind);
        ((TextView)v.findViewById(R.id.text_cardLevel)).setText("Lv."+card.cardLevel);
        ((TextView)v.findViewById(R.id.text_cardName)).setText(card.cardName);
        ((TextView)v.findViewById(R.id.text_cardForm)).setText(card.cardForm);
        ((TextView)v.findViewById(R.id.text_cardAttribute)).setText(card.cardAttribute);
        ((TextView)v.findViewById(R.id.text_cardType)).setText(card.cardType);
        ((TextView)v.findViewById(R.id.text_cardDP)).setText(card.cardDP);
        ((TextView)v.findViewById(R.id.text_cardEvCost1)).setText(card.cardEvCost1);
        ((TextView)v.findViewById(R.id.text_cardEvCost2)).setText(card.cardEvCost2);
        ((TextView)v.findViewById(R.id.text_effect)).setText(card.cardEffect.replace("<LB>","\n"));
        ((TextView)v.findViewById(R.id.text_digiEffect)).setText(card.cardEvEffect.replace("<LB>","\n"));
        ((TextView)v.findViewById(R.id.text_security)).setText(card.cardSecurity.replace("<LB>","\n"));

        ((Button)v.findViewById(R.id.button_close)).setOnClickListener(this);

        setColor(v);
        setCardImg(v);
    }

    public void setColor(View v){
        ((TextView)v.findViewById(R.id.text_cardKind)).setTextColor(Color.parseColor(card.getColorStr()));
        ((TextView)v.findViewById(R.id.text_cardLevel)).setBackgroundColor(Color.parseColor(card.getColorStr()));
        ((TextView)v.findViewById(R.id.text_cardName)).setTextColor(Color.parseColor(card.getColorStr()));

        for(int i=0;i<10;i++){
            int k = getResources().getIdentifier("text_color"+(i+1),"id",v.getContext().getPackageName());
            ((TextView)v.findViewById(k)).setTextColor(Color.parseColor(card.getColorStr()));
        }
    }

    public void setCardImg(View v){
        ImageView cardImgView = v.findViewById(R.id.card_Img);
        //cardImgView.setImageResource(getResources().getIdentifier(card.cardImgSrc,"raw", parentContext.getPackageName()));

        String pr_str = (card.cardPr.toUpperCase().substring(0,1).equals("P"))?"_"+card.cardPr.toUpperCase():"";
        String img_str = "https://en.digimoncard.com/images/cardlist/card/" + card.cardID + pr_str + ".png";

        Glide.with(this)
                .load(img_str)
                .into(cardImgView);

        int layout_height = DpiTranslator.dpToPx(parentContext, 250);
        int layout_margin = DpiTranslator.dpToPx(parentContext, 8);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, layout_height);
        params.setMargins(0,layout_margin,layout_margin*2,layout_margin);
        cardImgView.setAdjustViewBounds(true);
        cardImgView.setLayoutParams(params);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_card_info, container);
        setCardInfo(v);
        return v;
    }

    @Override
    public void onClick(View v){
        dismiss();
    }

    public void onResume() {
        WindowManager windowManager = (WindowManager) parentContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = (int)(size.x * 1);
        int height = DpiTranslator.dpToPx(parentContext, 650);
        getDialog().getWindow().setLayout(width,height);
        super.onResume();
    }
}