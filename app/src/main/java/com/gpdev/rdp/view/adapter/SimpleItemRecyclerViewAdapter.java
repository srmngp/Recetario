package com.gpdev.rdp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.gpdev.rdp.R;
import com.gpdev.rdp.view.activity.RecetaDetailActivity;
import com.gpdev.rdp.view.activity.RecetaDetailFragment;
import com.gpdev.rdp.view.activity.RecetaListActivity;

import java.util.List;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final RecetaListActivity mParentActivity;
    private final List<ElementoListable> mValues;

    public SimpleItemRecyclerViewAdapter(RecetaListActivity parent, List<ElementoListable> items) {
        mValues = items;
        mParentActivity = parent;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receta_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).getId().toString());
        holder.mContentView.setText(mValues.get(position).getTitle());

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(buildOnClickListener(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.id_text);
            mContentView = view.findViewById(R.id.content);
        }
    }

    private View.OnClickListener buildOnClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                ElementoListable item = mValues.get(position);

                if (item.isParent()){
                     Intent intent = new Intent(mParentActivity.getBaseContext(), RecetaListActivity.class);
                     intent.putExtra(RecetaListActivity.ARG_ITEM, item);
                     context.startActivity(intent);
                     return;
                 }

                    Intent intent = new Intent(context, RecetaDetailActivity.class);
                    intent.putExtra(RecetaDetailFragment.ARG_ITEM, item);

                    context.startActivity(intent);
            }
        };
    }
}
