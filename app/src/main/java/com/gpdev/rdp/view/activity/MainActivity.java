package com.gpdev.rdp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gpdev.rdp.R;
import com.gpdev.rdp.model.RecetarioType;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.listarRecetasBtn:
                goToDetail(RecetarioType.RECETA);
                break;

            case R.id.listarCategoriasBtn:
                goToDetail(RecetarioType.CATEGORIA);
                break;

            case R.id.listarPlatosBtn:
                goToDetail(RecetarioType.PLATO);
                break;

            case R.id.scroll:
                Intent intent = new Intent(MainActivity.this, ListaRecetasActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    private void goToDetail(RecetarioType type) {
        Intent intent = new Intent(MainActivity.this, RecetaListActivity.class);
        intent.putExtra(getString(R.string.ELEMENTO_SELECCIONADO), type);
        startActivity(intent);
    }

}
