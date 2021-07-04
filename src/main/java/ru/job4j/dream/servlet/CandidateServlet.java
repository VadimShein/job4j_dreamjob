package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class CandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if ("delete".equals(req.getParameter("action"))) {
            if (req.getParameter("id") != null) {
                int id = Integer.parseInt(req.getParameter("id"));
                if (PsqlStore.instOf().findByIdCandidate(id).isHasPhoto()) {
                    Files.delete(Paths.get("c:\\images\\" + id + ".JPG"));
                }
                PsqlStore.instOf().deleteCandidate(id);
            }
        } else {
            int cityId = PsqlStore.instOf().findCityId(req.getParameter("city"));
            PsqlStore.instOf().save(
                    new Candidate(
                            Integer.parseInt(req.getParameter("id")),
                            req.getParameter("name"),
                            req.getParameter("description"),
                            new Date(),
                            cityId
                    )
            );
        }
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
