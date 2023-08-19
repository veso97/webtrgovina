package ba.sum.fsre.webtrgovina.controler;


import ba.sum.fsre.webtrgovina.model.Item;
import ba.sum.fsre.webtrgovina.model.UserDetails;
import ba.sum.fsre.webtrgovina.repositories.ItemRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class itemsController {

    @Autowired
    ItemRepository ItemRepo;
    private Object item;

    @GetMapping("item")
    public String listItems (Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("userDetails", userDetails);
        List<Item> listBooks = ItemRepo.findAll();
        model.addAttribute("listBooks", listBooks);
        model.addAttribute("activeLink", "Knjige");
        return "Items";
    }

    @GetMapping("Items/add")
    public String showAddBookForm (Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("activeLink", "Proizvodi");
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("item", new Item());
        return "add_item";
    }

    @PostMapping("books/add")
    public String addBook (@Valid Item item, BindingResult result, Model model) {
        boolean errors = result.hasErrors();
        if (errors) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("activeLink", "Knjige");
            model.addAttribute("userDetails", userDetails);
            model.addAttribute("book",item);
            return "add_book";
        }
        ItemRepo.save(item);
        return "redirect:/books";
    }

    @GetMapping("books/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Item item = ItemRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("activeLink", "Knjige");
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("book", item);
        return "item";
    }

    @PostMapping("books/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Item item,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            item.setId(id);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("activeLink", "Knjige");
            model.addAttribute("userDetails", userDetails);
            model.addAttribute("Item", item);
            return "edit_Item";
        }

        ItemRepo.save(item);
        return "redirect:/items";
    }


}