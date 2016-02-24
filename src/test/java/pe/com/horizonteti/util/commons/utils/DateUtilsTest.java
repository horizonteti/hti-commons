package pe.com.horizonteti.util.commons.utils;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

  @Test
  public void dateFormatShouldFormatDateCorrectly () {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DATE, 29);
    calendar.set(Calendar.MONTH, 1);
    calendar.set(Calendar.YEAR, 2016);
    Date date = calendar.getTime();
    
    String formatedDate = DateUtils.dateFormat(date, "dd/MM/yyyy");
    
    Assert.assertEquals("29/02/2016", formatedDate);
  }
  
  @Test
  public void dateFormatShouldReturnNullWhenPassedNull () {
    Date date = null;
    String formatedDate = DateUtils.dateFormat(date, "dd/MM/yyyy");
    
    Assert.assertNull(formatedDate);
  }
  
  @Test
  public void removeTimeShouldRemoveTime () {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DATE, 29);
    calendar.set(Calendar.MONTH, 1);
    calendar.set(Calendar.YEAR, 2016);
    calendar.set(Calendar.HOUR, 10);
    
    Date date = DateUtils.removeTime(calendar.getTime());
    Assert.assertNotEquals(date, calendar.getTime());
  }
}
