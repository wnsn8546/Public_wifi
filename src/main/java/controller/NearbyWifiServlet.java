package controller;

import model.DAO;
import model.HistoryDAO;
import model.WifiVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.List;


@WebServlet("/NearbyWifiServlet")
public class NearbyWifiServlet extends HttpServlet {

    private final DAO dao;
    private final HistoryDAO historyDAO;

    public NearbyWifiServlet(){
        this.dao = new DAO();
        this.historyDAO = new HistoryDAO();
    }

    @Override
    protected void service(HttpServletRequest request , HttpServletResponse response) throws
            IOException, HTTPException, ServletException {

        Double latDouble = Double.valueOf( request.getParameter("latitude-input"));
        Double lntDouble = Double.valueOf( request.getParameter("longitude-input"));

        List<WifiVO> searchList =  dao.search(latDouble,lntDouble);

        historyDAO.save(latDouble,lntDouble);

        request.setAttribute("searchList",searchList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
        requestDispatcher.forward(request,response);

    }
}
