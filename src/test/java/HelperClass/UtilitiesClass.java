package HelperClass;
import TestBase.TestBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilitiesClass extends TestBase {
    /**
     * getStartDate method is used to get the current date by formate yyyy-MM-dd
     * @Author Arun Kumar
     * @return String current date
     */
    public String getStartDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    /**
     * getEndDate method is used to get the end date where current date month+3
     * @Arun Kumar
     * @return String
     */
    public String getEndDate() {
        String format = "yyyy-MM-dd" ;
        SimpleDateFormat sdf = new SimpleDateFormat(format) ;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 3);
        Date dateAsObjAfterAMonth = cal.getTime() ;
        return sdf.format(dateAsObjAfterAMonth);
    }
}
