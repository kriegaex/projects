package dao;

import bean.Category;
import bean.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public int getTotal(){
        int total = 0;
        try(Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement()){
            String sql = "select count(*) from category";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Category category){
        String sql = "insert into category values(null, ?)";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                int id = rs.getInt(1);
                category.setID(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Category category){
        String sql = "update category set name = ? where id = ?";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            statement.setInt(2, category.getID());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "delete from category where id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * GET a Config object with provided ID
     * @param id
     * @return
     */
    public Category get(int id) {
        Category category = null;
        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "select * from category where id = " + id;
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                category = new Category();
                category.setID(id);
                category.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    /**
     * Overide next method, return a list of config objects with given start id and final id
     * @return
     */
    public List<Category> list(){
        return list(0, Short.MAX_VALUE);
    }

    public List<Category> list(int startID, int endID){
        List<Category> categories = new ArrayList<>();
        String sql = "select * from category order by id desc limit ?, ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, startID);
            statement.setInt(2, endID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Category category = new Category();
                category.setID(rs.getInt(1));
                category.setName(rs.getString(2));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}

