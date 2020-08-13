package khoaphd.cart;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author KhoaPHD
 */
public class CartObject implements Serializable {
    
    private ArrayList<CartItem> items;
    
    public CartObject() {
        items = new ArrayList<>();
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }
}
