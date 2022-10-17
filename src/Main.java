import com.cieep.controladores.DataBaseControler;
import com.cieep.modelos.Animal;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            DataBaseControler dataBaseControler=new DataBaseControler();

            Animal animal = new Animal(1,"Marino","Nemo","Naranja",1,1);

            Connection connection=dataBaseControler.obtenerConexión();

           // dataBaseControler.insertarAnimal(animal,connection);
            System.out.println(dataBaseControler.getAnimal(1,connection));
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}