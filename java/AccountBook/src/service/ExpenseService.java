package service;

import bean.DateUtil;
import bean.Record;
import dao.RecordDAO;

import java.util.Date;
import java.util.List;

public class ExpenseService {
    public int getMonthSpend() {
        return monthSpend;
    }

    public int getTodaySpend() {
        return todaySpend;
    }

    public double getAvgSpendPerDay() {
        return avgSpendPerDay;
    }

    public int getMonthAvailable() {
        return monthAvailable;
    }

    public double getDayAvgAvailable() {
        return dayAvgAvailable;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public int getTotalSpend() {
        return totalSpend;
    }

    public int getUsagepecentage() {
        return usagepecentage;
    }

    private int usagepecentage;
    private int totalSpend;
    private int monthSpend;
    private int todaySpend;
    private double avgSpendPerDay;
    private int monthAvailable;
    private double dayAvgAvailable;
    private int timeLeft;
    private int monthBudget;

    public boolean overBudget(){
        return monthBudget < monthSpend;
    }

    public ExpenseService(){
        RecordDAO recordDAO = new RecordDAO();

        List<Record> totalRecordList = recordDAO.list();
        for(Record record : totalRecordList){
            totalSpend += record.getSpend();
        }

        List<Record> recordList = recordDAO.listThisMont();
        for (Record record : recordList){
            monthSpend += record.getSpend();
        }

        List<Record> recordTodayList = recordDAO.list(new Date());
        for (Record record  : recordTodayList){
            todaySpend += record.getSpend();
        }

        avgSpendPerDay = monthSpend / DateUtil.daysPassInMonth();

        monthBudget = new ConfigService().getBudget();
        monthAvailable = monthBudget - monthSpend;

        dayAvgAvailable = monthBudget / DateUtil.daysInMonth() - todaySpend;

        timeLeft = DateUtil.daysLeftInMonth();

        usagepecentage = monthSpend * 100 / monthBudget;
    }
}
