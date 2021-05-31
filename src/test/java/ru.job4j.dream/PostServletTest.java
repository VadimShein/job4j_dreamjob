package ru.job4j.dream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.servlet.PostServlet;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PostServletTest {
    @Test
    public void whenPostServletDoPost() throws ServletException, IOException {
        PowerMockito.mockStatic(PsqlStore.class);
        when(PsqlStore.instOf()).thenReturn(MemStore.instOf());
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("newUser");
        when(req.getParameter("description")).thenReturn("newDescription");
        new PostServlet().doPost(req, resp);
        assertThat(MemStore.instOf().findByIdPost(5).getName(), is("newUser"));
    }

    @Test
    public void whenPostServletDoGet() throws ServletException, IOException {
        PowerMockito.mockStatic(PsqlStore.class);
        when(PsqlStore.instOf()).thenReturn(MemStore.instOf());

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpSession sessionMock = mock(HttpSession.class);
        when(req.getSession()).thenReturn(sessionMock);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getParameter("user")).thenReturn("user");
        when(req.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        new PostServlet().doGet(req, resp);
        verify(req, Mockito.times(1)).getRequestDispatcher("posts.jsp");
    }
}
