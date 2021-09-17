/**
 *  CardViewHolder
 *
 *  Created by
 *  Thitiporn Sukpartcharoen
 *
 *  16 September 2021
 */
package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CardViewHolder extends BaseViewHolder{
    private ImageView imageView;
    private TextView textView;
    /**
     * Constructor instantiate card view holder that init the item view
     */
    public CardViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        textView = (TextView) itemView.findViewById(R.id.name_textView);
    }
    /**
     * set image to image view
     * @param urlString image url
     */
    public void setImage(String urlString) {
        Picasso.get().load(urlString).into(imageView);
    }
    /**
     * set text message
     * @param text image url
     */
    public void setText(String text) {
        textView.setText(text);
    }
}
