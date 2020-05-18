package com.example.navdrawer.ui.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.navdrawer.BBDD_helper;
import com.example.navdrawer.EstructuraBD;
import com.example.navdrawer.model.Persona;
import com.example.navdrawer.MainActivity;
import com.example.navdrawer.R;

import java.util.ArrayList;
import java.util.List;

public class SqliteFrament extends Fragment  {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sql, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {

        Vibrator vibrator = (Vibrator) MainActivity.getInstance().getSystemService(Context.VIBRATOR_SERVICE);
        super.onViewCreated(view, savedInstanceState);



//botones
        Button botonInsertar = (Button) view.findViewById(R.id.insertar);
        Button botonActualizar = (Button) view.findViewById(R.id.actualizar);
        Button botonBorrar = (Button) view.findViewById(R.id.borrar);
        Button botonBuscar = (Button) view.findViewById(R.id.buscar);
        Button botonTodos = (Button) view.findViewById(R.id.todos);


        //editables
        EditText textoId = (EditText) view.findViewById(R.id.id);
        EditText textoNombre = (EditText) view.findViewById(R.id.nombre);
        EditText textoApellido = (EditText) view.findViewById(R.id.apellido);

//Creamos una instancia de nuestra clase que heredaba de SQLiteOpenHelper:
        final BBDD_helper helper = new BBDD_helper(MainActivity.getInstance());

        //Hacemos que los botones escuchen el click
        botonInsertar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();

                (Toast.makeText(MainActivity.getInstance(), "Insertado el registro ", Toast.LENGTH_LONG)).show();

// Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();

                values.put(EstructuraBD.COL1, textoId.getText().toString());
                values.put(EstructuraBD.COL2, textoNombre.getText().toString());
                values.put(EstructuraBD.COL3, textoApellido.getText().toString());

// Insert the new row, returning the primary key value of the new row
                long idFila = db.insert(EstructuraBD.NOMBRE_TABLA, null, values);

                (Toast.makeText(MainActivity.getInstance(), "Se guardó un registro con clave " + idFila, Toast.LENGTH_LONG)).show();
            }
        });

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                //SQLiteDatabase db = helper.getReadableDatabase();
// New value/s for columns
                ContentValues values = new ContentValues();
                values.put(EstructuraBD.COL2, textoNombre.getText().toString());
                values.put(EstructuraBD.COL3, textoApellido.getText().toString());

// Which row to update
                String selection = EstructuraBD.COL1 + " LIKE ?";
                String[] selectionArgs = { textoId.getText().toString() };

                int count = db.update(
                        EstructuraBD.NOMBRE_TABLA,
                        values,
                        selection,
                        selectionArgs);

                (Toast.makeText(MainActivity.getInstance(), "Se actualizó el registro" , Toast.LENGTH_LONG)).show();
            }
        });

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getReadableDatabase();

                // Define a projection that specifies which columns from the database
// you will actually use after this query.
                String[] projection = {
                        EstructuraBD.COL2,
                        EstructuraBD.COL3
                };

// Filter results WHERE "title" = 'My Title'
                String selection = EstructuraBD.COL1 + " = ?";
                String[] selectionArgs = { textoId.getText().toString() };

// How you want the results sorted in the resulting Cursor
                //String sortOrder = EstructuraBD.COL2 + " DESC"; //Descendentemente por apellido
                // Solo devuelve un registro
                try {
                    Cursor cursor = db.query(
                            EstructuraBD.NOMBRE_TABLA,      // The table to query
                            projection,                     // The array of columns to return (pass null to get all)
                            selection,                      // The columns for the WHERE clause
                            selectionArgs,                  // The values for the WHERE clause
                            null,                  // don't group the rows
                            null,                   // don't filter by row groups
                            null                    // The sort order
                    );

                    cursor.moveToFirst();

                    /*
                    List itemIds = new ArrayList<>();
                    while(cursor.moveToNext()) {
                        long itemId = cursor.getLong(
                                cursor.getColumnIndexOrThrow(EstructuraBD.COL1));
                        itemIds.add(itemId);
                    }

                 */
                    textoNombre.setText(cursor.getString(0));
                    textoApellido.setText(cursor.getString(1));
                    cursor.close();
                } catch (Exception e) {
                    (Toast.makeText(MainActivity.getInstance(), "No se ha encontrado el registro" , Toast.LENGTH_LONG)).show();
                }

            }
        });

        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                ///Define 'where' part of query.
                String selection = EstructuraBD.COL1 + " LIKE ?";
                // Specify arguments in placeholder order.
                String[] selectionArgs = { textoId.getText().toString() };
                // Issue SQL statement.
                int deletedRows = db.delete(EstructuraBD.NOMBRE_TABLA, selection, selectionArgs);
                (Toast.makeText(MainActivity.getInstance(), "Se borró el registro con clave " + textoId.getText().toString(), Toast.LENGTH_LONG)).show();
                textoId.setText("");
                textoNombre.setText("");
                textoApellido.setText("");
            }
        });


        botonTodos.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                vibrator.vibrate(100);
                Navigation.findNavController(view).navigate(R.id.action_nav_sql_to_nav_listViewPersonas);

            }
        });
    }


}

