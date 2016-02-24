package pe.com.horizonteti.util.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
  public static final String DATE_ONLY_FORMAT = "dd/MM/yyyy";
  public static final String DATE_TIME_AMPM_FORMAT = "dd/MM/yyyy hh:mm a";

  public static String dateFormat(Date date, String format) {
    if (date == null) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
  }

  public static Date removeTime(Date date) {
    if (date == null) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_ONLY_FORMAT);
    try {
      return sdf.parse(sdf.format(date));
    } catch (ParseException ex) {
    }
    return date;
  }

  public static Date sumarHoras(Date date, int numHoras) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    calendar.add(Calendar.HOUR, numHoras);

    return calendar.getTime();
  }

  public static Date sumarMinutos(Date date, int numMinutos) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    calendar.add(Calendar.MINUTE, numMinutos);

    return calendar.getTime();
  }

  public static Date sumarDias(Date date, int numDias) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    calendar.add(Calendar.DATE, numDias);

    return calendar.getTime();
  }

  public static Date getLastTimeOfDay(Date fecha) {
    Calendar cFecha = Calendar.getInstance();
    cFecha.setTime(removeTime(fecha));
    cFecha.add(Calendar.DAY_OF_MONTH, 1);
    cFecha.add(Calendar.SECOND, -1);

    return cFecha.getTime();
  }

  public static long getDaysBetweenDates(Date fechaInicial, Date fechaFinal) {
    if (fechaInicial.after(fechaFinal)) {
      return 0;
    }

    long diff = fechaFinal.getTime() - fechaInicial.getTime();
    long numDias = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    return numDias;
  }
}
