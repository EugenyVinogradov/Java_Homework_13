package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class Repository {
    protected Product[] products = new Product[0];

    public Repository() {
        this.products = products;
    }

    public Product[] findAll() {
        return products;
    }

    public void save(Product newProduct) {
        int length = products.length + 1;
        Product[] tmp = new Product[length];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = newProduct;
        products = tmp;
    }

    public Product[] removeById(int id) {
        Product[] tmp = new Product[products.length - 1];
        int index = 0;
        for (Product product : products) {
            if (id != product.getId()) {
                tmp[index] = product;
                index++;
            }
            products = tmp;
        }
        return products;
    }
}
