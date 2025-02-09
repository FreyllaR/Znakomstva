package com.example.znakomstva;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SplashScreen extends Fragment {

    public SplashScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        // Задержка перед переходом к ActivityRegister
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Переход к ActivityRegister
                loadFragment(new activity_register());
            }
        }, 3000); // Задержка в 3 секунды (3000 миллисекунд)

        return view;
    }

    private void loadFragment(Fragment fragment) {
        // Получаем FragmentManager
        FragmentManager fragmentManager = getParentFragmentManager(); // Используйте getParentFragmentManager() для фрагментов
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment); // Убедитесь, что идентификатор совпадает
        fragmentTransaction.addToBackStack(null); // Добавляем в back stack, если нужно
        fragmentTransaction.commit();
    }
}