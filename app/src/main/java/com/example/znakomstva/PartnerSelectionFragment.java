package com.example.znakomstva;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PartnerSelectionFragment extends Fragment {

    private ViewPager2 viewPager;
    private ImageSliderAdapter imageAdapter;
    private LinearLayout indicatorLayout;
    private TextView textInfo;

    private ArrayList<Uri> imageUris = new ArrayList<>();
    private ArrayList<String> textItems = new ArrayList<>();

    public PartnerSelectionFragment() {
        // Required empty public constructor
    }

    public static PartnerSelectionFragment newInstance(ArrayList<Uri> imageUris, ArrayList<String> textItems) {
        PartnerSelectionFragment fragment = new PartnerSelectionFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partner_selection, container, false);

        // Инициализация элементов интерфейса
        viewPager = view.findViewById(R.id.viewPager);
        indicatorLayout = view.findViewById(R.id.indicatorLayout);
        textInfo = view.findViewById(R.id.textInfo);

        // Настройка адаптера для ViewPager2
        imageAdapter = new ImageSliderAdapter(getContext(), imageUris, position -> {
            // Обновление индикаторов, если это необходимо
        });
        viewPager.setAdapter(imageAdapter);

        // Отображение текстовой информации
        StringBuilder fullText = new StringBuilder();
        for (String text : textItems) {
            fullText.append(text).append("\n\n");
        }
        textInfo.setText(fullText.toString().trim());
        textInfo.setVisibility(View.VISIBLE);

        // Создание индикаторов для ViewPager2
        createIndicators();

        // Обновление индикаторов при перелистывании
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateIndicators(position);
            }
        });

        return view;
    }

    private void createIndicators() {
        indicatorLayout.removeAllViews();
        for (int i = 0; i < imageUris.size(); i++) {
            View indicator = new View(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(4, 0, 4, 0);
            indicator.setLayoutParams(params);
            indicator.setBackgroundResource(R.drawable.indicator_inactive);
            indicatorLayout.addView(indicator);
        }
        updateIndicators(0); // Установить активный индикатор на первый элемент
    }

    private void updateIndicators(int position) {
        for (int i = 0; i < indicatorLayout.getChildCount(); i++) {
            View indicator = indicatorLayout.getChildAt(i);
            if (i == position) {
                indicator.setBackgroundResource(R.drawable.indicator_active);
            } else {
                indicator.setBackgroundResource(R.drawable.indicator_inactive);
            }
        }
    }
}