package MedicalOffice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PediatriciansDB extends Doctor implements AutoCloseable {

    private static Connection connection;

    public PediatriciansDB() throws SQLException {

        this.connection = DriverManager.getConnection("jdbc:derby:D:/Facultate/Anul2/'Semestrul 2'/'Programare Avansata pe Obiecte'/MedicalOfficeFinal/MedicalOfficeDB;create=true");

        boolean notFoundP=true;


        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});

        while(results.next()){

            if("pediatricians".equalsIgnoreCase(results.getString("TABLE_NAME"))){
                notFoundP = false;
                break;
            }

        }


        if(notFoundP){
            connection.createStatement()
                    .execute("CREATE TABLE pediatricians (id int , specialty varchar(50), firstName varchar(50), lastName varchar(50), age int)");
        }

    }


    @Override
    public void close() throws Exception {
        connection.close();
    }

    public static Pediatrician createPediatrician(int id,String specialty, String firstName, String lastName, int age) throws SQLException {
        Pediatrician p=new Pediatrician(id, specialty, firstName, lastName, age);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO pediatricians (id,specialty, firstName, lastName,age) VALUES (?,?,?,?,?)");
        statement.setInt(1, p.getId());
        statement.setString(2,p.getSpecialty());
        statement.setString(3, p.getFirstName());
        statement.setString(4, p.getLastName());
        statement.setInt(5, p.getAge());
        if(statement.executeUpdate() == 1){
            return p;
        }
        return null;
    }

    public static List<Pediatrician> readPediatricians() throws Exception {
        List<Pediatrician> pediatricians = new ArrayList<>();
        ResultSet results = connection.createStatement().executeQuery("SELECT id, specialty,firstName, lastName, age FROM pediatricians");
        while(results.next()){
            pediatricians.add(new Pediatrician(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getInt(5)));
        }
        return pediatricians;
    }

    public static boolean updatePediatrician(Pediatrician pediatrician) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE pediatricians SET firstName = ?, lastName = ?, age=?, WHERE id = ?");
        statement.setString(1, pediatrician.getFirstName());
        statement.setString(2, pediatrician.getLastName());
        statement.setInt(3, pediatrician.getAge());
        statement.setInt(4,pediatrician.getId());
        return statement.executeUpdate() == 1;
    }

    public static boolean deletePediatrician(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM pediatricians WHERE id = ?");
        statement.setInt(1, id);
        return statement.executeUpdate() == 1;
    }

    public static void dropTablePediatricians(){

        try {
            PreparedStatement stmt = connection.prepareStatement("DROP TABLE pediatricians");
            stmt.executeUpdate();
            System.out.println("Table pediatricians dropped!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void showInfo() {

    }


}
