package com.tw.joi.delivery.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private String cartId;
    private Outlet outlet;

    @Builder.Default
    private List<Product> products = new ArrayList<>();

    private User user;

    public void addProduct(Product product) {
        products.add(product);
    }

}
