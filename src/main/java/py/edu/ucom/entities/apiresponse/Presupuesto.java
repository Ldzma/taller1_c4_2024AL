package py.edu.ucom.entities.apiresponse;

import java.util.Date;
import java.util.List;

public class Presupuesto {
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private double montoPresupuestado;
    private List<Gastos> gastos;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getMontoPresupuestado() {
        return montoPresupuestado;
    }

    public void setMontoPresupuestado(double montoPresupuestado) {
        this.montoPresupuestado = montoPresupuestado;
    }

    public List<Gastos> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gastos> gastos) {
        this.gastos = gastos;
    }

    @Override
    public String toString() {
        return "Presupuesto{" +
                "id=" + id +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", montoPresupuestado=" + montoPresupuestado +
                ", gastos=" + gastos +
                '}';
    }
}
