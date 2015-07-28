package com.app.c2s.application.db.models;

/**
 * Created by Nawel on 7/24/2015.
 */
public class Article {

        public int id;
        public String intitule;
        public String codeArticle;
        public String photo;
        public int quantite;
        public float tva ;
        public float prixHt ;
        public float prixTtc;



        public Article(){
        }



        public Article(String intitule, String codeArticle, float prixHt, int quantite){
            this.intitule =  intitule;
            this.codeArticle = codeArticle;
            this.photo ="";
            this.tva =  0;
            this.prixHt = prixHt;
            this.prixTtc= 0;
            this.quantite = quantite;
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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getQuantite() {
            return quantite;
        }

        public void setQuantite(int quantite) {
            this.quantite = quantite;
        }

        public float getTva() {
            return tva;
        }

        public void setTva(float tva) {
            this.tva = tva;
        }

        public float getPrixHt() {
            return prixHt;
        }

        public void setPrixHt(float prixHt) {
            this.prixHt = prixHt;
        }

        public float getPrixTtc() {
            return prixTtc;
        }

        public void setPrixTtc(float prixTtc) {
            this.prixTtc = prixTtc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
}


