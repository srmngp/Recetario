package com.gpdev.rdp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.gpdev.rdp.App;
import com.gpdev.rdp.R;

import com.gpdev.rdp.dao.DaoSession;
import com.gpdev.rdp.model.RecetarioType;
import com.gpdev.rdp.view.adapter.ElementoListable;
import com.gpdev.rdp.view.adapter.SimpleItemRecyclerViewAdapter;

import org.greenrobot.greendao.AbstractDao;

import java.util.EnumMap;
import java.util.List;

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

    private EditText filterEditText;
    private SimpleItemRecyclerViewAdapter adapter;

    private EnumMap<RecetarioType, AbstractDao> daoMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_list);

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

    private void initViewComponents() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setUpFloatingButton();
        setupListView();
        setUpListFilter();
    }

    private void setUpListFilter() {
        filterEditText = findViewById(R.id.buscarRecetaEditText);
        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                adapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void setUpFloatingButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filterEditText.isShown()){
                    filterEditText.setVisibility(View.GONE);
                    return;
                }
                filterEditText.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupListView( ) {
        RecyclerView recyclerView = findViewById(R.id.receta_list);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        RecetarioType typeSelected = (RecetarioType) bundle.get(getString(R.string.ELEMENTO_SELECCIONADO));
        if (typeSelected != null) {
            setTitle(typeSelected.getDescripcion());
            adapter = new SimpleItemRecyclerViewAdapter(RecetaListActivity.this, daoMap.get(typeSelected).loadAll());
            recyclerView.setAdapter(adapter);
            return;
        }

        ElementoListable elementSelected = (ElementoListable) bundle.get(RecetaDetailFragment.ARG_ITEM);
        if (elementSelected != null) {
            setTitle(elementSelected.getTitle());
            adapter = new SimpleItemRecyclerViewAdapter(this, getChildren(elementSelected));
            recyclerView.setAdapter(adapter);
            return;
        }
    }

    private List<ElementoListable> getChildren(ElementoListable elementSelected) {
        ElementoListable parent = (ElementoListable) daoMap.get(elementSelected.getType()).loadByRowId(elementSelected.getId());
        return parent.getChilds();
    }

    private void buildDaoMap() {
        daoMap = new EnumMap<>(RecetarioType.class);
        DaoSession daoSession = ((App) getApplication()).getDaoSession();

        daoMap.put(RecetarioType.PLATO, daoSession.getPlatoDao());
        daoMap.put(RecetarioType.CATEGORIA, daoSession.getCategoriaDao());
        daoMap.put(RecetarioType.RECETA, daoSession.getRecetaDao());
    }
}
