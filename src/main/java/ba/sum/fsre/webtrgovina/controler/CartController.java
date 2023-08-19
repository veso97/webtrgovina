package ba.sum.fsre.webtrgovina.controller;


import ba.sum.fsre.webtrgovina.model.CartItem;
import ba.sum.fsre.webtrgovina.model.Product;
import ba.sum.fsre.webtrgovina.model.ShoppingCart;
import ba.sum.fsre.webtrgovina.services.CartService;
import ba.sum.fsre.webtrgovina.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final ProductService productService;
    private final CartService cart;

    public CartController(ProductService productService, CartService cart) {
        this.productService = productService;
        this.cart = cart;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cart.getCartItems());
        model.addAttribute("totalPrice", cart.getTotalCartPrice());
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("itemId") Long itemId, @RequestParam("quantity") int quantity) {
        Product product = productService.getProductById(itemId);
        if (product != null) {
            ShoppingCart ShopingCart =  new ShoppingCart();
            CartItem cartItem = new CartItem(product, quantity);
            cart.addToCart(cartItem);
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam("itemId") int itemId,@RequestParam("quantity") int quantity) {
        cart.removeFromCart(itemId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String updateCartItem(@RequestParam("itemId") int itemId, @RequestParam("quantity") int quantity) {
        cart.updateCartItemQuantity(itemId, quantity);
        return "redirect:/cart";
    }
}
