package com.gpdev.rdp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gpdev.rdp.R;
import com.gpdev.rdp.model.RecetarioType;
import com.gpdev.rdp.view.adapter.ElementoListable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.listarRecetasBtn:
                goToDetail(RecetarioType.RECETA);
                break;

            case R.id.listarCategoriasBtn:
                goToDetail(RecetarioType.CATEGORIA);
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
