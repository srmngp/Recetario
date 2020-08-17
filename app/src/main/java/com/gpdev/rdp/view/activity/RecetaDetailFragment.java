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
import com.gpdev.rdp.view.activity.dummy.DummyContent;
import com.gpdev.rdp.view.adapter.ElementoListable;

/**
 * A fragment representing a single Receta detail screen.
 * This fragment is either contained in a {@link RecetaListActivity}
 * in two-pane mode (on tablets) or a {@link RecetaDetailActivity}
 * on handsets.
 */
public class RecetaDetailFragment extends Fragment {

    public static final String ARG_ITEM = "item";

    /**
     * The dummy content this fragment is presenting.
     */
    private ElementoListable mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecetaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = (ElementoListable) getArguments().get(ARG_ITEM);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getTitle());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receta_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.receta_detail)).setText("Detalles del item");
        }

        return rootView;
    }
}
