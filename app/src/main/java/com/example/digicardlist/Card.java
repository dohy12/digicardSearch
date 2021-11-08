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
    public boolean cardPr;
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
        this.cardPromo = cardInfo[16];
        this.cardImgSrc = cardInfo[17];
        this.cardPr = (cardInfo[19].toUpperCase().equals("PR"));
    }

    public String getColorStr(){
        switch (cardColor){
            case "빨강" : return "#d50000";
            case "파랑" : return "#189fed";
            case "노랑" : return "#f0ac09";
            case "초록" : return "#00bd97";
            case "보라" : return "#752ae6";
            case "검정" : return "#000000";
            default: return "#999999";
        }
    }

}
