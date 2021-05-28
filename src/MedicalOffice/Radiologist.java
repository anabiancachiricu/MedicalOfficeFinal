package MedicalOffice;

import java.util.Iterator;
import java.util.Scanner;
public class Radiologist extends Doctor{

    public Radiologist(int id, String specialty, String firstName, String lastName, int age) {
        super();
    }
    @Override
    public void showInfo() {
        System.out.println("Radiology is the medical discipline that uses medical imaging to diagnose and treat diseases within the bodies of animals, including humans.");
        System.out.println("Our radiologists are:");
        for (int i=0; i<DataBase.radiologists.size(); i++)
        {
            showDoc(DataBase.radiologists.get(i));
        }
    }
}
