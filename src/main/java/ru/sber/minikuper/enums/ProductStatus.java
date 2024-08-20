package ru.sber.minikuper.enums;

import java.util.HashMap;
import java.util.Map;

public enum ProductStatus {

    ACTIVE(0, "Активен"),
    HIDE(1, "Скрыт"),
    ARCHIVED(2, "Архивирован");

    private final int id;
    private final String title;

    ProductStatus(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int id() {
        return id;
    }

    public String title() {
        return title;
    }

    private static final Map<Integer, ProductStatus> idToParamMap;

    static {
        idToParamMap = new HashMap<>();
        for (ProductStatus param : ProductStatus.values()) {
            idToParamMap.put(param.id, param);
        }
    }

    public static ProductStatus getById(int id) {
        return idToParamMap.get(id);
    }




}
