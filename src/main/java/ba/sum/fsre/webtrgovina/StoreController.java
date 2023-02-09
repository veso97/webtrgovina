package ba.sum.fsre.webtrgovina;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
