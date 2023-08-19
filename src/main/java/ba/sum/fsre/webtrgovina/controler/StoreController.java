package ba.sum.fsre.webtrgovina.controller;

import ba.sum.fsre.webtrgovina.model.Product;
import ba.sum.fsre.webtrgovina.repositories.ProductRepository;
import ba.sum.fsre.webtrgovina.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class StoreController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ProductService productService;

    private static String UPLOADED_FOLDER = "C:\\Users\\Veselko\\IdeaProjects\\webtrgovina\\src\\main\\java\\ba\\sum\\fsre\\webtrgovina\\uploads\\";

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/onama")
    public String onama(Model model) {
        return "onama";
    }

    @GetMapping("/add_item")
    public String add_item(Model model) {
        model.addAttribute("product", new Product());
        return "add_item";
    }

    @PostMapping("/add_new_item")
    public String new_item(Product product, @RequestParam("slika") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            product.setImagePath("/uploads/" + file.getOriginalFilename());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        productRepo.save(product);
        return "redirect:/shop";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "shop";
    }

    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product";
    }


}







