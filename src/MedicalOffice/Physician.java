package MedicalOffice;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
public class Physician extends Doctor{

    public Physician(int id, String specialty, String firstName, String lastName, int age) {
        super();
    }
    @Override
    public void showInfo() {
        System.out.println("A physician is a professional who practices medicine, which is concerned with promoting, maintaining, or restoring health through the study, diagnosis, prognosis and treatment of disease, injury, and other physical and mental impairments. Physicians may focus their practice on certain disease categories, types of patients, and methods of treatment ");
        System.out.println("Our physicians are:");
        for (int i=0; i<DataBase.physicians.size(); i++)
        {
            showDoc(DataBase.physicians.get(i));
        }
    }
    public void MakeSending(int idPatient, String department){
        if(department=="Cardiology"){
            System.out.println("Tou should make an appointment in cardiology");
        }
        else if(department=="Neurology"){
            System.out.println("Tou should make an appointment in neurology");
        }
        else if(department=="Radiology"){
            System.out.println("Tou should make an appointment in radiology");
        }
    }

}
