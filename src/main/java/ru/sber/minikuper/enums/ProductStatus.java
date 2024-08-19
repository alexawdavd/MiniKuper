package ru.sber.minikuper.enums;

public enum ProductStatus {

    ACTIVE(1, "Активен"),
    HIDE(2, "Скрыт"),
    ARCHIVED(3, "Архивирован");

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

}
