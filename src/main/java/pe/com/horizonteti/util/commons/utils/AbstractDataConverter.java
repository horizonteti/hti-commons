package pe.com.horizonteti.util.commons.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta de conversor de datos, que implementa la conversión de listas
 * como un método estándar
 * @author Jorge
 *
 * @param <S> Clase fuente de la conversión
 * @param <T> Clase destino de la conversión
 */
public abstract class AbstractDataConverter<S, T> implements DataConverter<S, T> {

  @Override
  public List<T> convertList(List<S> dataList) {
    if (dataList == null) {
      return null;
    }
    List<T> result = new ArrayList<>();
    for (S s : dataList) {
      result.add(convert(s));
    }
    return result;
  }
  
}
