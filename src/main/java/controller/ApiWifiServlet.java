package controller;

import api.WifiApiJson;
import api.RowInfoDto;
import api.WifiDto;
import model.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.List;

@WebServlet("/ApiWifiServlet")
public class ApiWifiServlet extends HttpServlet {

    WifiApiJson wifiApiService = new WifiApiJson();
    DAO wifiDao = new DAO();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse respons) throws
            IOException, HTTPException {

        try {
            int count = wifiApiService.getTotalPageCount();
            int start = 0;
            int end = 999;

            wifiDao.removeAllData();
            for (int i = 0; i < count; i++) {
                WifiDto wifiDto = wifiApiService.requestApiAndResponseToDto(start, end);
                List<RowInfoDto> rowInfoDtos = wifiDto.getWifiDetails();
                wifiDao.saveAllWifiDetailList(rowInfoDtos);
                start += 1000;
                end += 1000;
            }
            request.setAttribute("apiWifiTotalCount",wifiApiService.getTotalCount());

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("load-wifi.jsp");
            requestDispatcher.forward(request,respons);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

