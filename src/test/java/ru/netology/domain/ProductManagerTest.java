package ru.netology.domain;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@Data

public class ProductManagerTest {
    protected Repository repository = new Repository();
    protected ProductManager manager = new ProductManager();
    protected Book book = new Book();
    protected Smartphone smartphone = new Smartphone();


    public ProductManagerTest() {
        this.repository = getRepository();
        this.manager = getManager();
    }


    Book book1 = new Book(1, "Java", 10.25, "Author1_s");
    Book book2 = new Book(2, "JS", 11.25, "Author2");
    Book book3 = new Book(3, "Basic", 12.25, "Author3");
    Smartphone smartphone1 = new Smartphone(4, "iPhone", 999.99, "Apple Inc.");
    Smartphone smartphone2 = new Smartphone(5, "Huawei", 999.99, "Huawei Technology Co.");
    Smartphone smartphone3 = new Smartphone(6, "Samsung", 999.99, "Samsung Group");
    Book newBook = new Book(7, "Pascal", 10.10, "Author4");


    @BeforeEach
    public void setUp() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(smartphone1);
        repository.save(smartphone2);
        repository.save(smartphone3);


    }

    @Test
    public void checkFindAll() {
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{book1, book2, book3, smartphone1, smartphone2, smartphone3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void checkSavingRepository() {
        repository.save(newBook);
        Product[] expected = new Product[]{book1, book2, book3, smartphone1, smartphone2, smartphone3, newBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);

    }

    @Test
    public void checkRemoveFindById() {
        Product[] actual = repository.removeById(3);
        Product[] expected = new Product[]{book1, book2, smartphone1, smartphone2, smartphone3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void checkAdding() {
        ProductManager manager = new ProductManager(repository);
        manager.add(newBook);
        Product[] expected = new Product[]{book1, book2, book3, smartphone1, smartphone2, smartphone3, newBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void checkFindByQuery() {
        ProductManager manager = new ProductManager(repository);
        Product[] expected = manager.searchByQuery("s");
        Product[] actual = new Product[]{book1, book3, smartphone3};
        assertArrayEquals(expected, actual);
    }
}
