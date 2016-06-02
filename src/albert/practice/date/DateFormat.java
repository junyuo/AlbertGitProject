package albert.practice.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateFormat {
    public static void main(String[] args) {
	Date date = new Date();

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String dateString = dateFormat.format(date);

	log.debug("dateString = " + dateString);
    }
}
