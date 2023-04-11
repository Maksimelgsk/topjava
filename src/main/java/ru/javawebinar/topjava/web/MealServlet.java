package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.storage.MealStorageList;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final int CALORIES_PER_DAY = 2000;
    private final MealStorage mealStorage = new MealStorageList();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            log.info("get all");
            request.setAttribute("meals", MealsUtil.getWithExceeded(mealStorage.getAll(), CALORIES_PER_DAY));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        } else if (action.equals("add")) {
            request.setAttribute("meal", null);
            request.getRequestDispatcher("/meal.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = mealStorage.get(id);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/meal.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            log.debug("delete with id" + id);
            log.debug(("size : " + mealStorage.getAll().size()));
            mealStorage.delete(id);
            response.sendRedirect("meals");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        LocalDateTime date = LocalDateTime.parse(request.getParameter("datetime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        if (action.equals("create")) {
            Meal meal = new Meal(date, description, calories);
            log.info("create {}", meal);
            mealStorage.add(meal);
        } else if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = new Meal(id, date, description, calories);
            log.info("update {}", meal);
            mealStorage.update(meal);
        }
        response.sendRedirect("meals");
    }
}
