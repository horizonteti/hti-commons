package pe.com.horizonteti.util.commons.reporting.jasperreports;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import pe.com.horizonteti.util.commons.reporting.exceptions.ReportGenerationException;

/**
 * Clase que abstrae el proceso de exportación en PDF y Excel de jasper reprots
 * @author Jorge
 *
 * @param <T> clase de la que se generará la exportación
 */
public abstract class DataListReportExporter<T> {

  private List<T> data;
  private String reportSource;
  private InputStream inputStream;
  private Map params;
  
  /**
   * 
   * @param data lista de información a exportar
   * @param reportSource ruta desde la que se leerá el reporte
   * @param params parametros a pasar al reporte
   */
  public DataListReportExporter(List<T> data, String reportSource, Map params) {
    this.data = data;
    this.reportSource = reportSource;
    this.params = params;
  }
  
  private JasperReport loadReportInputStream() throws JRException {
    inputStream = DataListReportExporter.class.getClassLoader().getResourceAsStream(reportSource);
    return JasperCompileManager.compileReport(inputStream);
  }
  
  /**
   * Carga la fuente de jasper reports desde la cual se generará el reporte, 
   * basándose en data
   * @return DataSource de jasper reports
   */
  protected abstract JRDataSource loadJRDataSource();
  
  /**
   * Compila el reporte con la data
   * 
   * @return Objeto de jasper
   * @throws JRException en caso que ocurra algún error de jasper
   */
  protected JasperPrint getJasperPrint() throws JRException {
    return JasperFillManager.fillReport(loadReportInputStream(), params, loadJRDataSource());
  }
  
  /**
   * Obtiene los bytes de un pdf que representa al reporte lleno con la información
   * {@link #data}
   * @return bytes del reporte en pdf
   * @throws ReportGenerationException en caso no se pueda exportar debido a un error
   * de jasper
   */
  public byte[] getPdfBytes() throws ReportGenerationException {
    JasperPrint print;
    try {
      print = getJasperPrint();
      return JasperExportManager.exportReportToPdf(print);
    } catch (JRException e) {
      throw new ReportGenerationException(e);
    }
  }
  
  /**
   * Obtiene los bytes del reporte representado en Excel en base a una config.
   * básica.
   * @return bytes del reporte en excel
   * @throws ReportGenerationException en caso no se pueda exportar debido a un error 
   * de jasper
   */
  public byte[] getExcelBytes() throws ReportGenerationException {
    try {
      JasperPrint print = getJasperPrint();
      JRXlsExporter exporter = new JRXlsExporter();
      ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
      exporter.setExporterInput(new SimpleExporterInput(print));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
      
      SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
      configuration.setDetectCellType(true);
      configuration.setCollapseRowSpan(false);
      exporter.setConfiguration(configuration);
      
      exporter.exportReport();
      
      return xlsReport.toByteArray();
    } catch (JRException ex) {
      throw new ReportGenerationException(ex);
    }
  }
  
  /**
   * Obtiene los bytes del reporte representado en Excel con una configuración
   * definida por el usuario.
   * @param config Configuración del reporte para exportar a excel.
   * @return bytes del reporte en excel.
   * @throws ReportGenerationException en caso no se pueda exportar debido a un error
   * de jasper
   */
  public byte[] getExcelBytes (SimpleXlsReportConfiguration config) throws ReportGenerationException {
    try {
      JasperPrint print = getJasperPrint();
      JRXlsExporter exporter = new JRXlsExporter();
      ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
      exporter.setExporterInput(new SimpleExporterInput(print));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));

      exporter.setConfiguration(config);
      
      exporter.exportReport();
      
      return xlsReport.toByteArray();
    } catch (JRException ex) {
      throw new ReportGenerationException(ex);
    }
  }

  /**
   * Obtiene la lista de la data a exportar
   * @return lista con la data a exportar
   */
  public List<T> getData() {
    return data;
  }
  
  /**
   * Cambia la data a exportar
   * @param data que se desea exportar
   */
  public void setData(List<T> data) {
    this.data = data;
  }

  /**
   * Obtiene la ruta del reporte configurada
   * @return ruta del reporte
   */
  public String getReportSource() {
    return reportSource;
  }

  /**
   * Cambia la ruta del reporte configurada inicialmente.
   * @param reportSource ruta del reporte
   */
  public void setReportSource(String reportSource) {
    this.reportSource = reportSource;
  }

  /**
   * Obtiene los parametros que serán pasados al reporte
   * @return map de parametros del reporte
   */
  public Map getParams() {
    return params;
  }

  /**
   * Cambia los parametros del reporte
   * @param params del reporte
   */
  public void setParams(Map params) {
    this.params = params;
  }
}
