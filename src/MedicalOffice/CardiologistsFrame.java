package MedicalOffice;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

import static MedicalOffice.CardiologistsPanel.NEW_CARDIOLOGIST;

public class CardiologistsFrame extends JFrame {
    public CardiologistsFrame() throws SQLException {
        CardiologistsDB cardiologistsDB = new CardiologistsDB();
        setTitle("Cardiologists");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 400);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);
        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.3);
        contentPane.add(splitPane, BorderLayout.CENTER);
        final DefaultListModel<Cardiologist> cardiologists = new DefaultListModel<>();
        JList<Cardiologist> list = new JList<>(cardiologists);
        splitPane.setLeftComponent(list);
        JPanel panel = new JPanel();
        splitPane.setRightComponent(panel);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{1};
        gridBagLayout.rowWeights = new double[]{1, 0};
        panel.setLayout(gridBagLayout);
        final CardiologistsPanel cardiologistsPanel = new CardiologistsPanel();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0,0,5, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        panel.add(cardiologistsPanel, gridBagConstraints);
        cardiologistsPanel.setVisible(false);
        JPanel buttonsPanel = new JPanel();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridy = 1;
        panel.add(buttonsPanel, gridBagConstraints);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(event -> {
            Cardiologist cardiologist = cardiologistsPanel.getCardiologist();
            if( !cardiologist.getSpecialty().isEmpty() && !cardiologist.getFirstName().isEmpty() && !cardiologist.getLastName().isEmpty()){
                try {
                    cardiologist = cardiologistsDB.createCardiologist(cardiologist.getId(), cardiologist.getSpecialty(), cardiologist.getFirstName(), cardiologist.getLastName(), cardiologist.getAge());
                    cardiologists.addElement(cardiologist);
                    list.setSelectedIndex(cardiologists.size() - 1);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(CardiologistsFrame.this,
                            exception.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonsPanel.add(createButton);
        createButton.setVisible(false);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(event-> {
            Cardiologist cardiologist = CardiologistsPanel.getCardiologist();
            if(!cardiologist.getSpecialty().isEmpty() && !cardiologist.getFirstName().isEmpty() && !cardiologist.getLastName().isEmpty() ){
                try {
                    if(cardiologistsDB.updateCardiologist(cardiologist)){
                        cardiologists.set(list.getSelectedIndex(), cardiologist);
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(CardiologistsFrame.this,
                            exception.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonsPanel.add(updateButton);
        updateButton.setVisible(false);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(event->{
            try {
                if(cardiologistsDB.deleteCardiologist(cardiologistsPanel.getCardiologist().getId())){
                    int index = list.getSelectedIndex();
                    list.setSelectedIndex(0);
                    cardiologists.remove(index);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(CardiologistsFrame.this,
                        exception.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonsPanel.add(deleteButton);
        deleteButton.setVisible(false);

        list.addListSelectionListener(event->{
            //CardiologistsPanel.setVisible(true);
            Cardiologist cardiologist = ((JList<Cardiologist>)event.getSource()).getSelectedValue();
            cardiologistsPanel.setCardiologist(cardiologist);
            createButton.setVisible(true);
            updateButton.setVisible(true);
            deleteButton.setVisible(true);

        });

        try {
            cardiologistsDB.readCardiologist().forEach(note -> cardiologists.addElement(note));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(CardiologistsFrame.this,
                    exception.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)(dimension.getWidth()-getWidth())/2, (int)(dimension.getHeight()-getHeight())/2);
    }
}