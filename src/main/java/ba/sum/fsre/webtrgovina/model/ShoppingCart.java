package ba.sum.fsre.webtrgovina.model;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {


    private List<CartItem> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
        }
    }

    public void updateQuantity(int index, int quantity) {
        if (index >= 0 && index < cartItems.size()) {
            cartItems.get(index).setQuantity(quantity);
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }
}
