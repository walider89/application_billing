package com.app.c2s.application.adapters.item;

import com.app.c2s.application.db.models.Article;
import com.app.c2s.application.db.models.Client;

/**
 * This class used data in a Client List Item.
 */
public class ClientListItem {

    private String intitule;
    private float solde;
    private float solde_initial;
    private float reglement;
    private float ca_ttc;



    public ClientListItem(Client client){
        this.intitule = client.getIntitule();
        this.solde = client.getSolde();
        this.ca_ttc = client.getCa_ttc();
        this.reglement = client.getReglement();
        this.solde_initial = client.getSolde_initial();
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

    public float getReglement() {
        return reglement;
    }

    public void setReglement(float reglement) {
        this.reglement = reglement;
    }

    public float getCa_ttc() {
        return ca_ttc;
    }

    public void setCa_ttc(float ca_ttc) {
        this.ca_ttc = ca_ttc;
    }
}