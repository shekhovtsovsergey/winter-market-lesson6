package ru.geekbrains.winter.market.carts.convertes;

import org.springframework.stereotype.Component;
import ru.geekbrains.winter.market.api.CartItemDto;
import ru.geekbrains.winter.market.carts.model.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setProductId(cartItem.getProductId());
        return cartItemDto;
    }
}
