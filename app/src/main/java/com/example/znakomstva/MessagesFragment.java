package com.example.znakomstva;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MessagesFragment extends Fragment {

    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        // Инициализация элементов интерфейса
        ImageView messageImageView = view.findViewById(R.id.messageImageView);
        TextView messageTextView = view.findViewById(R.id.messageTextView);

        // Установка текста
        messageTextView.setText("Здесь будет проходить ваше общение :)");

        return view;
    }
}