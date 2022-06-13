package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Product {
    protected int id;
    protected String name;
    protected double cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean matches(String query) {
        if (getName().contains(query)) {
            return true;
        } else {
            return false;
        }
    }
}
