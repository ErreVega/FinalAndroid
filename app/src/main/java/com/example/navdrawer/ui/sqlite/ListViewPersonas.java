package com.example.navdrawer.ui.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.navdrawer.BBDD_helper;
import com.example.navdrawer.EstructuraBD;
import com.example.navdrawer.MainActivity;
import com.example.navdrawer.R;
import com.example.navdrawer.model.AdapterPersona;
import com.example.navdrawer.model.Persona;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListViewPersonas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListViewPersonas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListViewPersonas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListViewPersonas.
     */
    // TODO: Rename and change types and number of parameters
    public static ListViewPersonas newInstance(String param1, String param2) {
        ListViewPersonas fragment = new ListViewPersonas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view_personas, container, false);




        BBDD_helper helper = new BBDD_helper(MainActivity.getInstance());
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<Persona> listPersonas = new ArrayList<Persona>();

        try {
            Cursor cursor = db.query(
                    EstructuraBD.NOMBRE_TABLA,      // The table to query
                    null,                     // The array of columns to return (pass null to get all)
                    null,                      // The columns for the WHERE clause
                    null,                  // The values for the WHERE clause
                    null,                  // don't group the rows
                    null,                   // don't filter by row groups
                    null                    // The sort order
            );

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Persona p = new Persona(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                listPersonas.add(p);
                cursor.moveToNext();
            }
            cursor.close();

        } catch (Exception e) {
            (Toast.makeText(MainActivity.getInstance(), "No se ha encontrado el registro", Toast.LENGTH_LONG)).show();
        }



        ListView lv = (ListView) view.findViewById(R.id.listviewPersonas);
        AdapterPersona arrayAdapter = new AdapterPersona(MainActivity.getInstance(), listPersonas);
        lv.setAdapter(arrayAdapter);

        return view;
    }



}




