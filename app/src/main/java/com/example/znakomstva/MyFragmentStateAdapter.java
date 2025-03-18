package com.example.znakomstva;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentStateAdapter extends FragmentStateAdapter {

    public MyFragmentStateAdapter(ChoosePartnerFragment fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PartnerSelectionFragment(); // Фрагмент для выбора партнера
            case 1:
                return new MessagesFragment(); // Фрагмент для сообщений
            case 2:
                return new ProfileFragment(); // Фрагмент для профиля
            default:
                return new PartnerSelectionFragment(); // По умолчанию
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Количество вкладок
    }
}