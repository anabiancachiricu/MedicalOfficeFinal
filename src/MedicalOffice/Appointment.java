package MedicalOffice;

public class Appointment {
    String date;
    String hour;
    int IdPatient;
    int idDoctor;

    public Appointment(String date, String hour, int IdPatient, int idDoctor) {
        this.date = date;
        this.hour=hour;
        this.IdPatient=IdPatient;
        this.idDoctor=idDoctor;
    }


}
