package MedicalOffice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhysiciansDB extends Doctor implements AutoCloseable {

    private static Connection connection;

    public PhysiciansDB() throws SQLException {

        this.connection = DriverManager.getConnection("jdbc:derby:D:/Facultate/Anul2/'Semestrul 2'/'Programare Avansata pe Obiecte'/MedicalOfficeFinal/MedicalOfficeDB;create=true");

        boolean notFoundPy=true;

        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});

        while(results.next()){

            if("physicians".equalsIgnoreCase(results.getString("TABLE_NAME"))){
                notFoundPy = false;
                break;
            }

        }


        if(notFoundPy){
            connection.createStatement()
                    .execute("CREATE TABLE physicians(id int , specialty varchar(50), firstName varchar(50), lastName varchar(50), age int)");
        }

    }


    @Override
    public void close() throws Exception {
        connection.close();
    }

    public static Physician createPhysician(int id,String specialty, String firstName, String lastName, int age) throws SQLException {
        Physician p=new Physician(id, specialty, firstName, lastName, age);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO physicians (id,specialty, firstName, lastName,age) VALUES (?,?,?,?,?)");
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

    public static List<Physician> readPhysicians() throws Exception {
        List<Physician> physicians = new ArrayList<>();
        ResultSet results = connection.createStatement().executeQuery("SELECT id, specialty,firstName, lastName, age FROM physicians");
        while(results.next()){
            physicians.add(new Physician(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getInt(5)));
        }
        return physicians;
    }

    public static boolean updatePhysician(Physician physician) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE physician SET firstName = ?, lastName = ?, age=?, WHERE id = ?");
        statement.setString(1, physician.getFirstName());
        statement.setString(2, physician.getLastName());
        statement.setInt(3, physician.getAge());
        statement.setInt(4,physician.getId());
        return statement.executeUpdate() == 1;
    }

    public static boolean deletePhysician(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM physicians WHERE id = ?");
        statement.setInt(1, id);
        return statement.executeUpdate() == 1;
    }
    public static void dropTablePhysician(){

        try {
            PreparedStatement stmt = connection.prepareStatement("DROP TABLE physicians");
            stmt.executeUpdate();
            System.out.println("Table physician dropped!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void showInfo() {

    }


}
