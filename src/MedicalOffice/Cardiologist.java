package MedicalOffice;
import MedicalOffice.DataBase;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
public class Cardiologist extends Doctor{

    public Cardiologist( int id,String specialty, String firstName, String lastName, int age) {
        this.setId(id);
        this.setSpecialty(specialty);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
    }


    @Override
    public void showInfo() {
        System.out.println("Cardiology is a branch of medicine that deals with the disorders of the heart as well as some parts of the circulatory system. The field includes medical diagnosis and treatment of congenital heart defects, coronary artery disease, heart failure, valvular heart disease and electrophysiology.");
        System.out.println("Our cardiologists are:");
        for (int i=0; i<DataBase.cardiologists.size(); i++)
        {
            showDoc(DataBase.cardiologists.get(i));
        }

    }



}
