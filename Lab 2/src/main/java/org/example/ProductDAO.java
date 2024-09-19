package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product, Integer> {
    private final Connection connection;

    public ProductDAO(String url) throws SQLException {
        this.connection = DriverManager.getConnection(url);
    }
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Integer add(Product item) throws SQLException {
        String sql = "INSERT INTO Product (name, price) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public List<Product> readAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
            }
        }
        return products;
    }

    @Override
    public Product read(Integer id) throws SQLException {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(Product item) throws SQLException {
        String sql = "UPDATE Product SET name = ?, price = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setInt(3, item.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Product WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
