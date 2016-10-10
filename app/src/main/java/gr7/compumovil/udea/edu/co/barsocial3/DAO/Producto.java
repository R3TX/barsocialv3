package gr7.compumovil.udea.edu.co.barsocial3.DAO;

/**
 * Created by r3tx on 4/10/16.
 */
public class Producto {

    String name, tipo, descripcion,urlImagen;

    public String getDescripcion() {
        return descripcion;
    }

    public String getName() {
        return name;
    }

    public String getTipo() {
        return tipo;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
