/**
 *  CardViewItem
 *
 *  Created by
 *  Thitiporn Sukpartcharoen
 *
 *  16 September 2021
 */
package com.example.myapplication;

public class CardViewItem extends BaseItem{
    private String cardViewImage;
    private String name_cardViewText;
    private String price_cardViewText;
    /**
     * Constructor instantiate card view item
     */
    public CardViewItem() {
        super(ViewType.TYPE_CARD_VIEW);
    }
    /**
     * Getter for image card view
     * @return image card view
     */
    public String getImage() {
        return cardViewImage;
    }
    /**
     * set image card view
     * @param cardViewImage image url
     * @return card view item of image
     */
    public CardViewItem setImage(String cardViewImage) {
        this.cardViewImage = cardViewImage;
        return this;
    }
    /**
     * Getter for card view of text
     * @return image card view
     */
    public String getText() {
        return name_cardViewText;
    }
    /**
     * set image card view
     * @param cardViewText card viewof text
     * @return card view item of text
     */
    public CardViewItem setText(String cardViewText) {
        this.name_cardViewText = cardViewText;
        return this;
    }
}
