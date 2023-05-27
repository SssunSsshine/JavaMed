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

@WebServlet("/product/update")
public class UpdateProduct extends HttpServlet {
    private static final String JSP_PATH = "/jsp/";
    private ProductService productService;

    @Override
    public void init(ServletConfig config) {
        productService = new ProductService(new ProductRepo(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        BigDecimal price;
        try {
            price = new BigDecimal(req.getParameter("price"));
        } catch (NumberFormatException e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "product-form.jsp");
            req.setAttribute("product", productService.selectById(req.getParameter("id")));
            req.setAttribute("error", "Bad price");
            dispatcher.forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Product product = new Product(id, name, price, user.getId());
        try {
            productService.updateByID(product);
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "product-form.jsp");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/product/show");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

