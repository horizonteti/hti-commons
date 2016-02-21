package pe.com.horizonteti.util.commons.utils;

import java.util.List;

import org.springframework.core.convert.converter.Converter;

public interface DataConverter<S, T> extends Converter<S, T> {

  /**
   * Convierte una lista de tipo S al tipo T
   * @param dataList del tipo S
   * @return lista del tipo T
   */
  public List<T> convertList (List<S> dataList);
}
