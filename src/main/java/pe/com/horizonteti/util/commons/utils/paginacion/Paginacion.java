package pe.com.horizonteti.util.commons.utils.paginacion;

import java.util.List;

/**
 * Created by JaxKodex on 29/07/2016.
 */
public interface Paginacion<T> {

    Integer getPaginaActual();
    Integer getTamanioPagina();
    Long getTotal();
    List<T> getElementos();

    void setPaginaActual(Integer paginaActual);
    void setTamanioPagina(Integer tamanioPagina);
    void setTotal(Long total);
    void setElementos(List<T> elementos);
}
