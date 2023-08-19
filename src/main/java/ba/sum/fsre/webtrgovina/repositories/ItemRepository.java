package ba.sum.fsre.webtrgovina.repositories;

import ba.sum.fsre.webtrgovina.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository <Item, Long>
{};

