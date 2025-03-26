package com.example.znakomstva;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class ChoosePartnerFragment extends Fragment {

    private ViewPager2 viewPager;
    private MyFragmentStateAdapter adapter;
    private TabLayout tabLayout;

    private ArrayList<Uri> imageUris = new ArrayList<>();
    private ArrayList<String> textItems = new ArrayList<>();

    public ChoosePartnerFragment() {
        // Required empty public constructor
    }

    public static ChoosePartnerFragment newInstance(ArrayList<Uri> imageUris, ArrayList<String> textItems) {
        ChoosePartnerFragment fragment = new ChoosePartnerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("imageUris", imageUris);
        args.putStringArrayList("textItems", textItems);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUris = getArguments().getParcelableArrayList("imageUris");
            textItems = getArguments().getStringArrayList("textItems");
            Log.d("ChoosePartnerFragment", "Image URIs: " + imageUris);
            Log.d("ChoosePartnerFragment", "Text Items: " + textItems);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_partner, container, false);

        // Инициализация элементов интерфейса
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        // Настройка адаптера для ViewPager2
        adapter = new MyFragmentStateAdapter(this, imageUris, textItems);
        viewPager.setAdapter(adapter);

        // Привязка TabLayout к ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Партнеры");
                    tab.setIcon(R.drawable.hearts);
                    break;
                case 1:
                    tab.setText("Сообщения");
                    tab.setIcon(R.drawable.messages);
                    break;
                case 2:
                    tab.setText("Профиль");
                    tab.setIcon(R.drawable.profile_acc);
                    break;
            }
        }).attach();

        return view;
    }
}