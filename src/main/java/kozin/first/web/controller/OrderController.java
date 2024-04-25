package kozin.first.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kozin.first.domain.entity.Item;
import kozin.first.domain.entity.Member;
import kozin.first.domain.entity.Order;
import kozin.first.domain.service.ItemService;
import kozin.first.domain.service.MemberService;
import kozin.first.domain.service.OrderService;
import kozin.first.web.SessionConst;
import kozin.first.web.form.OrderForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/new")
    public String orderForm(Model model) {
        List<Member> members = memberService.findAll();
        List<Item> items = itemService.findAll();

        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "orders/orderForm";
    }

    @PostMapping("/new")
    public String order(@RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count,
                        HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        orderService.order(member.getId(), itemId, count);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String orderList(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders/orderList";
    }
}
