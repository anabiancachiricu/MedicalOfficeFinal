package MedicalOffice;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {

        new CardiologistsDB();
        CardiologistsDB.createCardiologist(1,"cardiologist", "Cridtina", "Yang", 35);
        try {
            new CardiologistsFrame().setVisible(true);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
