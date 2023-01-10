package helperCode;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class datetimeHellper {
    public Date convertString2SQLDate(String date) {
        try {
            java.util.Date date1 = new SimpleDateFormat("MM-dd-yyyy").parse(date);
            Date date2 = new Date(date1.getTime());
            return date2;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertTimeStam2String(java.sql.Timestamp timestamp) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat result = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            SimpleDateFormat hour_min = new SimpleDateFormat("hh:mm");
            java.util.Date cr_reTime = new java.util.Date(timestamp.getTime());
            java.util.Date now = new java.util.Date();
            if (sdf.format(cr_reTime).equals(sdf.format(now))) {
                return "Today <br>" + hour_min.format(cr_reTime);
            } else if (sdf.format(cr_reTime).equals(sdf.format(cal.getTime()))) {
                return "Yesterday <br>" + hour_min.format(cr_reTime);
            } else {
                return result.format(cr_reTime);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
