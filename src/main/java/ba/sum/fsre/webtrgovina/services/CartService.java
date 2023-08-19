package ba.sum.fsre.webtrgovina.services;

import ba.sum.fsre.webtrgovina.model.CartItem;

import java.util.List;

public interface CartService {
    void addToCart(CartItem cartItem);
    void removeFromCart(int index);
    void updateCartItemQuantity(int index, int quantity);
    double getTotalCartPrice();
    List<CartItem> getCartItems();




}
