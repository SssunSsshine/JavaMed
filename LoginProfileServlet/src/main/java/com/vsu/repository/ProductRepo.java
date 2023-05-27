package com.vsu.repository;

import com.vsu.entity.Product;
import com.vsu.exception.DBException;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM product WHERE id_product = ?";
    private static final String SELECT_ALL_BY_USER_ID_QUERY = "SELECT * FROM product WHERE id_profile = ?";
    private static final String INSERT_QUERY = "INSERT INTO product(name_product, price, id_profile) VALUES (?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM product WHERE id_product = ?";
    private static final String UPDATE_QUERY = "UPDATE product SET name_product=?, price=?, id_profile=? " + "WHERE id_product = ?";
    private final ConnectionFactory connectionFactory;

    public ProductRepo(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Product selectById(Long id) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getProduct(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<Product> selectAllByUserId(Long idUser) {
        try (Connection connection = connectionFactory.getConnection()) {
            List<Product> productList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_USER_ID_QUERY);
            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                productList.add(getProduct(resultSet));
            }
            return productList;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public int insert(Product product) {
        int countUpdate;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            setProductParamsToStatement(product, statement);
            countUpdate = statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getLong(1));
                } else {
                    throw new DBException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    public int deleteById(Long id) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, id);
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    public int updateByID(Product product) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            setProductParamsToStatement(product, statement);
            statement.setLong(4, product.getId());
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    private void setProductParamsToStatement(Product product, PreparedStatement statement) throws SQLException {
        statement.setString(1, product.getName());
        statement.setBigDecimal(2, product.getPrice());
        statement.setLong(3, product.getIdProfile());
    }

    @NotNull
    private Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id_product"));
        product.setName(resultSet.getString("name_product"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setIdProfile(resultSet.getLong("id_profile"));
        return product;
    }
}
