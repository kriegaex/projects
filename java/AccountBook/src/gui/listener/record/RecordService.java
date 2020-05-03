package gui.listener.record;

import bean.Category;
import bean.Record;
import dao.CategoryDAO;
import dao.RecordDAO;
import gui.listener.Category.CategoryService;

import javax.swing.*;
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
