package org.example;

import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    public Gson get_gson() {
        return _gson;
    }

    public void set_gson(Gson _gson) {
        this._gson = _gson;
    }


    private static class ResponseDTO {

        private Integer errorCode;
        private String message;
        private Object data;

        public ResponseDTO(Integer errorCode, String message, Object data) {
            this.errorCode = errorCode;
            this.message = message;
            this.data = data;
        }

        public ResponseDTO() {
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    private static class Product {
        private int id;

        private String name;

        private double price;

        public Product() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return Math.toIntExact(id);
        }

        public void setId(int id) {
            this.id = (Integer) id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Product(int id, String name, double price) {
            this.name = name;
            this.id = (Integer) id;
            this.price = price;
        }
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }

    private Gson _gson = null;
    private Map<Integer, Product> products;

    public ProductServlet() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("id");
        ResponseDTO responseDTO;
        if (key == null) {
            responseDTO = new ResponseDTO(0, "All product", this.products);
            sendAsJson(resp, responseDTO);
            return;
        }
        int productId = Integer.parseInt(key);
        if (!this.products.containsKey(productId)) {

            responseDTO = new ResponseDTO(HttpServletResponse.SC_NOT_FOUND, "Product not found", this.products);
            sendAsJson(resp, responseDTO);
            return;
        }
        responseDTO = new ResponseDTO(0, "Product is found", this.products);
        sendAsJson(resp, responseDTO);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = this._gson.fromJson(req.getReader(), Product.class);
        this.products.put(product.getId(), product);
        ResponseDTO responseDTO = new ResponseDTO(0, "Product added", this.products);
        sendAsJson(resp, responseDTO);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = this._gson.fromJson(req.getReader(), Product.class);
        ResponseDTO responseDTO;
        if (product.getId() == null) {
            responseDTO = new ResponseDTO(HttpServletResponse.SC_NOT_FOUND, "Product is not found", this.products);
            sendAsJson(resp, responseDTO);
            return;
        }
        this.products.put(product.getId(), product);
        responseDTO = new ResponseDTO(0, "Product updated", this.products);
        sendAsJson(resp, responseDTO);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = this._gson.fromJson(req.getReader(), Product.class);
        ResponseDTO responseDTO;
        product.getId();
        if (!this.products.containsKey(product.getId())) {
            responseDTO = new ResponseDTO(HttpServletResponse.SC_NOT_FOUND, "Product is not found", this.products);
            sendAsJson(resp, responseDTO);
            return;
        }
        this.products.remove(product.getId());
        responseDTO = new ResponseDTO(0, "Product deleted", this.products);
        sendAsJson(resp, responseDTO);
    }

    private void sendAsJson(HttpServletResponse response, Object object) throws IOException {

        response.setContentType("application/json");
        String res = _gson.toJson(object);
        PrintWriter out = response.getWriter();
        out.println(res);
        out.flush();
    }

    @Override
    public void init() throws ServletException {
        this._gson = new Gson();
        this.products = new HashMap<Integer, Product>();
        products.put(1, new Product(1, "Iphone", 1000));
        products.put(2, new Product(2, "Iphone13", 2000));
        products.put(3, new Product(3, "Iphone14", 3000));
        products.put(4, new Product(4, "Iphone15", 4000));
    }
}
