package MedicalOffice;
import java.util.Scanner;
import java.util.Iterator;

public class Neurologist extends Doctor{
    public Neurologist(int id, String specialty, String firstName, String lastName, int age) {
        super();
    }
    @Override
    public void showInfo() {
        System.out.println("Neurology is a branch of medicine dealing with disorders of the nervous system. Neurology deals with the diagnosis and treatment of all categories of conditions and disease involving the central and peripheral nervous systems (and their subdivisions, the autonomic and somatic nervous systems), including their coverings, blood vessels, and all effector tissue, such as muscle. Neurological practice relies heavily on the field of neuroscience, the scientific study of the nervous system.");
        System.out.println("Our neurologists are:");
        for (int i=0; i<DataBase.neurologists.size(); i++)
        {
            showDoc(DataBase.neurologists.get(i));
        }

    }

}
