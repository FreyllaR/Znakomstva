package com.example.znakomstva;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button; // Импортируем Button
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class activity_register extends Fragment { // Измените имя класса на ActivityRegister для соответствия стандартам именования

    public activity_register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity_register, container, false);

        // Найдите ваш TextView
        TextView registerTextView = view.findViewById(R.id.registerTextView);
        Button registerButton = view.findViewById(R.id.registerButton); // Найдите вашу кнопку

        // Установите обработчик клика для TextView
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход к фрагменту регистрации
                loadFragment(new FirstRegisterFragment()); // Замените FirstRegisterFragment на ваш фрагмент регистрации
            }
        });

        // Установите обработчик клика для кнопки registerButton
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход к фрагменту GEOFragment
                loadFragment(new GEOFragment()); // Замените GEOFragment на ваш фрагмент геолокации
            }
        });

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