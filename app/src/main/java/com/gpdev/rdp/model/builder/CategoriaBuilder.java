package com.gpdev.rdp.model.builder;

import com.gpdev.rdp.model.Categoria;

public class CategoriaBuilder {

    private Categoria categoria;

    private CategoriaBuilder(){
        super();
        categoria = new Categoria();
    }

    public static CategoriaBuilder unaCategoria(){
        return new CategoriaBuilder();
    }

    public Categoria build() {
        return categoria;
    }

    public CategoriaBuilder withNombre(String nombre){
        categoria.setNombre(nombre);
        return this;
    }

    public CategoriaBuilder withId(Long id){
        categoria.setId(id);
        return this;
    }

}
