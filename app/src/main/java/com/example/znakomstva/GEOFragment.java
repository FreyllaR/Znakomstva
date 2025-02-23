package com.example.znakomstva;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class GEOFragment extends Fragment {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;

    public GEOFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_g_e_o, container, false);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        Button enableLocationButton = view.findViewById(R.id.enable_location_button);
        Button disableLocationButton = view.findViewById(R.id.disable_location_button);

        enableLocationButton.setOnClickListener(v -> {
            enableLocation();
            goToAboutPersonFragment(); // Переход после нажатия на кнопку
        });

        disableLocationButton.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Геолокация отключена", Toast.LENGTH_SHORT).show();
            goToAboutPersonFragment(); // Переход после нажатия на кнопку
        });

        return view;
    }

    private void enableLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Запрашиваем разрешение
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Разрешение уже предоставлено, получаем местоположение
            getLocation();
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                // Здесь вы можете использовать местоположение
                                Toast.makeText(getActivity(), "Широта: " + location.getLatitude() + ", Долгота: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Геолокация не включена на устройстве", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void goToAboutPersonFragment() {
        AboutPersonFragment aboutPersonFragment = new AboutPersonFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, aboutPersonFragment); // Замените fragment_container на ID вашего контейнера
        fragmentTransaction.addToBackStack(null); // Добавьте в стек, чтобы можно было вернуться
        fragmentTransaction.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(getActivity(), "Разрешение на геолокацию отклонено", Toast.LENGTH_SHORT).show();
            }
        }
    }
}