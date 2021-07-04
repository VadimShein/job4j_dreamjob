package ru.job4j.dream.servlet;

import org.json.JSONObject;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;


public class CityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Map<Integer, String> cities = PsqlStore.instOf().findCity();

        JSONObject jsonObj = new JSONObject();
        for (Map.Entry<Integer, String> city : cities.entrySet()) {
            int key = city.getKey();
            String value = city.getValue();
            jsonObj.put(Integer.toString(key), value);
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
        writer.println(jsonObj.toString());
    }
}
