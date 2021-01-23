package com.gpdev.rdp.model;

public enum RecetarioType {

    RECETA("Recetas"),
    CATEGORIA("Categorías"),
    PLATO("Platos"),
    OTRO("Otros");

    private boolean parent;
    private String descripcion;

    RecetarioType(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isParent() {
        return parent;
    }

}
