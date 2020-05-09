package com.gpdev.rdp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.gpdev.rdp.App;
import com.gpdev.rdp.R;
import com.gpdev.rdp.dao.DaoSession;
import com.gpdev.rdp.model.RecetarioType;
import com.gpdev.rdp.view.adapter.ElementoListable;
import com.gpdev.rdp.view.adapter.GenericAdapter;

import org.greenrobot.greendao.AbstractDao;

import java.util.EnumMap;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    private ListView listView;
    private EnumMap<RecetarioType, AbstractDao> daoMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        buildDaoMap();

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            goToMain();
            return;
        }

        setUpListView(bundle);
    }

    private void buildDaoMap() {
        daoMap = new EnumMap<>(RecetarioType.class);
        DaoSession daoSession = ((App) getApplication()).getDaoSession();

        //daoMap.put(RecetarioType.PLATO, daoSession.getPlatoDao());
        daoMap.put(RecetarioType.CATEGORIA, daoSession.getCategoriaDao());
        daoMap.put(RecetarioType.RECETA, daoSession.getRecetaDao());
    }

    private void setUpListView(Bundle bundle) {
        RecetarioType recetarioType = (RecetarioType) bundle.get(getString(R.string.ELEMENTO_SELECCIONADO));
        buildListView();
        fillListView(daoMap.get(recetarioType).loadAll());
    }

    private void goToMain() {
        Intent intent = new Intent(ListadoActivity.this, ListaRecetasActivity.class);
        startActivity(intent);
    }

    private void buildListView() {
        listView = findViewById(R.id.categoriasListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                manageItemClick((ElementoListable) listView.getItemAtPosition(position));
            }
        });
    }

    private void manageItemClick(ElementoListable selectedElement) {
        if (selectedElement.isParent()){
            fillListView(selectedElement.getChilds());
            return;
        }

        goToDetail(selectedElement);
    }

    private void fillListView(List<ElementoListable> list) {
        listView.setAdapter(new GenericAdapter(ListadoActivity.this, list));
    }

    private void goToDetail(ElementoListable elemento) {
        Intent intent = new Intent(ListadoActivity.this, ListaRecetasActivity.class);
        intent.putExtra(getString(R.string.ELEMENTO_SELECCIONADO), elemento);
        startActivity(intent);
    }

}
