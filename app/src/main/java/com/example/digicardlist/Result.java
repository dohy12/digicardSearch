package com.example.digicardlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("DigiCard")
    @Expose
    private List<DigiCard> digiCardList = null;

    public List<DigiCard> getDigiCard() {
        return digiCardList;
    }

    public void setDigiCard(List<DigiCard> digiCardList) {
        this.digiCardList = digiCardList;
    }

    @Override
    public String toString(){
        String rs = "";
        for(int i=0;i<digiCardList.size();i++){
            DigiCard card = digiCardList.get(i);
            rs += "("+card.getCardNo()+"," + card.getName()+")";
        }
        return rs;
    }

}