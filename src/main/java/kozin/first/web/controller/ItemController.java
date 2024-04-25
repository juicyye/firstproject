package kozin.first.web.controller;

import kozin.first.domain.entity.Item;
import kozin.first.domain.service.ItemService;
import kozin.first.web.form.ItemForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/new")
    public String createForm(@ModelAttribute("item") ItemForm form) {
        return "items/itemForm";
    }

    @PostMapping("/new")
    public String create(@Validated @ModelAttribute("item") ItemForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("error:" + bindingResult.getAllErrors());
            return "items/itemForm";
        }
        Item item = new Item(form.getTitle(), form.getAuthor(), form.getPrice(), form.getStockQuantity());
        itemService.save(item);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String listForm(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items/itemList";
    }
}
