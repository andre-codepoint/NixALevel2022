package com.example.demo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "helloServlet", urlPatterns = "/count")
public class GetCountServlet extends HttpServlet {
    private static Map<String, String> myMap = new ConcurrentHashMap<String, String>();

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private String message;

    @Override
    public void init() {
        message = "GET counter";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        String ipAdress = request.getRemoteAddr();
        String userAgent = request.getHeader("user-agent");
        myMap.put(ipAdress, userAgent);
        for (Map.Entry<String, String> entry : myMap.entrySet()) {
            if (ipAdress.equalsIgnoreCase(ipAdress))
            out.println("<p align=\"center\"><b>" + entry.getKey() + " :: " + entry.getValue() + "</b></p>");
            else
                out.println("<p align=\"center\">" + entry.getKey() + " :: " + entry.getValue() + "</p>");
        }

//            Map<String, String> result = new HashMap<>();
//            Enumeration headerNames = request.getHeaderNames();
//            while (headerNames.hasMoreElements()) {
//                String headerKey = (String) headerNames.nextElement();
//                String headerValue = request.getHeader(headerKey);
//                out.println("<h3 align=\"center\">Request from: "+headerKey+" : "+headerValue+ "</h3>");
//            }

        out.println("</body></html>");
    }

}

