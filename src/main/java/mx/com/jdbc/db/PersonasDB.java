package mx.com.jdbc.db;

import mx.com.jdbc.beans.Personas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonasDB extends DBConnection{

    //Retorna un lista de personas
    public List<Personas> getPersonas() throws SQLException {
        List<Personas> personas = new ArrayList<>();
        openConnection();
        String query = "select * from personas";
        try {
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(query);

            while (resultSet.next()) {
                Personas p = new Personas(resultSet.getString("nombre"), resultSet.getString("email"), Integer.parseInt(resultSet.getString("id")));
                personas.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return personas;
    }

    public boolean setPersona(Personas persona) throws SQLException {
        boolean insert = false;
        openConnection();
        String query = "insert into personas values(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, persona.getName());
            statement.setString(2, persona.getEmail());

            statement.execute();
            statement.close();

            insert = true;

        } catch (Exception e) {
            e.printStackTrace();
            closeConnection();
        } finally {
            return insert;
        }
    }

    public boolean updatePersona(Personas personas) throws SQLException {
        boolean status = false;
        openConnection();
        String query = "update personas set nombre = ?, email = ? where id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(3,Integer.toString(personas.getId()));
            statement.setString(2, personas.getEmail());
            statement.setString(1, personas.getName());
            statement.execute();
            statement.close();
            status = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
            return status;
        }
    }

    public boolean deletePersona(int id) throws SQLException {
        boolean status = false;
        openConnection();
        String query ="delete from personas where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(id));
            status = statement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
            return status;
        }
    }

}
