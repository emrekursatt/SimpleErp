package com.tr.simpleerp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = OrderModel.TABLE , uniqueConstraints = {
        @UniqueConstraint(columnNames = {OrderModel.ORDER , OrderModel.ITEM_NUMBER})})
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    public static  final String TABLE = "orders";
    public static  final String ID = "id";
    public static  final String ORDER = "order_number";
    public static  final String ITEM_NUMBER = "item_number";
    public static  final String AMOUNT = "amount";
    public static  final String UNIT_PRICE = "unit_price";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private int id;

    @Column(name = ORDER)
    private String order;

    @Column(name = ITEM_NUMBER)
    private String itemNumber;

    @Column(name = AMOUNT)
    private int amount;

    @Column(name = UNIT_PRICE)
    private float unitPrice;


    @Transient
    private float price;

    @Transient
    private float avarageAmount;

    public int addAmount(int amount){
        return this.amount += amount;
    }

    public float addUnitPrice(float unitPrice){
        return this.unitPrice += unitPrice;
    }

    public float getPrice(){
        return amount * unitPrice;
    }


}
