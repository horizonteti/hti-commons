package pe.com.horizonteti.util.commons.reporting.jasperreports;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

public class MapDataListReposrtExporter extends DataListReportExporter<Map<String, ?>> {

  public MapDataListReposrtExporter(List<Map<String, ?>> data, String reportSource, Map params) {
    super(data, reportSource, params);
  }

  @Override
  protected JRDataSource loadJRDataSource() {
    return new JRMapCollectionDataSource(this.getData());
  }

}
