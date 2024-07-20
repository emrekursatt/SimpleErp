package com.tr.simpleerp.service;

import com.tr.simpleerp.model.OrderModel;
import com.tr.simpleerp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public float getAllOrdersDataTotalAmount() {
        List<OrderModel> allData = orderRepository.findAll();
        double sum = allData.stream().mapToDouble(item -> item.getAmount() * item.getUnitPrice()).sum();
        return sum > 0 ? (float) sum : 0;
    }

    public float getAllOrdersDataAveragePrice() {
        List<OrderModel> allData = orderRepository.findAll();
        double sum = getAllOrdersDataTotalAmount();
        double sum1 = allData.stream().mapToDouble(item -> item.getAmount()).sum();
        return sum > 0 ? (float) ((float) sum / sum1) : 0;
    }

    public List<OrderModel> getAveragePriceByItemNumber() {
        List<OrderModel> allData = orderRepository.findAll();
        Map<String, Double> totalPriceByItemNumber = new HashMap<>();
        Map<String, Integer> totalAmountByItemNumber = new HashMap<>();

        for (OrderModel item : allData) {
            String itemNumber = item.getItemNumber();
            int amount = item.getAmount();
            double unitPrice = item.getUnitPrice();
            double totalPrice = amount * unitPrice;

            totalPriceByItemNumber.merge(itemNumber, totalPrice, Double::sum);
            totalAmountByItemNumber.merge(itemNumber, amount, Integer::sum);
        }

        List<OrderModel> result = new ArrayList<>();
        for (String itemNumber : totalPriceByItemNumber.keySet()) {
            double totalPrice = totalPriceByItemNumber.get(itemNumber);
            int totalAmount = totalAmountByItemNumber.get(itemNumber);
            double averagePrice = totalPrice / totalAmount;

            OrderModel orderModel = new OrderModel();
            orderModel.setItemNumber(itemNumber);
            orderModel.setAmount(totalAmount);
            orderModel.setUnitPrice((float) averagePrice);
            orderModel.setAvarageAmount((float) averagePrice);

            result.add(orderModel);
        }

        return result;

    }

    public Map<String, Map<String, Integer>> getOrderDetailsByItemNumber() {
        List<OrderModel> allData = orderRepository.findAll();
        Map<String, Map<String, Integer>> result = new HashMap<>();

        for (OrderModel item : allData) {
            String itemNumber = item.getItemNumber();
            String orderNumber = item.getOrder();
            int amount = item.getAmount();

            result.putIfAbsent(itemNumber, new HashMap<>());

            Map<String, Integer> ordersMap = result.get(itemNumber);
            ordersMap.merge(orderNumber, amount, Integer::sum);
        }

        return result;
    }
    //Save
    public OrderModel addOrder(OrderModel orderModel) {
        return orderRepository.save(orderModel);
    }
}


//a. Üç siparişteki malların toplam tutarının çıktısını veren java kodu.
//b. Üç siparişteki bütün malların ortalama fiyatını bulan java kodu.
//c. Üç siparişteki bütün malların tek tek mal bazlı ortalama fiyatını bulan java kodu.
//d. Tek tek mal bazlı, malların hangi siparişlerde kaç adet olduğunun çıktısını veren
