package pe.com.horizonteti.util.commons.utils;

import java.util.ArrayList;
import java.util.List;

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
