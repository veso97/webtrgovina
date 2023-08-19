package ba.sum.fsre.webtrgovina.repositories;

import ba.sum.fsre.webtrgovina.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product>findAll();

    // Dodatne metode za prilagođene upite ili operacije nad podacima proizvoda
}
