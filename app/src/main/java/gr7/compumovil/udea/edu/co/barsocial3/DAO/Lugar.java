package gr7.compumovil.udea.edu.co.barsocial3.DAO;

import java.util.List;

/**
 * Created by r3tx on 4/10/16.
 */
public class Lugar {
    String direccion,
            name,
            pequeñaDescripcion,
            descripcion,
            imagenUrl,
            creditCard,
            horario,
            outDoor,
            reservacion;
    double rate;
    Gps gps;

    List<Evento> eventos;
    List<Producto> productos;
    List<Categoria> categoria;

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public String getHorario() {
        return horario;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public Gps getGps() {
        return gps;
    }

    public String getName() {
        return name;
    }

    public String getOutDoor() {
        return outDoor;
    }

    public String getPequeñaDescripcion() {
        return pequeñaDescripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double getRate() {
        return rate;
    }

    public String getReservacion() {
        return reservacion;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOutDoor(String outDoor) {
        this.outDoor = outDoor;
    }

    public void setPequeñaDescripcion(String pequeñaDescripcion) {
        this.pequeñaDescripcion = pequeñaDescripcion;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setReservacion(String reservacion) {
        this.reservacion = reservacion;
    }
}
