package MedicalOffice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardiologistsDB extends Doctor implements AutoCloseable {

    private static Connection connection;

    public CardiologistsDB() throws SQLException {

        this.connection = DriverManager.getConnection("jdbc:derby:D:/Facultate/Anul2/'Semestrul 2'/'Programare Avansata pe Obiecte'/MedicalOfficeFinal/MedicalOfficeDB;create=true");

        boolean notFound = true;

        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});

        while(results.next()){
            if("cardiologists".equalsIgnoreCase(results.getString("TABLE_NAME"))){
                notFound = false;
                break;
            }

        }

        if(notFound){
            connection.createStatement()
                    .execute("CREATE TABLE cardiologists (id int, specialty varchar(50), firstName varchar(50), lastName varchar(50), age int)");
        }


    }


    @Override
    public void close() throws Exception {
        connection.close();
    }

    public static Cardiologist createCardiologist(int id, String specialty, String firstName, String lastName, int age) throws SQLException {
        Cardiologist c=new Cardiologist(id, specialty, firstName, lastName, age);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO cardiologists (id, specialty, firstName, lastName,age) VALUES (?,?,?,?,?)");
        statement.setInt(1, c.getId());
        statement.setString(2,c.getSpecialty());
        statement.setString(3, c.getFirstName());
        statement.setString(4, c.getLastName());
        statement.setInt(5, c.getAge());
        System.out.println("The cardiologist was successfully added");
        if(statement.executeUpdate() == 1){
            return c;
        }
        return null;
    }

    public static List<Cardiologist> readCardiologist() throws Exception {
        List<Cardiologist> cardiologists = new ArrayList<>();
        ResultSet results = connection.createStatement().executeQuery("SELECT id, specialty,firstName, lastName, age FROM cardiologists");
        while(results.next()){
            cardiologists.add(new Cardiologist( results.getInt(1),results.getString(2), results.getString(3), results.getString(4), results.getInt(5)));
        }
        System.out.println("Read Cardiologist was successfull");
        return cardiologists;

    }

    public static boolean updateCardiologist(Cardiologist cardiologist) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE cardiologists SET firstName = ?, lastName = ?, age=?, WHERE id = ?");
        statement.setString(1, cardiologist.getFirstName());
        statement.setString(2, cardiologist.getLastName());
        statement.setInt(3, cardiologist.getAge());
        statement.setInt(4,cardiologist.getId());
        System.out.println("The cardiologist was updated");
        return statement.executeUpdate() == 1;
    }

    public static boolean deleteCardiologist(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM cardiologists WHERE id = ?");
        statement.setInt(1, id);
        System.out.println("The cardiologist was deleted");
        return statement.executeUpdate() == 1;
    }

    public static void dropTableCardiologist(){

        try {
            PreparedStatement stmt = connection.prepareStatement("DROP TABLE cardiologists");
            stmt.executeUpdate();
            System.out.println("Table cardiologists dropped!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void showInfo() {

    }


}
