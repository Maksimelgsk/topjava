package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealStorageList implements MealStorage {
    private static final List<Meal> mealStorage = new CopyOnWriteArrayList<>();
    private static final AtomicInteger counter = new AtomicInteger();

    {
        add(new Meal(LocalDateTime.of(2023, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(LocalDateTime.of(2023, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2023, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        add(new Meal(LocalDateTime.of(2023, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        add(new Meal(LocalDateTime.of(2023, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2023, Month.JANUARY, 31, 13, 0), "Обед", 500));
        add(new Meal(LocalDateTime.of(2023, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public void add(Meal meal) {
        meal.setId(counter.incrementAndGet());
        mealStorage.add(meal);
    }

    @Override
    public void update(Meal meal) {
        Meal indexToDelete = get(meal.getId());
        if (indexToDelete != null) {
            int index = mealStorage.indexOf(indexToDelete);
            mealStorage.set(index, meal);
        }
    }

    @Override
    public void delete(int id) {
        Meal meal = get(id);
        if (meal != null) mealStorage.remove(meal);
    }

    @Override
    public Meal get(int id) {
        for (Meal meal : mealStorage) {
            if (meal.getId() == id) return meal;
        }
        return null;
    }

    @Override
    public List<Meal> getAll() {
        return mealStorage;
    }
}
