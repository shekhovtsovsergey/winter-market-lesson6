package ru.geekbrains.winter.market.core.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.winter.market.core.entities.Order;
import ru.geekbrains.winter.market.core.entities.Product;
import ru.geekbrains.winter.market.core.repositories.CategoryRepository;
import ru.geekbrains.winter.market.core.repositories.OrderRepository;
import ru.geekbrains.winter.market.core.repositories.ProductRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@DisplayName("класс productService")
public class OrderServiceTest {


    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;



    @DisplayName("корректно возвращает Product")
    @Test
    void testCreateOrder() {
        Product product = new Product();
        product.setTitle("Test");
        product.setCategory(categoryRepository.findById(1L).orElseThrow());
        product = productRepository.save(product);
        Product createdProduct = productService.findById(product.getId()).orElseThrow();
        Assertions.assertNotNull(createdProduct);
        Assertions.assertNotNull(createdProduct.getId());
        Assertions.assertEquals(product.getId(), createdProduct.getId());

    }


}
