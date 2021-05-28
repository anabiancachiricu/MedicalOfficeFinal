package MedicalOffice;
import java.util.Scanner;
public abstract class Doctor {
    protected static int id;
    protected static String specialty;
    protected static String firstName;
    protected static String lastName;
    protected static int age;
    protected static int counter=0;
    //protected static int idDoctor;

    public static String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String speciality) {
        this.specialty = speciality;
    }

    public static String getFirstName() {
        return firstName;
    }
    public static String getLastName(){
        return lastName;
    }

    public static int getAge() {
        return age;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public void setAge(int age) {
        this.age = age;
    }


    /*public static int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor() {
        counter++;
        this.idDoctor = counter;
    }*/

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Doctor.id = id;
    }

    Scanner console=new Scanner(System.in);
    public void writePrescription(){
        System.out.println("Enter the number of meds:");
        int num=console.nextInt();
        for (int i=1; i<=num; i++){
            System.out.println("Enter med:");
            System.out.println("Enter med name:");
            String n=console.next();
            System.out.println("Enter med weight:");
            int w=console.nextInt();
            System.out.println("Enter med producer:");
            String prod=console.next();
            Medicine m=new Medicine(n, w, prod);
            DataBase.medicines.add(m);
            System.out.println("Enter the quantity:");
            Float q=console.nextFloat();

            Prescription p=new Prescription();
            p.addMedToPrescription(m,q);
        }
    }

    public abstract void showInfo();
    public void showDoc(Doctor d){
        System.out.println(d.firstName +" "+d.lastName+" "+d.age);
    }


}
