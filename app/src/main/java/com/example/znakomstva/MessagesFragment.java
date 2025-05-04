package com.example.znakomstva;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MessagesAdapter messagesAdapter;
    private List<Message> messageList;

    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Инициализация списка сообщений
        messageList = new ArrayList<>();
        loadMessages();

        messagesAdapter = new MessagesAdapter(messageList);
        recyclerView.setAdapter(messagesAdapter);

        return view;
    }

    private void loadMessages() {
        messageList.add(new Message("Мария", "Привет, познакомимся?", R.drawable.user_ava));

    }
}