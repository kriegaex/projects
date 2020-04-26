package dao;

import bean.DBUtil;
import bean.DateUtil;
import bean.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDAO {
    public int getTotal(){
        int total = 0;
        try(Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement()){
            String sql = "select count(*) from record";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Record record){
        String sql = "insert into record values(null, ?, ?, ?, ?)";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, record.getSpend());
            statement.setInt(2, record.getCategoryID());
            statement.setDate(3, DateUtil.util2sql(record.getDate()));
            statement.setString(4, record.getComment());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                int id = rs.getInt(1);
                record.setID(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Record record){
        String sql = "update record set spend = ?, cid = ?, date = ?, comment = ? where id = ?";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, record.getSpend());
            statement.setInt(2, record.getCategoryID());
            statement.setDate(3, DateUtil.util2sql(record.getDate()));
            statement.setString(4, record.getComment());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Record record) {
        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "delete from record where id = " + record.getID();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * GET a Record object with provided ID
     * @param id
     * @return
     */
    public Record get(int id) {
        Record record = null;
        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "select * from record where id = " + id;
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                record = new Record();
                record.setID(id);
                record.setSpend(rs.getInt(2));
                record.setCategoryID(rs.getInt(3));
                record.setDate(rs.getDate(4));
                record.setComment(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    /**
     * Overide next method, return a list of record objects with given start id and final id
     * @return
     */
    public List<Record> list(){
        return list(0, Short.MAX_VALUE);
    }

    public List<Record> list(int startID, int endID) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record order by id desc limit ?, ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, startID);
            statement.setInt(2, endID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Record record = new Record();
                record.setID(rs.getInt(1));
                record.setSpend(rs.getInt(2));
                record.setCategoryID(rs.getInt(3));
                record.setDate(rs.getDate(4));
                record.setComment(rs.getString(5));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    /**
     * List all the records happen in today
     * @return
     */
    public List<Record> listToday(){
        return list(DateUtil.today());
    }

    public List<Record> list(java.util.Date date){
        List<Record> records = new ArrayList<>();
        try(Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "select * from record where date = " + DateUtil.util2sql(date);
            ResultSet rs =statement.executeQuery(sql);
            while (rs.next()) {
                Record record = new Record();
                record.setID(rs.getInt(1));
                record.setSpend(rs.getInt(2));
                record.setCategoryID(rs.getInt(3));
                record.setDate(rs.getDate(4));
                record.setComment(rs.getString(5));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  records;
    }

    /**
     * Override list, list all the records happen between two given dates
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Record> list(java.util.Date startDate, java.util.Date endDate){
        List<Record> records = new ArrayList<>();
        String sql = "select * from record where date >= ? and date <= ?";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, DateUtil.util2sql(startDate));
            statement.setDate(2, DateUtil.util2sql(endDate));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Record record = new Record();
                record.setID(rs.getInt(1));
                record.setSpend(rs.getInt(2));
                record.setCategoryID(rs.getInt(3));
                record.setDate(rs.getDate(4));
                record.setComment(rs.getString(5));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  records;
    }

    /**
     * List all the records happen in this month
     * @return
     */
    public List<Record> listThisMont(){
        return list(DateUtil.monthBegin(), DateUtil.monthEnd());
    }
}
