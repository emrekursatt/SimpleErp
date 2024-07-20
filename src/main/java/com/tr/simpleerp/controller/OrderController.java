package com.tr.simpleerp.controller;

import com.tr.simpleerp.model.OrderModel;
import com.tr.simpleerp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("/order/simple/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/totalAmount")
    public float getAllOrdersDataTotalAmount() {
        return orderService.getAllOrdersDataTotalAmount();
    }

    @GetMapping("/averagePrice")
    public float getAllOrdersDataAveragePrice() {
        return orderService.getAllOrdersDataAveragePrice();
    }

    @GetMapping("/averagePriceByItemNumber")
    public List<OrderModel> getAveragePriceByItemNumber() {
        return orderService.getAveragePriceByItemNumber();
    }

    @GetMapping("/orderDetailsByItemNumber")
    public Map<String, Map<String, Integer>> getOrderDetailsByItemNumber() {
        return orderService.getOrderDetailsByItemNumber();
    }

    @PostMapping("/addOrder")
    public OrderModel addOrder(OrderModel orderModel) {
        return orderService.addOrder(orderModel);
    }

}
