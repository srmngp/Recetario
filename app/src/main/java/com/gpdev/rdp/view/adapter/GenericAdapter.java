package com.gpdev.rdp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gpdev.rdp.R;

import java.util.List;

public class GenericAdapter<T extends ElementoListable> extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;

    List<T> elementList;

    public GenericAdapter(Context cont, List<T> elementList) {
        super();
        this.context = cont;
        this.elementList = elementList;
    }

    @Override
    public int getCount() {
        return elementList.size();
    }

    @Override
    public Object getItem(int position) {
        return elementList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return elementList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T element = elementList.get(position);
        layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.lista_item, null);
        TextView titleTextView = (TextView) view.findViewById(R.id.nombreTextView);
        titleTextView.setText(element.getTitle());

        return view;
    }
}
