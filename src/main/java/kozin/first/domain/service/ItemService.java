package kozin.first.domain.service;

import kozin.first.domain.entity.Item;
import kozin.first.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long save(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
