package bean;

import java.util.Date;

public class Record {
    private int id;
    private int spend;
    private int categoryID;
    private Date date;
    private String comment;

    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
    public int getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getSpend() {
        return spend;
    }
    public void setSpend(int spend) {
        this.spend = spend;
    }
}
