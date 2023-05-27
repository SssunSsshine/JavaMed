package com.vsu.servlet.product;

import com.vsu.entity.Product;
import com.vsu.entity.User;
import com.vsu.repository.ProductRepo;
import com.vsu.repository.ConnectionFactory;
import com.vsu.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/product/insert")
public class InsertProduct extends HttpServlet {
    private static final String JSP_PATH = "/jsp/";
    private ProductService productService;

    @Override
    public void init(ServletConfig config) {
        productService = new ProductService(new ProductRepo(new ConnectionFactory()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        String sPrice = req.getParameter("price");
        BigDecimal price;
        try {
            price = new BigDecimal(sPrice);
        } catch (NumberFormatException e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "product-form.jsp");
            req.setAttribute("error", "Bad price");
            req.setAttribute("name", name);
            req.setAttribute("price", sPrice);
            dispatcher.forward(req, resp);
            return;
        }
        User user = (User) session.getAttribute("user");
        try {
            productService.insertProduct(new Product(name, price, user.getId()));
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/user/page");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/user/page");
    }
}
