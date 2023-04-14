package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealStorage {
    Meal add(Meal meal);

    boolean delete(int id);

    Meal get(int id);

    List<Meal> getAll();
}
