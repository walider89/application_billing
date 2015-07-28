package com.app.c2s.application.adapters.item;

import com.app.c2s.application.db.models.Article;

/**
 * Created by nawel on 7/26/2015.
 */
public class ArticleListItem {

    private String intitule;
    private String codeArticle;
    private float prix_ht;
    private float prix_ttc;
    private int quantite;



    public ArticleListItem(Article article){
        this.intitule = article.getIntitule();
        this.codeArticle = article.getCodeArticle();
        this.prix_ht = article.getPrixHt();
        this.prix_ttc = article.getPrixTtc();
        this.quantite = article.getQuantite();
    }


    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public float getPrix_ht() {
        return prix_ht;
    }

    public void setPrix_ht(float prix_ht) {
        this.prix_ht = prix_ht;
    }

    public float getPrix_ttc() {
        return prix_ttc;
    }

    public void setPrix_ttc(float prix_ttc) {
        this.prix_ttc = prix_ttc;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}