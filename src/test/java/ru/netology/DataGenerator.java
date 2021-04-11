package ru.netology;


import com.github.javafaker.Faker;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String getFutureDate(int n) {
        LocalDate date = LocalDate.now();
        return date.plusDays(n).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String city() {
        String[] cities = {"Абакан", "Анадырь", "Астрахань", "Барнаул", "Белгород", "Благовещенск",
                "Брянск", "Великий Новгород", "Владивосток", "Владикавказ", "Владимир", "Волгоград",
                "Вологда", "Воронеж", "Грозный", "Екатеринбург", "Иваново", "Ижевск", "Иркутск", "Йошкар-Ола",
                "Казань", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Краснодар", "Красноярск",
                "Курган", "Курск", "Кызыл", "Липецк", "Магадан", "Магас", "Майкоп", "Махачкала", "Москва",
                "Нальчик", "Нарьян-Мар", "Нижний Новгород", "Новосибирск", "Омск", "Орёл", "Оренбург", "Пенза",
                "Пермь", "Петрозаводск", "Петропавловск-Камчатский", "Псков", "Ростов-на-Дону", "Рязань", "Салехард",
                "Самара", "Санкт-Петербург", "Саранск", "Саратов", "Севастополь", "Симферополь", "Смоленск",
                "Ставрополь", "Сыктывкар", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Улан-Удэ", "Ульяновск",
                "Уфа", "Хабаровск", "Ханты-Мансийск", "Чебоксары", "Челябинск", "Чита", "Элиста", "Южно-Сахалинск",
                "Якутск", "Ярославль", "Архангельск", "Биробиджан", "Горно-Алтайск", "Мурманск", "Черкесск"};
        return cities[new Random().nextInt(cities.length)];

    }

    public static class FormRequest {
        public FormRequest() {
        }

        public static DataSet clientInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new DataSet(
                    faker.name().lastName() + " " + faker.name().firstName(), city(),
                    faker.phoneNumber().phoneNumber()
            );
        }


    }

}

