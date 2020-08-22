package com.gpdev.rdp.model;

public enum RecetarioType {

    PLATO(true, "Platos"),
    CATEGORIA(true, "Categorias"),
    RECETA(false, "Recetas");

    private boolean parent;
    private String label;

    RecetarioType(boolean parent, String label) {
        this.parent = parent;
        this.label = label;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
