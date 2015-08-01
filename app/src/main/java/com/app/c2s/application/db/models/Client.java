package com.app.c2s.application.db.models;

/**
 * This class is the model of Client which is table in DB
 */
public class Client {

        public int id;
        public String intitule;
        public float solde;
        public float solde_initial;
        public float ca_ttc;
        public float reglement ;


      public Client(){
        }



    public Client(String   intitule, float solde,float solde_initial ,float reglement, float ca_ttc){
        this.intitule =  intitule;
        this.solde = solde;
        this.solde_initial =solde_initial;
        this.reglement =  reglement;
        this.ca_ttc = ca_ttc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public float getSolde_initial() {
        return solde_initial;
    }

    public void setSolde_initial(float solde_initial) {
        this.solde_initial = solde_initial;
    }

    public float getCa_ttc() {
        return ca_ttc;
    }

    public void setCa_ttc(float ca_ttc) {
        this.ca_ttc = ca_ttc;
    }

    public float getReglement() {
        return reglement;
    }

    public void setReglement(float reglement) {
        this.reglement = reglement;
    }
}


