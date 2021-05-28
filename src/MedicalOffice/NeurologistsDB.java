package MedicalOffice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NeurologistsDB extends Doctor implements AutoCloseable {

    private static Connection connection;

    public NeurologistsDB() throws SQLException {

        this.connection = DriverManager.getConnection("jdbc:derby:D:/Facultate/Anul2/'Semestrul 2'/'Programare Avansata pe Obiecte'/MedicalOfficeFinal/MedicalOfficeDB;create=true");

        boolean notFoundN=true;

        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});

        while(results.next()){

            if("neurologists".equalsIgnoreCase(results.getString("TABLE_NAME"))){
                notFoundN = false;
                break;
            }

        }

        if(notFoundN){
            connection.createStatement()
                    .execute("CREATE TABLE neurologists (id int , specialty varchar(50), firstName varchar(50), lastName varchar(50), age int)");
        }

    }


    @Override
    public void close() throws Exception {
        connection.close();
    }

    public static Neurologist createNeurologist(int id, String specialty, String firstName, String lastName, int age) throws SQLException {
        Neurologist n=new Neurologist(id, specialty, firstName, lastName, age);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO neurologists (id,specialty, firstName, lastName,age) VALUES (?,?,?,?,?)");
        statement.setInt(1, n.getId());
        statement.setString(2,n.getSpecialty());
        statement.setString(3, n.getFirstName());
        statement.setString(4, n.getLastName());
        statement.setInt(5, n.getAge());
        if(statement.executeUpdate() == 1){
            return n;
        }
        return null;
    }

    public static List<Neurologist> readNeurologists() throws Exception {
        List<Neurologist> neurologists = new ArrayList<>();
        ResultSet results = connection.createStatement().executeQuery("SELECT id, specialty,firstName, lastName, age FROM neurologists");
        while(results.next()){
            neurologists.add(new Neurologist(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getInt(5)));
        }
        return neurologists;
    }

    public static boolean updateNeurologist(Neurologist neurologist) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE neurologists SET firstName = ?, lastName = ?, age=?, WHERE id = ?");
        statement.setString(1, neurologist.getFirstName());
        statement.setString(2, neurologist.getLastName());
        statement.setInt(3,neurologist.getAge());
        statement.setInt(4,neurologist.getId());
        return statement.executeUpdate() == 1;
    }

    public static boolean deleteNeurologist(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM neurologists WHERE id = ?");
        statement.setInt(1, id);
        return statement.executeUpdate() == 1;
    }
    public static void dropTableNeurologist(){

        try {
            PreparedStatement stmt = connection.prepareStatement("DROP TABLE neurologists");
            stmt.executeUpdate();
            System.out.println("Table neurologists dropped!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void showInfo() {

    }


}
