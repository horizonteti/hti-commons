package pe.com.horizonteti.util.commons.reporting.jasperreports;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Clase para exportar beans de java con jasper
 * @author Jorge
 *
 * @param <T> Clase java de la cual heredan los datos a exportar.
 */
public class BeanDataListReportExporter<T> extends DataListReportExporter<T> {

  public BeanDataListReportExporter(List<T> data, String reportSource, Map params) {
    super(data, reportSource, params);
  }

  @Override
  protected JRDataSource loadJRDataSource() {
    return new JRBeanCollectionDataSource(this.getData());
  }

}
