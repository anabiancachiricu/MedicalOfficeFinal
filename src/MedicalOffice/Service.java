package MedicalOffice;

import java.sql.Timestamp;

public class Service {
    public void auditService(String action){
        Timestamp timestamp= new Timestamp(System.currentTimeMillis());
        String [] data={action, timestamp.toString(), Thread.currentThread().getName()};
        DataBase.writeDatatoCsv("audit.csv",data );


    }

}
