package com.example.navdrawer.ui.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navdrawer.CocheLab;
import com.example.navdrawer.R;
import com.example.navdrawer.model.Coche;

import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private EditText etId;
    private EditText etNombre;
    private EditText etColor;
    private EditText etPuertas;

    private CocheLab cocheLab;
    private Coche coche;

    private String strId;
    private String strNombre;
    private String strColor;
    private int intPuertas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Button botonGuardar = (Button) findViewById(R.id.bt_room_guardar);
        Button botonBorrar = (Button) findViewById(R.id.bt_room_borrar);
        Button botonTodos = (Button) findViewById(R.id.bt_room_todos);


        etId = findViewById(R.id.etID);
        etNombre = findViewById(R.id.etNombre);
        etColor = findViewById(R.id.etColor);
        etPuertas = findViewById(R.id.etPuertas);

        cocheLab = CocheLab.get(this);

//        CocheLab cocheLab = CocheLab.get(this); // Nos devuelve el objeto NotaLab

// para el master detail
//        List<Coche> allCoches = cocheLab.getAllCoches();
//
//        if (allCoches.size() > 0) {
//            mNota = allCoches.get(0);
//            mTextoNota.setText(mNota.getMensaje());
//        }

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strId = etId.getText().toString();
                strNombre = etNombre.getText().toString();
                strColor = etColor.getText().toString();
                intPuertas = Integer.parseInt(etPuertas.getText().toString());
                coche = cocheLab.getCoche(strId);


                guardar();
            }
        });
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strId = etId.getText().toString();
                strNombre = etNombre.getText().toString();
                strColor = etColor.getText().toString();
                intPuertas = Integer.parseInt(etPuertas.getText().toString());
                coche = cocheLab.getCoche(strId);
                borrar();
            }
        });

        botonTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentSumar = new Intent(v.getContext(), CocheListActivity.class);
                startActivity(intentSumar);

            }
        });

    }


    private void guardar() {
        coche = cocheLab.getCoche(strId);

        if (coche == null) {
            try {

                coche = new Coche(
                        strNombre,
                        strColor,
                        intPuertas
                );
                cocheLab.addCoche(coche);
                Toast.makeText(this, "Registro creado",
                        Toast.LENGTH_SHORT).show();


            } catch (NumberFormatException e) {
                Toast.makeText(this, "Introduzca un nº entero en nº puertas",
                        Toast.LENGTH_SHORT).show();
            }

        } else {
            coche.setNombre(strNombre);
            coche.setColor(strColor);
            coche.setPuertas(intPuertas);
            cocheLab.updateCoche(coche);
            Toast.makeText(this, "registro actualizado",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void borrar() {
        coche = cocheLab.getCoche(strId);

        if (coche != null) {
            cocheLab.deleteCoche(coche);
            coche = null;
            Toast.makeText(this, "Coche eliminado",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe coche con este ID",
                    Toast.LENGTH_SHORT).show();
        }
    }


}

