package MedicalOffice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RadiologistsDB extends Doctor implements AutoCloseable {

    private static Connection connection;

    public RadiologistsDB() throws SQLException {

        this.connection = DriverManager.getConnection("jdbc:derby:D:/Facultate/Anul2/'Semestrul 2'/'Programare Avansata pe Obiecte'/MedicalOfficeFinal/MedicalOfficeDB;create=true");

        boolean notFoundR=true;

        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});

        while(results.next()){
            if("radiologists".equalsIgnoreCase(results.getString("TABLE_NAME"))){
                notFoundR = false;
                break;
            }

        }

        if(notFoundR){
            connection.createStatement()
                    .execute("CREATE TABLE radiologists(id int , specialty varchar(50), firstName varchar(50), lastName varchar(50), age int)");
        }
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }

    public static Radiologist createRadiologist(int id, String specialty, String firstName, String lastName, int age) throws SQLException {
        Radiologist r=new Radiologist(id, specialty, firstName, lastName, age);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO radiologists (id,specialty, firstName, lastName,age) VALUES (?,?,?,?,?)");
        statement.setInt(1, r.getId());
        statement.setString(2,r.getSpecialty());
        statement.setString(3, r.getFirstName());
        statement.setString(4, r.getLastName());
        statement.setInt(5, r.getAge());
        if(statement.executeUpdate() == 1){
            return r;
        }
        return null;
    }

    public static List<Radiologist> readRadiologists() throws Exception {
        List<Radiologist> radiologists = new ArrayList<>();
        ResultSet results = connection.createStatement().executeQuery("SELECT id, specialty,firstName, lastName, age FROM radiologists");
        while(results.next()){
            radiologists.add(new Radiologist(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getInt(5)));
        }
        return radiologists;
    }

    public static boolean updateRadiologist(Radiologist radiologist) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE radiologists SET firstName = ?, lastName = ?, age=?, WHERE id = ?");
        statement.setString(1, radiologist.getFirstName());
        statement.setString(2, radiologist.getLastName());
        statement.setInt(3, radiologist.getAge());
        statement.setInt(4,radiologist.getId());
        return statement.executeUpdate() == 1;
    }

    public static boolean deleteRadiologist(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM radiologists WHERE id = ?");
        statement.setInt(1, id);
        return statement.executeUpdate() == 1;
    }

    public static void dropTableRadiologists(){

        try {
            PreparedStatement stmt = connection.prepareStatement("DROP TABLE radiologists");
            stmt.executeUpdate();
            System.out.println("Table radiologists dropped!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void showInfo() {

    }


}
