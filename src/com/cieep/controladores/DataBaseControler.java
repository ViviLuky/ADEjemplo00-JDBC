package com.cieep.controladores;

import com.cieep.bd.Constantes;
import com.cieep.modelos.Animal;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseControler {
    // Dar las herramientas para gestión de la base de datos

    private final String SERVER="jdbc:mysql://localhost/granja";
    private final String USER="root";
    private final String PASSWORD="toor";

    public DataBaseControler() throws SQLException {
        inicializarTablas();
        inializarTabla();
    }

    private void inializarTabla() throws SQLException {
        Connection connection=obtenerConexión();
        String query = "create table if not exists " + Constantes.TABLA_EMPLEADOS + "(\n" +
                "     "+Constantes.DNI+ " varchar(9) Primary Key,\n" +
                "    "+Constantes.EMPLEO+" varchar(40) not null ,\n" +
                "    "+Constantes.NOMBRE_EMPLEADO+" varchar(40) not null ,\n" +
                "    "+Constantes.HORAS_SEMANALES+" int not null\n" +
                ");";
        Statement stm=connection.createStatement();
        stm.execute(query);

    }

    public Connection obtenerConexión() throws SQLException {

        return DriverManager.getConnection(SERVER,USER,PASSWORD);
    }
    private void inicializarTablas() throws SQLException {

        Connection connection=obtenerConexión();
        String query="create table if not exists " + Constantes.TABLA_ANIMALES + "(\n" +
                "    "+Constantes.ID_ANIMAL+" int PRIMARY KEY ,\n" +
                "    "+Constantes.TIPO+" varchar(40)not null ,\n" +
                "    "+Constantes.NOMBRE+" varchar(40)not null ,\n" +
                "    "+Constantes.COLOR+" varchar(10),\n" +
                "    "+Constantes.EDAD+" int not null ,\n" +
                "    "+Constantes.NUMEROENFERMEDADES+" int not null \n" +
                ");";

        Statement stm=connection.createStatement();
        stm.execute(query);


    }

    public int insertarAnimal(Animal animal,Connection connection) throws SQLException {

        String query="insert into "+Constantes.TABLA_ANIMALES+ "values(?,?,?,?,?,?)";

        PreparedStatement pstm=connection.prepareStatement(query);

        pstm.setInt(1,animal.getIdAnimal());
        pstm.setString(2,animal.getTipo());
        pstm.setString(3,animal.getNombre());
        pstm.setString(4, animal.getColor());
        pstm.setInt(5,animal.getEdad());
        pstm.setInt(6,animal.getNumEnfermedades());

        return pstm.executeUpdate();
    }
    public Animal getAnimal(int idAnimal,Connection connection) throws SQLException {
        String query="Select * from "+Constantes.TABLA_ANIMALES+" where "+Constantes.ID_ANIMAL+"=?";
        PreparedStatement pstm=connection.prepareStatement(query);
        pstm.setInt(1,idAnimal);
        ResultSet rs=pstm.executeQuery();

        if(rs.next()){
           return  new Animal(
                   rs.getInt(1),
                   rs.getString(2),
                   rs.getString(3),
                   rs.getString(4),
                   rs.getInt(5),
                   rs.getInt(6)
           );
        }
        return null;
 }
    public ArrayList<Animal> getAnimales(Connection connection) throws SQLException {

        ArrayList<Animal> animales = new ArrayList<>();

        String query = "select * from "+Constantes.TABLA_ANIMALES;
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(query);


        while (rs.next()){
            animales.add(new Animal(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6)
            ));
        }
               return animales;
   }
    public boolean deleteAnimal(Animal animal,Connection connection) throws SQLException {
        String query = "delete from "+Constantes.TABLA_ANIMALES+" where "+Constantes.ID_ANIMAL+" = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1,animal.getIdAnimal());

       return pstm.executeUpdate() == 1;
    }
    public boolean updateAnimal(Animal animal,Connection connection) throws SQLException {
        String query = "update "+Constantes.TABLA_ANIMALES+ " \n" +
                "                     "+Constantes.TIPO+" = ?, \n" +
                "                     "+Constantes.NOMBRE+" = ?, \n" +
                "                     "+Constantes.COLOR+" = ?,\n" +
                "                     "+Constantes.EDAD+" = ?,\n" +
                "                     "+Constantes.NUMEROENFERMEDADES+" = ?\n" +
                " where " +Constantes.ID_ANIMAL+ " = ?";

        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setString(1, animal.getTipo());
        pstm.setString(2, animal.getNombre());
        pstm.setString(3, animal.getColor());
        pstm.setInt(4,animal.getEdad());
        pstm.setInt(5,animal.getNumEnfermedades());
        pstm.setInt(6,animal.getIdAnimal());

        return pstm.executeUpdate() == 1;
   }
    public int updateEdades(int incremento, Connection connection) throws SQLException {
        String query = " update "+Constantes.TABLA_ANIMALES+ " set "+Constantes.EDAD+ " = "+Constantes.EDAD + "?;";

        PreparedStatement pstm = connection.prepareStatement(query);

        pstm.setInt(1,incremento);

        int filas = pstm.executeUpdate();
        int otrasFilas = pstm.getUpdateCount();
        return filas;


    }
}
