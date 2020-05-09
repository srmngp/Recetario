package com.gpdev.rdp.model.builder;

import com.gpdev.rdp.model.Receta;

public class RecetaBuilder {

    private Receta receta;

    private RecetaBuilder(){
        super();
        receta = new Receta();
    }

    public static RecetaBuilder unaReceta(){
        return new RecetaBuilder();
    }

    public Receta build() {
        return receta;
    }

    public RecetaBuilder withNombre(String nombre){
        receta.setNombre(nombre);
        return this;
    }

    public RecetaBuilder withIngredientes(String ingredientes){
        receta.setIngredientes(ingredientes);
        return this;
    }

    public RecetaBuilder withPreparacion(String preparacion){
        receta.setPreparacion(preparacion);
        return this;
    }

    public RecetaBuilder withId(Long id){
        receta.setId(id);
        return this;
    }

}
