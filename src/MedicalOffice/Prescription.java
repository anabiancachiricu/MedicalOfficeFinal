package MedicalOffice;
import java.util.HashMap;

public class Prescription {

    HashMap<Medicine, Float> pres=new HashMap<Medicine, Float>();

    public void addMedToPrescription(Medicine m, Float quantity){
        this.pres.put(m, quantity);
    }

    public void deleteFromPrescription(Prescription prescription, Medicine m){
        this.pres.remove(m);
    }

}
