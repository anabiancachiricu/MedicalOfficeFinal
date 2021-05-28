package MedicalOffice;

import javax.swing.*;
import java.awt.*;

public class CardiologistsPanel extends JPanel {

    public static final String NEW_CARDIOLOGIST = "<New cardiologist>";

    private static JTextField idTextField;
    private static JTextField specialtyTextField;
    private static JTextField firstNameTextField;
    private static JTextField lastNameTextField;
    private static JTextField ageTextField;
    private Cardiologist cardiologist;

    public CardiologistsPanel(){
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowWeights = new double[]{0, 1};
        gridBagLayout.columnWeights = new double[]{0,1};
        setLayout(gridBagLayout);
        add(new JLabel("Id"));
        idTextField = new JTextField();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(idTextField, gridBagConstraints);
        add(new JLabel("Specialty"));
        specialtyTextField = new JTextField();
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(specialtyTextField, gridBagConstraints);
        add(new JLabel("First Name"));
        firstNameTextField = new JTextField();
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(firstNameTextField, gridBagConstraints);
        add(new JLabel("Last name"));
        lastNameTextField = new JTextField();
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(lastNameTextField, gridBagConstraints);
        add(new JLabel("Age"));
        ageTextField = new JTextField();
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(ageTextField, gridBagConstraints);

    }

    public void setCardiologist(Cardiologist cardiologist) {
        this.cardiologist = new Cardiologist(cardiologist.getId(), cardiologist.getSpecialty(), cardiologist.getFirstName(), cardiologist.getLastName(), cardiologist.getAge());
        idTextField.setText(String.valueOf(cardiologist.getId()));
        specialtyTextField.setText(cardiologist.getSpecialty());
        firstNameTextField.setText(cardiologist.getFirstName());
        lastNameTextField.setText(cardiologist.getLastName());
        ageTextField.setText(String.valueOf(cardiologist.getAge()));
        idTextField.requestFocus();
    }

    public static Cardiologist getCardiologist() {
        return new Cardiologist(Integer.parseInt(idTextField.getText()), specialtyTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(),Integer.parseInt(ageTextField.getText()));
    }

}