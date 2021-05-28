package MedicalOffice;

import java.util.Iterator;
import java.util.Scanner;
public class Pediatrician extends Doctor{

    public Pediatrician(int id, String specialty, String firstName, String lastName, int age) {
        super();
    }
    @Override
    public void showInfo() {
        System.out.println("Pediatrics is the branch of medicine that involves the medical care of infants, children, and adolescents. ");
        System.out.println("Our pediatricians are:");
        for (int i=0; i<DataBase.pediatricians.size(); i++)
        {
            showDoc(DataBase.pediatricians.get(i));
        }

    }
}
