package helperCode;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


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
}
