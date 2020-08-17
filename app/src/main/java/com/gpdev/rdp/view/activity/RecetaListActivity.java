package com.gpdev.rdp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import com.gpdev.rdp.App;
import com.gpdev.rdp.R;

import com.gpdev.rdp.dao.DaoSession;
import com.gpdev.rdp.model.RecetarioType;
import com.gpdev.rdp.view.activity.dummy.DummyContent;
import com.gpdev.rdp.view.adapter.ElementoListable;
import com.gpdev.rdp.view.adapter.SimpleItemRecyclerViewAdapter;

import org.greenrobot.greendao.AbstractDao;

import java.util.EnumMap;
import java.util.List;

import static com.gpdev.rdp.view.activity.RecetaDetailActivity.PARENT;

/**
 * An activity representing a list of Recetas. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecetaDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecetaListActivity extends AppCompatActivity {

    public static final String ARG_ITEM = "item"; //TODO usar una clase común para estos valores?

    private EnumMap<RecetarioType, AbstractDao> daoMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_list);
        setTitle(R.string.categorias_title);

        buildDaoMap();

        initViewComponents();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void buildDaoMap() {
        daoMap = new EnumMap<>(RecetarioType.class);
        DaoSession daoSession = ((App) getApplication()).getDaoSession();

        //daoMap.put(RecetarioType.PLATO, daoSession.getPlatoDao());
        daoMap.put(RecetarioType.CATEGORIA, daoSession.getCategoriaDao());
        daoMap.put(RecetarioType.RECETA, daoSession.getRecetaDao());
    }

    private void initViewComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setupListView();
    }

    private void setupListView( ) {
        RecyclerView recyclerView = findViewById(R.id.receta_list);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        RecetarioType typeSelected = (RecetarioType) bundle.get(getString(R.string.ELEMENTO_SELECCIONADO));
        if (typeSelected != null) {
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(RecetaListActivity.this, daoMap.get(typeSelected).loadAll()));
            return;
        }

        ElementoListable elementSelected = (ElementoListable) bundle.get(RecetaDetailFragment.ARG_ITEM);
        if (elementSelected != null) {
            setTitle(elementSelected.getTitle());
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, getChildren(elementSelected)));
            return;
        }
    }

    private List<ElementoListable> getChildren(ElementoListable elementSelected) {
        ElementoListable parent = (ElementoListable) daoMap.get(elementSelected.getType()).loadByRowId(elementSelected.getId());
        return parent.getChilds();
    }

    private void goToMain() {
        Intent intent = new Intent(this, ListaRecetasActivity.class);
        startActivity(intent);
    }

}
