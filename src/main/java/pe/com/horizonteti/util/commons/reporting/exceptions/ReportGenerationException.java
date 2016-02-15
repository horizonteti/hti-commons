package pe.com.horizonteti.util.commons.reporting.exceptions;

import net.sf.jasperreports.engine.JRException;

public class ReportGenerationException extends Exception {

  public ReportGenerationException(JRException e) {
    super(e);
  }

}
