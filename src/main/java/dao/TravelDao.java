package dao;

import domain.Travel;
import exception.TravelAlreadyExistsException;
import exception.TravelNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class TravelDao {

    private Connection connection;

    public TravelDao(Connection connection) {
        this.connection = connection;
    }


    public void add(Travel travel) throws SQLException, TravelAlreadyExistsException {
        String sql = "INSERT INTO travels (id_travel, destination, price) VALUES (?, ?, ?)";
        if (existTravel(travel.getDestination()))
            throw new TravelAlreadyExistsException("This travel already exists");
        //todo hacer autoincrementales
        //todo repasar la mecanica de los autoincrementales (que no le falte a ningun método)
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, autoincremental());
        statement.setString(2, travel.getDestination());
        statement.setDouble(3, travel.getPrice());
        statement.executeUpdate();
    }

    public boolean delete(String destination) {
        try {
            String sql = "DELETE FROM travel WHERE destination = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, destination);
            int rows = statement.executeUpdate();

            return rows == 1;
        }catch (SQLException sqle){
            System.out.println("Problems probably related with this travel appearing in other tables");
            sqle.printStackTrace();
            return false;
        }

    }

    public boolean modify(String destination, Travel travel) throws SQLException, TravelAlreadyExistsException {
        /*Aqui solo vamos a modificar el precio, ya que modificar el id o el destino convertiría
        al viaje en otro diferente y daría muchos problemas de consistencia de datos. Si se quiere
        modificar cualquiera de los campos mencionados, lo correcto sería borrar el viaje y crear otro*/
        if (existTravel(travel.getDestination()))
            throw new TravelAlreadyExistsException("This travel already exists");

        String sql = "UPDATE travels SET destination = ?,  price = ? WHERE destination = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, travel.getDestination());
        statement.setDouble(2, travel.getPrice());
        statement.setString(3, destination);

        int rows = statement.executeUpdate();
        return rows == 1;
    }

    public ArrayList<Travel> showAll() throws SQLException {
        String sql = "SELECT * FROM travels ORDER BY destination";
        ArrayList<Travel> travels = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Travel travel = new Travel();
            travel.setId(resultSet.getInt("id_travel"));
            travel.setDestination(resultSet.getString("destination"));
            travel.setPrice(resultSet.getDouble("price"));
            travels.add(travel);
        }

        return travels;
    }

    public Optional<Travel> findByDestination(String destination) throws SQLException {
        String sql = "SELECT * FROM travels WHERE destination = ?";
        Travel travel = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, destination);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            travel = new Travel();
            travel.setId(resultSet.getInt("id_travel"));
            travel.setDestination(resultSet.getString("destination"));
            travel.setPrice(resultSet.getDouble("price"));
        }

        return Optional.ofNullable(travel);
    }

    private boolean existTravel(String destination) throws SQLException {
        Optional<Travel> travel = findByDestination(destination);
        return travel.isPresent();
    }
    private int autoincremental() throws SQLException {
        String sql = "SELECT COUNT (*) as autocount FROM travels";
        int autoincremental;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        autoincremental = resultSet.getInt("autocount");

        return autoincremental++;
    }

}






