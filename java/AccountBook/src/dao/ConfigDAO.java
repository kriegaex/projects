package dao;

import bean.Config;
import bean.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class performs object-relational mapping between config objects and Config table
 */
public class ConfigDAO {
    /**
     * GET the total number of record in the table
     * @return
     */
    public int getTotal() {
        int total = 0;
        try(Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement()){
                String sql = "select count(*) from config";
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()){
                    total = rs.getInt(1);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Config config){
        String sql = "insert into config values(null, ?, ?)";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, config.getKey());
                statement.setString(2, config.getValue());
                statement.execute();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    config.setID(id);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Config config){
        String sql = "update config set key_ = ?, value = ? where id = ?";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, config.getKey());
            statement.setString(2, config.getValue());
            statement.setInt(3, config.getID());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Config config) {
        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "delete from config where id = " + config.getID();
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
    public Config get(int id) {
        Config config = null;
        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement()) {
                String sql = "select * from config where id = " + id;
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()){
                    config = new Config();
                    config.setID(id);
                    config.setKey(rs.getString(2));
                    config.setValue(rs.getString(3));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return config;
    }

    /**
     * Overide next method, return a list of config objects with given start id and final id
     * @return
     */
    public List<Config> list(){
        return list(0, Short.MAX_VALUE);
    }

    public List<Config> list(int startID, int endID){
        List<Config> configs = new ArrayList<>();
        String sql = "select * from config order by id desc limit ?, ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, startID);
                statement.setInt(2, endID);
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    Config config = new Config();
                    config.setID(rs.getInt(1));
                    config.setKey(rs.getString(2));
                    config.setValue(rs.getString(3));
                    configs.add(config);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return configs;
    }

    /**
     * GET a config object with given key string
     * @param key
     * @return
     */
    public Config getByKey(String key){
        Config config = null;
        String sql =  "select * from config where key_ = ? ";
        try (Connection connection = DBUtil.getConnection();
//             Statement statement = connection.createStatement()
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // String sql = "select * from config where key_ = " + key;
            // ResultSet rs = statement.executeQuery(sql);
            preparedStatement.setString(1, key);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                config = new Config();
                config.setID(rs.getInt(1));
                config.setKey(key);
                config.setValue(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return config;
    }
}
