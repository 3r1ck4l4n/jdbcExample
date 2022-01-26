package mx.com.jdbc;

import mx.com.jdbc.beans.Personas;
import mx.com.jdbc.db.PersonasDB;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        PersonasDB personasDB = new PersonasDB();

        List<Personas> personasDBList = personasDB.getPersonas();

        for ( Personas p: personasDBList) {
            System.out.println("ID: "+ p.getId() + " Nombre: "+p.getName()+ " Email: "+ p.getEmail());
        }

        personasDB.deletePersona(6);

        personasDBList = personasDB.getPersonas();

        for ( Personas p: personasDBList) {
            System.out.println("ID: "+ p.getId() + " Nombre: "+p.getName()+ " Email: "+ p.getEmail());
        }
    }
}
