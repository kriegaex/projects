package service;

import bean.Category;
import bean.Record;
import dao.CategoryDAO;
import dao.RecordDAO;

import java.util.Date;

public class RecordService {
    private RecordDAO dao = new RecordDAO();

    public void add(int spend, String category, String payment, String comment, Date date){
        Record record = new Record();
        record.setSpend(spend);
        Category category1 = new CategoryDAO().getByName(category);
        record.setCategoryID(category1.getID());
        record.setPayment(payment);
        record.setDate(date);
        record.setComment(comment);
        dao.add(record);
    }
}