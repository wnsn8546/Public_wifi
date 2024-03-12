package controller;

import model.HistoryDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;

@WebServlet("/removeHistory")
public class HistoryRemoveServlet extends HttpServlet {
    private HistoryDAO historyDAO;
    public HistoryRemoveServlet(){
        historyDAO = new HistoryDAO();
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws
            IOException, HTTPException {
        int removeId = Integer.parseInt(request.getParameter("deleteIdnumber"));
        historyDAO.removeOneData(removeId);
    }
}
