package com.example.navdrawer.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.navdrawer.R;
import java.util.ArrayList;

public class AdapterPersona extends ArrayAdapter<Persona> {

        public AdapterPersona(Context context, ArrayList<Persona> per) {
            super(context, 0, per);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Persona persona = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_persona, parent, false);
            }
            // Lookup view for data population
            TextView tvID = (TextView) convertView.findViewById(R.id.tvId);
            TextView tvNombre = (TextView) convertView.findViewById(R.id.tvNombre);
            TextView tvApellido = (TextView) convertView.findViewById(R.id.tvApellido);

            // Populate the data into the template view using the data object
            tvID.setText(" " + persona.getId());
            tvNombre.setText(persona.getNombre());
            tvApellido.setText(persona.getApellido());
            // Return the completed view to render on screen
            return convertView;
        }



}
