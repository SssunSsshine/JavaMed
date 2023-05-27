package com.vsu.servlet.product;

import com.vsu.entity.Product;
import com.vsu.repository.ProductRepo;
import com.vsu.repository.ConnectionFactory;
import com.vsu.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product/edit")
public class EditProduct extends HttpServlet {
    private static final String JSP_PATH = "/jsp/";
    private ProductService productService;

    @Override
    public void init(ServletConfig config) {
        productService = new ProductService(new ProductRepo(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        Product existingProduct;
        try {
            existingProduct = productService.selectById(id);
            RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "product-form.jsp");
            req.setAttribute("product", existingProduct);
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}