package ba.sum.fsre.webtrgovina.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:C:\\Users\\Veselko\\IdeaProjects\\webtrgovina\\src\\main\\java\\ba\\sum\\fsre\\webtrgovina\\uploads\\");
    }
}