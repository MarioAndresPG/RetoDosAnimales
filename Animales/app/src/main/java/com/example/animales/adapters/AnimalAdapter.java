package com.example.animales.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animales.R;
import com.example.animales.entity.Animal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimalAdapter extends BaseAdapter {

    private List<Animal> listaAnimalesOut;
    private List<Animal> listaAnimalesIn;
    private final LayoutInflater inflater;

    public AnimalAdapter(Context context, List<Animal> listaAnimales) {
        listaAnimalesOut=listaAnimales;
        listaAnimalesIn=listaAnimales;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaAnimalesOut.size();
    }


    public Filter getFilter() {

        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listaAnimalesOut = (List<Animal>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Animal> FilteredArrList = new ArrayList<>();
                if (listaAnimalesIn == null) {
                    listaAnimalesIn = new ArrayList<>(listaAnimalesOut);
                }
                if (constraint == null || constraint.length() == 0) {
                    results.count = listaAnimalesIn.size();
                    results.values = listaAnimalesIn;
                } else {

                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < listaAnimalesIn.size(); i++) {
                        String data = listaAnimalesIn.get(i).getNombre();
                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(listaAnimalesIn.get(i));
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    @Override
    public Animal getItem(int i) {
        return listaAnimalesOut.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.animal_item_layout, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.imagen.setImageResource(listaAnimalesOut.get(i).getImagen());
        holder.txtNombre.setText(listaAnimalesOut.get(i).getNombre());
        holder.txtDescripcion.setText(listaAnimalesOut.get(i).getDescripcion());

        return view;
    }

    class ViewHolder{
        @BindView(R.id.imagen)
        ImageView imagen;
        @BindView(R.id.txtNombre)
        TextView txtNombre;
        @BindView(R.id.txtDescripcion)
        TextView txtDescripcion;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

