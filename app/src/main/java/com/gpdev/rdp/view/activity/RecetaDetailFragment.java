package com.gpdev.rdp.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gpdev.rdp.R;
import com.gpdev.rdp.model.Receta;

/**
 * A fragment representing a single Receta detail screen.
 * This fragment is either contained in a {@link RecetaListActivity}
 * in two-pane mode (on tablets) or a {@link RecetaDetailActivity}
 * on handsets.
 */
public class RecetaDetailFragment extends Fragment {

    public static final String ARG_ITEM = "item";

    private Receta receta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM)) {
            receta = (Receta) getArguments().get(ARG_ITEM);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(receta.getTitle());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receta_detail, container, false);

        if (receta != null) {
            ((TextView) rootView.findViewById(R.id.recipe_ingredients)).setText(receta.getIngredientes());
            ((TextView) rootView.findViewById(R.id.recipe_preparation)).setText(receta.getPreparacion());
        }

        return rootView;
    }

}
