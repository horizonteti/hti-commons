package pe.com.horizonteti.util.commons.utils.paginacion;

import java.util.List;

/**
 * Created by JaxKodex on 29/07/2016.
 */
public class PaginacionImpl<T> implements Paginacion<T> {
    private Integer paginaActual;
    private Integer tamanioPagina;
    private Long total;
    private List<T> elementos;

    public PaginacionImpl (Integer paginaActual, Long total, Integer tamanioPagina, List<T> elementos) {
        this.paginaActual = paginaActual;
        this.tamanioPagina = tamanioPagina;
        this.total = total;
        this.elementos = elementos;
    }

    public PaginacionImpl (Integer paginaActual, Integer tamanioPagina) {
        this.paginaActual = paginaActual;
        this.tamanioPagina = tamanioPagina;
    }

    @Override
    public Integer getPaginaActual() {
        return paginaActual;
    }

    @Override
    public Integer getTamanioPagina() {
        return tamanioPagina;
    }

    @Override
    public Long getTotal() {
        return total;
    }

    @Override
    public List<T> getElementos() {
        return elementos;
    }

    @Override
    public void setPaginaActual(Integer paginaActual) {
        this.paginaActual = paginaActual;
    }

    @Override
    public void setTamanioPagina(Integer tamanioPagina) {
        this.tamanioPagina = tamanioPagina;
    }

    @Override
    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public void setElementos(List<T> elementos) {
        this.elementos = elementos;
    }
}
