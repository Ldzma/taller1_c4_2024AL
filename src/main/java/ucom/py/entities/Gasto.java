package ucom.py.entities;

public class Gasto {
    // Declaro un atributo `id` para identificar cada gasto.
    private int id;
    
    // Declaro un atributo `fecha` para almacenar la fecha del gasto en formato de tiempo Unix.
    private long fecha;
    
    // Declaro un atributo `monto` para representar el valor del gasto.
    private double monto;
    
    // Declaro un atributo `descripcion` para dar más detalles sobre el gasto.
    private String descripcion;

    // Creo un método getter para obtener el `id` del gasto.
    public int getId() {
        return id;
    }

    // Creo un método setter para asignar un `id` al gasto.
    public void setId(int id) {
        this.id = id;
    }

    // Creo un método getter para obtener la `fecha` del gasto.
    public long getFecha() {
        return fecha;
    }

    // Creo un método setter para asignar una `fecha` al gasto.
    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    // Creo un método getter para obtener el `monto` del gasto.
    public double getMonto() {
        return monto;
    }

    // Creo un método setter para asignar un `monto` al gasto.
    public void setMonto(double monto) {
        this.monto = monto;
    }

    // Creo un método getter para obtener la `descripcion` del gasto.
    public String getDescripcion() {
        return descripcion;
    }

    // Creo un método setter para asignar una `descripcion` al gasto.
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
