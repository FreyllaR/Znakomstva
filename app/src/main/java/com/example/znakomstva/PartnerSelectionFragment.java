package com.example.znakomstva;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PartnerSelectionFragment extends Fragment {

    private TextView descriptionText;
    private ViewPager2 viewPager;

    // Массивы с женскими именами
    private String[] femaleNames = {
            "Анна", "Мария", "Екатерина", "Ольга", "Татьяна",
            "Светлана", "Дарья", "Анастасия", "Ксения", "Юлия"
    };

    public PartnerSelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partner_selection, container, false);

        descriptionText = view.findViewById(R.id.descriptionText);
        viewPager = view.findViewById(R.id.viewPager);

        // Установка адаптера для ViewPager2
        viewPager.setAdapter(new ImageSliderAdapter(getActivity(), getImageResIds()));

        // Генерация случайной информации
        generateRandomDescription();

        // Обработка нажатия кнопки
        ImageButton likeButton = view.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Генерация новой случайной информации
                generateRandomDescription();
                // Обновление ViewPager2
                viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % 10, true); // Циклический переход
            }
        });

        // Обработка нажатия кнопки "dislike"
        ImageButton dislikeButton = view.findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Генерация новой случайной информации
                generateRandomDescription();
                // Обновление ViewPager2
                viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % 10, true); // Циклический переход
            }
        });

        return view;
    }

    private void generateRandomDescription() {
        Random random = new Random();

        // Генерация случайного женского имени
        String randomName = femaleNames[random.nextInt(femaleNames.length)];

        // Генерация случайного возраста от 18 до 22
        int randomAge = random.nextInt(5) + 18; // 5 - это диапазон от 18 до 22

        // Получение массивов из ресурсов
        String[] aboutMe = getResources().getStringArray(R.array.about_me_array);
        String[] hobbies = getResources().getStringArray(R.array.hobby_array);
        String[] socialLife = getResources().getStringArray(R.array.social_life_array);
        String[] creativity = getResources().getStringArray(R.array.creativity_array);
        String[] activeLifestyle = getResources().getStringArray(R.array.active_lifestyle_array);
        String[] sports = getResources().getStringArray(R.array.sport_array);
        String[] travels = getResources().getStringArray(R.array.travel_array);
        String[] homeTime = getResources().getStringArray(R.array.home_time_array);
        String[] movies = getResources().getStringArray(R.array.movies_array);
        String[] animals = getResources().getStringArray(R.array.animals_array);
        String[] personalGrowth = getResources().getStringArray(R.array.personal_growth_array);
        String[] alcoholSmoking = getResources().getStringArray(R.array.alcohol_smoking_array);
        String[] sportyBody = getResources().getStringArray(R.array.sporty_body_array);

        List<String> textItems = new ArrayList<>();
        textItems.add(randomName + ", " + randomAge);
        textItems.add("О себе: " + aboutMe[random.nextInt(aboutMe.length)]);
        textItems.add("Хобби: " + hobbies[random.nextInt(hobbies.length)]);
        textItems.add("Социальная жизнь: " + socialLife[random.nextInt(socialLife.length)]);
        textItems.add("Творчество: " + creativity[random.nextInt(creativity.length)]);
        textItems.add("Активный образ жизни: " + activeLifestyle[random.nextInt(activeLifestyle.length)]);
        textItems.add("Спорт: " + sports[random.nextInt(sports.length)]);
        textItems.add("Путешествия: " + travels[random.nextInt(travels.length)]);
        textItems.add("Время дома: " + homeTime[random.nextInt(homeTime.length)]);
        textItems.add("Фильмы и сериалы: " + movies[random.nextInt(movies.length)]);
        textItems.add("Животные: " + animals[random.nextInt(animals.length)]);
        textItems.add("Личное: " + personalGrowth[random.nextInt(personalGrowth.length)]);
        textItems.add("Отношение к алкоголю и курению: " + alcoholSmoking[random.nextInt(alcoholSmoking.length)]);
        textItems.add("Спортивное телосложение: " + sportyBody[random.nextInt(sportyBody.length)]);

        // Объединение всех строк в одну с дополнительными пустыми строками
        StringBuilder descriptionBuilder = new StringBuilder();
        for (String item : textItems) {
            descriptionBuilder.append(item).append("\n\n"); // Добавляем две пустые строки
        }

        // Установка текста в descriptionText
        descriptionText.setText(descriptionBuilder.toString());
    }

    private List<Integer> getImageResIds() {
        List<Integer> imageResIds = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int imageId = getResources().getIdentifier("image_" + i, "drawable", getActivity().getPackageName());
            imageResIds.add(imageId); // Добавляем идентификатор ресурса
        }
        return imageResIds;
    }
}