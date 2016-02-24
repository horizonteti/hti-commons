package pe.com.horizonteti.util.commons.reporting.exceptions;

import net.sf.jasperreports.engine.JRException;

/**
 * Clase de excepción de aplicación para errores de reporte
 * @author Jorge
 *
 */
public class ReportGenerationException extends Exception {

  public ReportGenerationException(JRException e) {
    super(e);
  }

}
