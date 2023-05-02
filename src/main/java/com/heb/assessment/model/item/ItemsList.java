package com.heb.assessment.model.item;

import java.util.List;

public class ItemsList {
    private List<Item> items;

    public ItemsList() {
    }

    public ItemsList(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
