package com.gpdev.rdp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.gpdev.rdp.App;
import com.gpdev.rdp.R;
import com.gpdev.rdp.dao.CategoriaDao;
import com.gpdev.rdp.dao.DaoSession;
import com.gpdev.rdp.dao.RecetaDao;
import com.gpdev.rdp.model.Categoria;
import com.gpdev.rdp.model.Receta;
import com.gpdev.rdp.view.adapter.GenericAdapter;

import java.util.List;

public class ListaRecetasActivity extends AppCompatActivity {

    private RecetaDao recetaDao;
    private CategoriaDao categoriaDao;
    private Categoria categoria;
    private List<Receta> recetas;
    ListView recetasListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recetas);
        recetasListView = findViewById(R.id.recetasListView);

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        recetaDao = daoSession.getRecetaDao();
        categoriaDao = daoSession.getCategoriaDao();

        establecerListaRecetas();
    }

    private void establecerListaRecetas() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            recetasListView.setAdapter(rellenarListaConRecetasAsociadasACategoria(bundle));
            return;
        }

        recetas = recetaDao.loadAll();
        GenericAdapter<Receta> adapter = new GenericAdapter(ListaRecetasActivity.this, recetas);
        recetasListView.setAdapter(adapter);
    }

    private GenericAdapter rellenarListaConRecetasAsociadasACategoria(Bundle bundle) {
        Long idCategoria = (Long) bundle.get(getString(R.string.ELEMENTO_SELECCIONADO));
        categoria = categoriaDao.loadByRowId(idCategoria);
        recetas = categoria.getRecetas();

        return new GenericAdapter(ListaRecetasActivity.this, recetas);
    }

}
