package com.canteen.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.canteen.model.Item;
import com.canteen.repository.ItemRepository;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemRepository repo;
    public ItemController(ItemRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Item> list() { return repo.findAll(); }

    @PostMapping
    public Item create(@RequestBody Item item) {
        item.setId(null);
        return repo.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item in) {
        return repo.findById(id).map(it -> {
            it.setName(in.getName());
            it.setDescription(in.getDescription());
            it.setPrice(in.getPrice());
            it.setEmoji(in.getEmoji());
            return ResponseEntity.ok(repo.save(it));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
