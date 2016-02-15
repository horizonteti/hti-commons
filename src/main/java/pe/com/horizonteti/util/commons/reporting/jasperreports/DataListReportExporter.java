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

public abstract class DataListReportExporter<T> {

  private List<T> data;
  private String reportSource;
  private InputStream inputStream;
  private Map params;
  
  public DataListReportExporter(List<T> data, String reportSource, Map params) {
    this.data = data;
    this.reportSource = reportSource;
    this.params = params;
  }
  
  private JasperReport loadReportInputStream() throws JRException {
    inputStream = DataListReportExporter.class.getClassLoader().getResourceAsStream(reportSource);
    return JasperCompileManager.compileReport(inputStream);
  }
  
  protected abstract JRDataSource loadJRDataSource();
  
  protected JasperPrint getJasperPrint() throws JRException {
    return JasperFillManager.fillReport(loadReportInputStream(), params, loadJRDataSource());
  }
  
  public byte[] getPdfBytes() throws ReportGenerationException {
    JasperPrint print;
    try {
      print = getJasperPrint();
      return JasperExportManager.exportReportToPdf(print);
    } catch (JRException e) {
      throw new ReportGenerationException(e);
    }
  }
  
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

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public String getReportSource() {
    return reportSource;
  }

  public void setReportSource(String reportSource) {
    this.reportSource = reportSource;
  }

  public Map getParams() {
    return params;
  }

  public void setParams(Map params) {
    this.params = params;
  }
}
