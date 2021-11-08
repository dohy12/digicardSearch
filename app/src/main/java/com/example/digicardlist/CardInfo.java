package com.example.digicardlist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

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
        ((TextView)v.findViewById(R.id.text_effect)).setText(card.cardEffect);
        ((TextView)v.findViewById(R.id.text_digiEffect)).setText(card.cardEvEffect);
        ((TextView)v.findViewById(R.id.text_security)).setText(card.cardSecurity);
        ((TextView)v.findViewById(R.id.text_promotion)).setText(card.cardPromo);

        ((Button)v.findViewById(R.id.button_close)).setOnClickListener(this);

        setColor(v);
        setCardImg(v);
    }

    public void setColor(View v){
        ((TextView)v.findViewById(R.id.text_cardKind)).setTextColor(Color.parseColor(card.getColorStr()));
        ((TextView)v.findViewById(R.id.text_cardLevel)).setBackgroundColor(Color.parseColor(card.getColorStr()));
        ((TextView)v.findViewById(R.id.text_cardName)).setTextColor(Color.parseColor(card.getColorStr()));

        for(int i=0;i<11;i++){
            int k = getResources().getIdentifier("text_color"+(i+1),"id",v.getContext().getPackageName());
            ((TextView)v.findViewById(k)).setTextColor(Color.parseColor(card.getColorStr()));
        }
    }

    public void setCardImg(View v){
        ImageView cardImgView = v.findViewById(R.id.card_Img);
        cardImgView.setImageResource(getResources().getIdentifier(card.cardImgSrc,"raw", parentContext.getPackageName()));
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
}