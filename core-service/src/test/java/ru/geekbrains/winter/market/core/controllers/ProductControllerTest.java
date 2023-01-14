package ru.geekbrains.winter.market.core.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.geekbrains.winter.market.core.entities.Category;
import ru.geekbrains.winter.market.core.entities.Product;
import ru.geekbrains.winter.market.core.repositories.CategoryRepository;
import ru.geekbrains.winter.market.core.repositories.ProductRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@DisplayName("класс ProductController")
class ProductControllerTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    private CategoryRepository categoryRepository;



    @DisplayName("корректно возвращает Product")
    @Test
    void findProductById() {
        Product product = new Product();
        product.setTitle("TestProduct");
        product.setCategory(categoryRepository.findById(1L).orElse(null));
        product = productRepository.save(product);
        Product productByHttp = webTestClient.get()
                .uri("/api/v1/products/" + product.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(product.getId(), productByHttp.getId());
        Assertions.assertEquals(product.getTitle(), productByHttp.getTitle());
    }


}
