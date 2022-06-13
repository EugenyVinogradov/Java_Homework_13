package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductManager {
    protected Repository repository = new Repository();

    public void add(Product newProduct) {
        repository.save(newProduct);
    }

    public Product[] searchByQuery(String query) {
        Product[] result = new Product[0];
        for (Product product : repository.findAll()) {
            if (product.matches(query)) {
                Product[] tmp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    public Product[] findById(int id) {
        Product[] tmp = new Product[1];
        for (Product product : repository.findAll()) {
            if (id == product.getId()) {
                tmp[0] = product;
                return tmp;
            }
        }
        return null;
    }

    public void removedById(int id) {

        Product[] tmp = new Product[repository.products.length - 1];
        if (findById(id) != null) {
            int index = 0;
            for (Product product : repository.findAll()) {
                if (id != product.getId()) {
                    tmp[index] = product;
                    index++;
                }
                repository.products = tmp;
            }
        } else {
            throw new NotFoundByIdException("Element with id: " + id + " not found");

        }
    }

}
