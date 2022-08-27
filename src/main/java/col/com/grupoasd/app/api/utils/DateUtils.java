package col.com.grupoasd.app.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DateUtils {

    @Value("${jms.jwt.timezone}")
    private String TIMEZONE;

    private SimpleDateFormat simpleDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
        return simpleDateFormat;
    }

    public String getDateString() {
        return simpleDateFormat().format(new Date());
    }

    public Long getDateMillis() {
        Date strNow = new Date();
        try {
            strNow = simpleDateFormat().parse(simpleDateFormat().format(new Date()));
        } catch (ParseException e) {
        }
        return strNow.getTime();
    }

}
