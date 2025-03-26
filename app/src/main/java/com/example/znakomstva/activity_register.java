package com.example.znakomstva;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class activity_register extends Fragment {

    private ApiService apiService;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity_register, container, false);


        // Инициализация Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.31.125:8080") // Замените на ваш базовый URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

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
                usernameEditText = view.findViewById(R.id.usernameEditText);
                passwordEditText = view.findViewById(R.id.passwordEditText);
                checkUserExists(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        return view;
    }

    private void checkUserExists(String username, String password) {
        Call<Boolean> call = apiService.checkUserExists(username, password);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) { // Проверяем, что тело ответа не null
                        if (response.body()) {
                            Toast.makeText(getContext(), "Добро пожаловать!", Toast.LENGTH_SHORT).show();
                            loadFragment(new GEOFragment());
                        } else {
                            Toast.makeText(getContext(), "Нет такого аккаунта, зарегистрируемся?)", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Ответ от сервера пустой", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Ошибка: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(getContext(), "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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