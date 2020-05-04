package gui.service;

import bean.DateUtil;
import bean.Record;
import dao.RecordDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * AnalysisService helps to prepare the data for AnalysisPanel
 */
public class AnalysisService {

    public double[] listSpendThisMonth(){
        RecordDAO recordDAO = new RecordDAO();
        List<Record> monthRecordList = recordDAO.listThisMont();
        Calendar date = Calendar.getInstance();
        Date monthBegin = DateUtil.monthBegin();
        date.setTime(monthBegin);
        int daysInMonth = DateUtil.daysInMonth();

        double[] spendList = new double[daysInMonth];

        for (int i = 0; i < daysInMonth; i++) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            Date dateOfEachDay = date.getTime();
            int dailyExpend = 0;
            List<Record> todayList = recordDAO.list(dateOfEachDay);

            for (Record record : todayList){
                dailyExpend += record.getSpend();
            }
            spendList[i] = dailyExpend;
        }
        return spendList;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new AnalysisService().listSpendThisMonth()));
    }
}
