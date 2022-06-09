package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductManager {
    protected Repository repository = new Repository();


//    public boolean matches(Product product, String query) {
//        if (product.getName().contains(query)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

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

}
