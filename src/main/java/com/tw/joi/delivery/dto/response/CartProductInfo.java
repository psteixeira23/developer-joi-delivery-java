package com.tw.joi.delivery.dto.response;

import com.tw.joi.delivery.domain.Cart;
import com.tw.joi.delivery.domain.Product;
import java.math.BigDecimal;

/**
 * Response containing cart and product information.
 *
 * @param cart updated cart
 * @param product added product
 * @param sellingPrice effective selling price
 */
public record CartProductInfo(Cart cart, Product product, BigDecimal sellingPrice) {}
