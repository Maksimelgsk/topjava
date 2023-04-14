package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.InMemoryMealStorage;
import ru.javawebinar.topjava.storage.MealStorage;
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
    private static final String INSERT_OR_EDIT = "/meal.jsp";
    private static final String LIST_MEAL = "/meals.jsp";
    private MealStorage mealStorage;

    @Override
    public void init() {
        mealStorage = new InMemoryMealStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Start doGet Method");
        String action = request.getParameter("action");
        String forward;
        if (action == null) {
            action = "listMeal";
        }
        switch (action) {
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                if (mealStorage.delete(id)) {
                    log.info("Meal with id {} was deleted", id);
                }
                response.sendRedirect("meals");
                return;
            }
            case "update":
            case "create": {
                forward = INSERT_OR_EDIT;
                String id = request.getParameter("id");
                final Meal meal = id == null ? new Meal(LocalDateTime.now(), "", 1000) : mealStorage.get(Integer.parseInt(id));
                request.setAttribute("meal", meal);
                break;
            }
            default: {
                forward = LIST_MEAL;
                log.info("getAll");
                request.setAttribute("meals", MealsUtil.getWithExceeded(mealStorage.getAll(), CALORIES_PER_DAY));
                break;
            }
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("Start doPost method");
        String id = request.getParameter("id");
        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        log.info(meal.isNew() ? "create {}" : "update {}", meal);
        mealStorage.save(meal);
        response.sendRedirect("meals");
    }
}
