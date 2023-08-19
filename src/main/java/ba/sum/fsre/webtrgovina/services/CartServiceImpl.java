package ba.sum.fsre.webtrgovina.services;

import ba.sum.fsre.webtrgovina.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private List<CartItem> cartItems;

    public CartServiceImpl() {
        this.cartItems = new ArrayList<>();
    }

    @Override
    public void addToCart(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    @Override
    public void removeFromCart(int index) {
        if (index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
        }
    }

    @Override
    public void updateCartItemQuantity(int index, int quantity) {
        if (index >= 0 && index < cartItems.size()) {
            cartItems.get(index).setQuantity(quantity);
        }
    }

    @Override
    public double getTotalCartPrice() {
        double totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
