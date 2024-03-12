package controller;

import model.HistoryDAO;
import model.HistoryVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.List;

@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet{
    HistoryDAO historyDAO = new HistoryDAO();
    @Override
    protected void service(HttpServletRequest request , HttpServletResponse response) throws
            IOException, HTTPException, ServletException {

        List<HistoryVO> list = historyDAO.HistoryselectAll();
        System.out.println(list);

        request.setAttribute("selectAll",list);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/history.jsp");
        requestDispatcher.forward(request,response);
    }
}
