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
import java.util.List;

@WebServlet("/product/show")
public class ShowAllProducts extends HttpServlet {
    public static final String JSP_PATH = "/jsp/";
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = new ProductService(new ProductRepo(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Product> productList = productService.selectAllByUserId(user.getId());
        RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "products.jsp");
        req.setAttribute("products", productList);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
