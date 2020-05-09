package com.gpdev.rdp.model;

public enum RecetarioType {

    PLATO(true),
    CATEGORIA(true),
    RECETA(false);

    private boolean parent;

    RecetarioType(boolean b) {
    }
}
