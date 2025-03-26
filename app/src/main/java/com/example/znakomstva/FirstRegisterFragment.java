package com.example.znakomstva;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log; // Импортируйте класс Log
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstRegisterFragment extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;

    public FirstRegisterFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_register, container, false);

        usernameEditText = view.findViewById(R.id.usernameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);

        ImageButton backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new activity_register());
            }
        });

        Button registerButton = view.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser (); // Вызов метода для регистрации
            }
        });

        return view;
    }

    private void registerUser () {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Проверка на пустые поля
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(), "Пожалуйста заполните все поля :(", Toast.LENGTH_SHORT).show();
            return;
        }

        // Создание объекта UserDto для передачи данных
        UserDto user = new UserDto(username, password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.31.125:8080/") // Замените на ваш IP-адрес
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<UserDto> call = apiService.registerUser(user); // Отправьте объект UserDto
        call.enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Аккаунт успешно создан :) Мы тебя ждем!", Toast.LENGTH_SHORT).show();
                    loadFragment(new activity_register());
                } else {
                    String errorMessage = "Не получилось зарегистрироваться: " + response.message();
                    Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    Log.e("RegistrationError", errorMessage);
                }
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
                String errorMessage = "Error: " + t.getMessage();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                Log.e("RegistrationError", errorMessage, t);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}