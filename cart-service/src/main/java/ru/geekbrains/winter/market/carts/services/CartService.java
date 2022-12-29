package ru.geekbrains.winter.market.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.winter.market.api.ProductDto;
import ru.geekbrains.winter.market.api.ResourceNotFoundException;
import ru.geekbrains.winter.market.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.winter.market.carts.model.Cart;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.add(product);
    }

    public void remove(Long productId) {
        tempCart.remove(productId);
    }

    public void clear() {
        tempCart.clear();
    }
}
