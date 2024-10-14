package org.example.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.DAO.ProductDAO;
import org.example.model.Product;
import org.example.utils.HibernateUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    public ProductServlet() {
    }

    public ProductServlet(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void init() throws ServletException {
        this.productDAO = new ProductDAO();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fetch and display product list
        List<Product> productList = productDAO.getAllProducts();
        req.setAttribute("products", productList);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null && action.equals("delete")) {
            // Handle delete functionality
            handleDelete(req, resp);
        } else {
            // Handle add functionality
            handleAdd(req, resp);
        }
    }

    private void handleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String productName = req.getParameter("productName");
        String priceString = req.getParameter("priceProduct");

        int price = 0;
        if (priceString != null && !priceString.isEmpty()) {
            try {
                price = Integer.parseInt(priceString);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price value: " + priceString);
            }
        }

        Product product = productDAO.getProductByProductName(productName);

        if (productName == null || price == 0) {
            req.setAttribute("error", "Please enter this field");
        } else if (product != null) {
            req.setAttribute("error", "This name already exists, please rename it");
        } else {
            product = new Product();
            product.setName(productName);
            product.setPrice(price);
            productDAO.add(product);
            req.setAttribute("success", "Add product successfully");
        }

        // Refresh the product list and forward to index.jsp
        doGet(req, resp);
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        if (idString != null && !idString.isEmpty()) {
            try {
                int productId = Integer.parseInt(idString);
                productDAO.deleteByID(productId);
                req.setAttribute("success", "Product deleted successfully");
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Invalid product ID");
            }
        } else {
            req.setAttribute("error", "Product ID is required for deletion");
        }

        // Refresh the product list and forward to index.jsp
        doGet(req, resp);
    }
}
