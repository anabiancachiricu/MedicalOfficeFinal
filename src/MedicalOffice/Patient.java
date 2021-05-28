package MedicalOffice;

public class Patient {
    private String firstName;
    private String lastName;
    private int age;
    private int weight;
    private String phone;
    private String CNP;
    private String email;
    static int nr=0;
    int IdPatient;


    public Patient(String firstName, String lastName, int age, int weight, String phone, String CNP, String email) {
        this.firstName=firstName;
        this.lastName = lastName;
        this.age=age;
        this.weight=weight;
        this.phone=phone;
        this.CNP=CNP;
        this.email=email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdPatient() {
        return IdPatient;
    }

    public void setIdPatient(int idPatient) {
        nr++;
        this.IdPatient = nr;
    }

    public void showProfile(Patient p){
        System.out.println("Name:"+p.getFirstName()+p.getLastName());
        System.out.println("Age:"+p.getAge());
        System.out.println("Weight:"+p.getWeight());
        System.out.println("CNP:"+p.getCNP());
        System.out.println("Date de contact:");
        System.out.println("Phone:"+p.getPhone());
        System.out.println("Email:"+p.getEmail());
    }
}
