package com.gpdev.rdp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gpdev.rdp.R;
import com.gpdev.rdp.view.activity.RecetaDetailActivity;
import com.gpdev.rdp.view.activity.RecetaDetailFragment;
import com.gpdev.rdp.view.activity.RecetaListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> implements Filterable {

    private RecetaListActivity mParentActivity;
    private List<ElementoListable> elementList;
    private List<ElementoListable> filteredElementList;

    public SimpleItemRecyclerViewAdapter(RecetaListActivity parent, List<ElementoListable> items) {
        elementList = items;
        filteredElementList = items;
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
        if (filteredElementList == null || filteredElementList.isEmpty()){
            return;
        }
        
        try {

            holder.mIdView.setText(filteredElementList.get(position).getId().toString());
            holder.mContentView.setText(filteredElementList.get(position).getTitle());

            holder.itemView.setTag(filteredElementList.get(position));
            holder.itemView.setOnClickListener(buildOnClickListener(position));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(final CharSequence charSequence) {
                final String charString = charSequence.toString();
                FilterResults filterResults = new FilterResults();

                if (charString.isEmpty()) {
                    filteredElementList = elementList;
                    return filterResults;
                }

                filteredElementList = elementList.stream()
                        .filter(e -> e.getTitle().toLowerCase().contains(charString.toLowerCase()))
                        .collect(Collectors.toList());

                filterResults.values = filteredElementList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredElementList = (ArrayList<ElementoListable>) filterResults.values;

                notifyDataSetChanged();
            }
        };
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
                ElementoListable item = filteredElementList.get(position);

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
