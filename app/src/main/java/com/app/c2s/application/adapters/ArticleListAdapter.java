package com.app.c2s.application.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.c2s.application.R;
import com.app.c2s.application.adapters.item.ArticleListItem;

import java.util.List;

/**
 * Created by Nawel on 7/26/2015.
 */
public class ArticleListAdapter extends ArrayAdapter {

    private Context context;
    private boolean useList = true;

    public ArticleListAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }

    /**
     * Holder for the list items.
     */
    private class ViewHolder{
        TextView intitule;
        TextView prix;
        TextView qte;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ArticleListItem item = (ArticleListItem)getItem(position);
        View viewToUse = null;

        // This block exists to inflate the settings list item conditionally based on whether
        // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            if(useList){
                viewToUse = mInflater.inflate(R.layout.article_list_item, null);
            } else {
                viewToUse = mInflater.inflate(R.layout.article_grid_item, null);
            }

            holder = new ViewHolder();
            holder.intitule = (TextView)viewToUse.findViewById(R.id.txt_article_intitule);
            holder.prix = (TextView)viewToUse.findViewById(R.id.txt_article_prix);
            holder.qte = (TextView)viewToUse.findViewById(R.id.txt_article_qte);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        holder.intitule.setText(item.getIntitule());
        holder.prix.setText(item.getPrix_ht()+"");
        holder.qte.setText(item.getQuantite()+"");
        return viewToUse;
    }
}