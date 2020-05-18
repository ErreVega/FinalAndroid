package com.example.navdrawer.ui.accelerometro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navdrawer.MainActivity;
import com.example.navdrawer.R;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccelerometroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccelerometroFragment extends Fragment implements SensorEventListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Sensor _acelerometro;
    private TextView _etX;
    private TextView _etY;
    private TextView _etZ;
    private TextView _etPrecision;
    private SensorManager _sensorManager;


    public AccelerometroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccelerometroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccelerometroFragment newInstance(String param1, String param2) {
        AccelerometroFragment fragment = new AccelerometroFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accelerometro, container, false);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {


        _sensorManager = (SensorManager) MainActivity.getInstance().getSystemService(SENSOR_SERVICE);
        _acelerometro = _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        _etX = (TextView) view.findViewById(R.id.tvValorX);
        _etY = (TextView) view.findViewById(R.id.tvValorY);
        _etZ = (TextView) view.findViewById(R.id.tvValorZ);
        _etPrecision = (TextView)view.findViewById(R.id.tvValorPrecision);




    }

    public void onResume() {
        super.onResume();
        _sensorManager.registerListener(this, _acelerometro,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        _sensorManager.unregisterListener(this);
    }




    @Override
    public void onSensorChanged(SensorEvent event) {
        _etX.setText(""+event.values[0]);
        _etY.setText(""+event.values[1]);
        _etZ.setText(""+event.values[2]);
        _etPrecision.setText(""+event.accuracy);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
