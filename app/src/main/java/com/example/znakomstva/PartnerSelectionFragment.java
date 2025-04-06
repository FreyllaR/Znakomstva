package com.example.znakomstva;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class PartnerSelectionFragment extends Fragment {

    private TextView descriptionText;
    private ViewPager2 viewPager;
    private FusedLocationProviderClient fusedLocationClient;

    private String[] femaleNames = {
            "Анна", "Мария", "Екатерина", "Ольга", "Татьяна",
            "Светлана", "Дарья", "Анастасия", "Ксения", "Юлия",
            "Елена", "Ирина", "Наталья", "Александра", "Маргарита",
            "Виктория", "Кристина", "София", "Полина", "Анастасия",
            "Елизавета", "Диана", "Тамара", "Людмила", "Валентина",
            "Нина", "Светлана", "Галина", "Роксана", "Алина",
            "Кира", "Зоя", "Лариса", "Татьяна", "Яна"
    };

    public PartnerSelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partner_selection, container, false);

        descriptionText = view.findViewById(R.id.descriptionText);
        viewPager = view.findViewById(R.id.viewPager);

        // Установка адаптера для ViewPager2 с перемешанными изображениями
        viewPager.setAdapter(new ImageSliderAdapter(getActivity(), getShuffledImageResIds()));

        // Генерация случайной информации
        generateRandomDescription();

        // Устанавливаем местоположение для первой анкеты
        setLocationForFirstPartner();

        // Обработка нажатия кнопки "like"
        ImageButton likeButton = view.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(v -> {
            generateRandomDescription();
            viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % 40, true);
            getLocation(); // Получаем расстояние до нового партнера
        });

        // Обработка нажатия кнопки "dislike"
        ImageButton dislikeButton = view.findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(v -> {
            generateRandomDescription();
            viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % 40, true);
            getLocation(); // Получаем расстояние до нового партнера
        });

        return view;
    }

    private void generateRandomDescription() {
        Random random = new Random();

        // Генерация случайного женского имени
        String randomName = femaleNames[random.nextInt(femaleNames.length)];

        // Генерация случайного возраста от 18 до 22
        int randomAge = random.nextInt(5) + 18;

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
            descriptionBuilder.append(item).append("\n\n");
        }

        // Установка текста в descriptionText
        descriptionText.setText(descriptionBuilder.toString());
    }

    private List<Integer> getShuffledImageResIds() {
        List<Integer> imageResIds = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            int imageId = getResources().getIdentifier("image_" + i, "drawable", getActivity().getPackageName());
            imageResIds.add(imageId);
        }
        Collections.shuffle(imageResIds); // Перемешиваем изображения
        return imageResIds;
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Запрашиваем разрешение
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            // Разрешение уже предоставлено, получаем местоположение
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            double partnerLatitude;
                            double partnerLongitude;
                            String cityName;
                            float distanceInMeters;

                            // Устанавливаем известные координаты для первой девушки
                            if (viewPager.getCurrentItem() == 0) {
                                partnerLatitude = 55.9178; // Примерные координаты Мытищ
                                partnerLongitude = 37.6543;
                                cityName = "Мытищи"; // Устанавливаем город по умолчанию
                            } else {
                                if (location != null) {
                                    double userLatitude = location.getLatitude();
                                    double userLongitude = location.getLongitude();

                                    // Генерация случайного местоположения партнера
                                    partnerLatitude = userLatitude + (Math.random() - 0.5) * 0.1; // Случайное смещение
                                    partnerLongitude = userLongitude + (Math.random() - 0.5) * 0.1; // Случайное смещение

                                    // Получение города по координатам
                                    cityName = getCityName(partnerLatitude, partnerLongitude);
                                } else {
                                    // Если местоположение неизвестно, используем координаты Мытищ
                                    partnerLatitude = 55.9178; // Примерные координаты Мытищ
                                    partnerLongitude = 37.6543;
                                    cityName = "Мытищи"; // Устанавливаем город по умолчанию
                                }
                            }

                            // Вычисление расстояния
                            Location partnerLocation = new Location("");
                            partnerLocation.setLatitude(partnerLatitude);
                            partnerLocation.setLongitude(partnerLongitude);
                            distanceInMeters = location != null ? location.distanceTo(partnerLocation) : 0;

                            // Добавляем информацию о городе и расстоянии
                            String distanceText = distanceInMeters > 1000
                                    ? String.format("Город партнера: %s (на расстоянии %.2f км)", cityName, distanceInMeters / 1000)
                                    : String.format("Город партнера: %s (на расстоянии %.2f метров)", cityName, distanceInMeters);
                            descriptionText.append("\n" + distanceText); // Добавляем город и расстояние в описание
                        }
                    });
        }
    }

    private String getCityName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<android.location.Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                // Проверяем, есть ли название города, если нет, берем ближайшее населённое место
                String locality = addresses.get(0).getLocality();
                if (locality != null) {
                    return locality; // Возвращаем название города
                } else {
                    // Если нет названия города, возвращаем ближайшее населённое место
                    String subLocality = addresses.get(0).getSubLocality();
                    if (subLocality != null) {
                        return subLocality; // Возвращаем название ближайшего населённого места
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Мытищи"; // Если не удалось получить город
    }

    private void setLocationForFirstPartner() {
        // Устанавливаем известные координаты Мытищ
        final double partnerLatitude = 55.9178; // Примерные координаты Мытищ
        final double partnerLongitude = 37.6543;
        final String cityName = "Мытищи"; // Устанавливаем город по умолчанию

        // Создаем объект Location для фиксированного местоположения
        Location partnerLocation = new Location("");
        partnerLocation.setLatitude(partnerLatitude);
        partnerLocation.setLongitude(partnerLongitude);

        // Получаем текущее местоположение пользователя
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                // Вычисляем расстояние до Мытищ
                                float distanceInMeters = location.distanceTo(partnerLocation);
                                String distanceText = String.format("Город партнера: %s (на расстоянии %.2f км)", cityName, distanceInMeters / 1000);
                                descriptionText.append("\n" + distanceText); // Добавляем город и расстояние в описание
                            } else {
                                // Если местоположение пользователя недоступно, устанавливаем расстояние в 0
                                String distanceText = String.format("Город партнера: %s (на расстоянии %.2f км)", cityName, 0.0);
                                descriptionText.append("\n" + distanceText); // Добавляем город и расстояние в описание
                            }
                        }
                    });
        } else {
            // Если разрешение не предоставлено, устанавливаем расстояние в 0
            String distanceText = String.format("Город партнера: %s (на расстоянии %.2f км)", cityName, 0.0);
            descriptionText.append("\n" + distanceText); // Добавляем город и расстояние в описание
        }
    }
}