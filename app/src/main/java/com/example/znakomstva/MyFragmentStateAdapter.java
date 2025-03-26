package com.example.znakomstva;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

public class MyFragmentStateAdapter extends FragmentStateAdapter {

    private ArrayList<Uri> imageUris;
    private ArrayList<String> textItems;

    public MyFragmentStateAdapter(ChoosePartnerFragment fa, ArrayList<Uri> imageUris, ArrayList<String> textItems) {
        super(fa);
        this.imageUris = imageUris;
        this.textItems = textItems;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PartnerSelectionFragment(); // Передаем данные
            case 1:
                return new MessagesFragment(); // Фрагмент для сообщений
            case 2:
                Log.d("MyFragmentStateAdapter", "Image URIs: " + imageUris);
                Log.d("MyFragmentStateAdapter", "Text Items: " + textItems);
                return ProfileFragment.newInstance(imageUris, textItems); // Фрагмент для профиля
            default:
                return new PartnerSelectionFragment(); // По умолчанию
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Количество вкладок
    }
}