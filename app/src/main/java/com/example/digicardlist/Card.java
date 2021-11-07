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
    public String cardEvCost1;
    public String cardEvCost2;
    public String cardEffect;
    public String cardEvEffect;
    public String cardSecurity;
    public String cardPromo;
    public String cardImgSrc;

    public Card(String cardID, String cardKind, String cardLevel, String cardName, String cardForm, String cardAttribute, String cardType, String cardDP,
                String cardEvCost1, String cardEvCost2, String cardEffect, String cardEvEffect, String cardSecurity, String cardPromo, String cardImgSrc){
        this.cardID = cardID;
        this.cardKind = cardKind;
        this.cardLevel = cardLevel;
        this.cardName = cardName;
        this.cardForm = cardForm;
        this.cardAttribute = cardAttribute;
        this.cardType = cardType;
        this.cardDP = cardDP;
        this.cardEvCost1 = cardEvCost1;
        this.cardEvCost2 = cardEvCost2;
        this.cardEffect = cardEffect;
        this.cardEvEffect = cardEvEffect;
        this.cardSecurity = cardSecurity;
        this.cardPromo = cardPromo;
        this.cardImgSrc = cardImgSrc;
    }


}
