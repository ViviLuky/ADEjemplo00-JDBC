package com.cieep.modelos;

public class Empleados {
    String DNI;
    String empleo ;
    String nombre ;
    int horas_semanale;

    public Empleados(String DNI, String empleo, String nombre, int horas_semanale) {
        this.DNI = DNI;
        this.empleo = empleo;
        this.nombre = nombre;
        this.horas_semanale = horas_semanale;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getEmpleo() {
        return empleo;
    }

    public void setEmpleo(String empleo) {
        this.empleo = empleo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras_semanale() {
        return horas_semanale;
    }

    public void setHoras_semanale(int horas_semanale) {
        this.horas_semanale = horas_semanale;
    }

    @Override
    public String toString() {
        return "Empleados{" +
                "DNI='" + DNI + '\'' +
                ", empleo='" + empleo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", horas_semanale=" + horas_semanale +
                '}';
    }
}
