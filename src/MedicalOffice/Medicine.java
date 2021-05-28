package MedicalOffice;
import java.util.Scanner;
public class Medicine {
    private String name;
    private int weight;
    private String producer;

    public Medicine(String name, int weight, String producer) {
        this.name=name;
        this.weight=weight;
        this.producer=producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }


}
