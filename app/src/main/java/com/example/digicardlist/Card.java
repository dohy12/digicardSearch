package com.example.digicardlist;

public class Card {
    public String cardID;
    public String cardKind;
    public String cardLevel;
    public String cardName;
    public String cardForm;
    public String cardAttribute;
    public String cardType;
    public String cardDP;
    public String cardCost;
    public String cardEvCost1;
    public String cardEvCost2;
    public String cardEffect;
    public String cardEvEffect;
    public String cardSecurity;
    public String cardPromo;
    public String cardImgSrc;
    public String cardPr;
    public String cardColor;

    public Card(String[] cardInfo){
        this.cardID =  cardInfo[1];
        this.cardName = cardInfo[2];
        this.cardKind = cardInfo[3];
        this.cardColor = cardInfo[4];
        this.cardLevel = cardInfo[5];
        this.cardForm = cardInfo[6];
        this.cardAttribute = cardInfo[7];
        this.cardType = cardInfo[8];
        this.cardDP = cardInfo[9];
        this.cardCost = cardInfo[10];
        this.cardEvCost1 = cardInfo[11];
        this.cardEvCost2 = cardInfo[12];
        this.cardEffect = cardInfo[13];
        this.cardEvEffect = cardInfo[14];
        this.cardSecurity = cardInfo[15];
        this.cardPr = cardInfo[17];
    }

    public String getColorStr(){
        switch (cardColor){
            case "빨강" :
            case "적색" :
                return "#d50000";
            case "파랑" :
            case "청색" :
                return "#189fed";
            case "노랑" :
            case "황색" :
                return "#f0ac09";
            case "초록" :
            case "녹색" :
                return "#00bd97";
            case "보라" :
            case "자색" :
                return "#752ae6";
            case "검정" :
            case "흑색" :
                return "#000000";
            default: return "#999999";
        }
    }

}
