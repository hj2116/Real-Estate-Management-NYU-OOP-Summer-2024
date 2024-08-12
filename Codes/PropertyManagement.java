import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class PropertyManagement extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField addressField;
    private JTextField typeField;
    private JTextField sizeField;
    private JTextField rentalPriceField;
    private JTextField descriptionField;
    private Map<Integer, Property> properties = new HashMap<>();
    private int currentId = 1;
    private final String FILE_NAME = "properties.ser";

    public PropertyManagement() {
        loadProperties();

        setTitle("Property Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Address", "Type", "Size", "Rental Price", "Description"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createTitledBorder("Property Form"));
        contentPane.add(inputPanel, BorderLayout.NORTH);
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));

        JLabel lblAddress = new JLabel("Address:");
        inputPanel.add(lblAddress);

        addressField = new JTextField();
        inputPanel.add(addressField);
        addressField.setColumns(10);

        JLabel lblType = new JLabel("Type:");
        inputPanel.add(lblType);

        typeField = new JTextField();
        inputPanel.add(typeField);
        typeField.setColumns(10);

        JLabel lblSize = new JLabel("Size:");
        inputPanel.add(lblSize);

        sizeField = new JTextField();
        inputPanel.add(sizeField);
        sizeField.setColumns(10);

        JLabel lblRentalPrice = new JLabel("Rental Price:");
        inputPanel.add(lblRentalPrice);

        rentalPriceField = new JTextField();
        inputPanel.add(rentalPriceField);
        rentalPriceField.setColumns(10);

        JLabel lblDescription = new JLabel("Description:");
        inputPanel.add(lblDescription);

        descriptionField = new JTextField();
        inputPanel.add(descriptionField);
        descriptionField.setColumns(10);

        JButton btnCreateProperty = new JButton("Create Property");
        btnCreateProperty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createProperty();
            }
        });
        inputPanel.add(btnCreateProperty);

        JButton btnEditProperty = new JButton("Edit Property");
        btnEditProperty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editProperty();
            }
        });
        inputPanel.add(btnEditProperty);

        JButton btnUpdateProperty = new JButton("Update Property");
        btnUpdateProperty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateProperty();
            }
        });
        inputPanel.add(btnUpdateProperty);

        JButton btnDeleteProperty = new JButton("Delete Property");
        btnDeleteProperty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteProperty();
            }
        });
        inputPanel.add(btnDeleteProperty);

        populateTable();
    }

    private void createProperty() {
        String address = addressField.getText();
        String type = typeField.getText();
        double size = Double.parseDouble(sizeField.getText());
        double rentalPrice = Double.parseDouble(rentalPriceField.getText());
        String description = descriptionField.getText();

        Property property = new Property(currentId++, address, type, size, rentalPrice, description);
        properties.put(property.getId(), property);
        saveProperties();

        Object[] rowData = {
            property.getId(),
            property.getAddress(),
            property.getType(),
            property.getSize(),
            property.getRentalPrice(),
            property.getDescription()
        };
        tableModel.addRow(rowData);

        clearFormFields();
    }

    private void editProperty() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int propertyId = (int) tableModel.getValueAt(selectedRow, 0);
            Property property = properties.get(propertyId);

            if (property != null) {
                addressField.setText(property.getAddress());
                typeField.setText(property.getType());
                sizeField.setText(String.valueOf(property.getSize()));
                rentalPriceField.setText(String.valueOf(property.getRentalPrice()));
                descriptionField.setText(property.getDescription());
            }
        }
    }

    private void updateProperty() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int propertyId = (int) tableModel.getValueAt(selectedRow, 0);

            String address = addressField.getText();
            String type = typeField.getText();
            double size = Double.parseDouble(sizeField.getText());
            double rentalPrice = Double.parseDouble(rentalPriceField.getText());
            String description = descriptionField.getText();

            Property property = new Property(propertyId, address, type, size, rentalPrice, description);
            properties.put(propertyId, property);
            saveProperties();

            tableModel.setValueAt(address, selectedRow, 1);
            tableModel.setValueAt(type, selectedRow, 2);
            tableModel.setValueAt(size, selectedRow, 3);
            tableModel.setValueAt(rentalPrice, selectedRow, 4);
            tableModel.setValueAt(description, selectedRow, 5);

            clearFormFields();
        }
    }

    private void deleteProperty() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int propertyId = (int) tableModel.getValueAt(selectedRow, 0);
            properties.remove(propertyId);
            saveProperties();
            tableModel.removeRow(selectedRow);
        }
    }

    private void populateTable() {
        for (Property property : properties.values()) {
            Object[] rowData = {
                property.getId(),
                property.getAddress(),
                property.getType(),
                property.getSize(),
                property.getRentalPrice(),
                property.getDescription()
            };
            tableModel.addRow(rowData);
        }
    }

    private void clearFormFields() {
        addressField.setText("");
        typeField.setText("");
        sizeField.setText("");
        rentalPriceField.setText("");
        descriptionField.setText("");
    }

    public Property readProperty(int id) {
        return properties.get(id);
    }

    private void saveProperties() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(properties);
            oos.writeInt(currentId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadProperties() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            properties = (HashMap<Integer, Property>) ois.readObject();
            currentId = ois.readInt();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PropertyManagement frame = new PropertyManagement();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}