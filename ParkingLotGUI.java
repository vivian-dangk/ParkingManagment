
package ParkingManagement;
import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.awt.event.*;
import java.util.ArrayList;
public class ParkingLotGUI extends JFrame{
    private ParkingLot parkingLot;
    private JTextField txtLicensePlate, txtOwner, txtPhone, txtEntryTime, txtExitTime;
    private JTextArea txtOutput;
    private JComboBox<String> cbVehicleType, cbFuelOrType;

    public ParkingLotGUI() {
        parkingLot = new ParkingLot();
        setTitle("Parking Lot Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thong tin xe"));

        txtLicensePlate = new JTextField();
        txtOwner = new JTextField();
        txtPhone = new JTextField();
        txtEntryTime = new JTextField();
        txtExitTime = new JTextField();
        cbVehicleType = new JComboBox<>(new String[]{"Car", "Other"}); 
        cbFuelOrType = new JComboBox<>(new String[]{"1. Xe đien", "2. Nhien lieu khac", "3. Xe may", "4. Xe đap"});
        inputPanel.add(new JLabel("Bien so:"));
        inputPanel.add(txtLicensePlate);
        inputPanel.add(new JLabel("Chu xe:"));
        inputPanel.add(txtOwner);
        inputPanel.add(new JLabel("SDT:"));
        inputPanel.add(txtPhone);
        inputPanel.add(new JLabel("Thoi gian vao (dd/MM/yyyy HH:mm):"));
        inputPanel.add(txtEntryTime);
        inputPanel.add(new JLabel("Thoi gian ra (dd/MM/yyyy HH:mm hoac đe trong):"));
        inputPanel.add(txtExitTime);
        inputPanel.add(new JLabel("Loai xe (Car/Other):"));
        inputPanel.add(cbVehicleType);
        inputPanel.add(new JLabel("Nhiên lieu/Loai xe:"));
        inputPanel.add(cbFuelOrType);
        txtOutput = new JTextArea(15, 50);
        txtOutput.setEditable(false);
        txtOutput.setBackground(new Color(255,255,224));
        txtOutput.setForeground(new Color(222,120,174));
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Them Xe");
        buttonPanel.add(btnAdd);
        JButton btnRemove = new JButton("Xoa Xe");
        buttonPanel.add(btnRemove);
        JButton btnDisplay = new JButton("Hien Thi");
        buttonPanel.add(btnDisplay);
        JButton btnCalculate = new JButton("Tinh Tong Phi");
        buttonPanel.add(btnCalculate);
        JButton btnExport = new JButton("Xuat File");
        buttonPanel.add(btnExport);
        JButton btnSearch = new JButton("Tim Xe");
        buttonPanel.add(btnSearch);
        JButton btnCountVehicles = new JButton("Hiển Thị Số Lượng");
        buttonPanel.add(btnCountVehicles);
        add(inputPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        btnAdd.addActionListener(e -> {
            try {
                String licensePlate = txtLicensePlate.getText();
                String owner = txtOwner.getText();
                String phone = txtPhone.getText();
                String entryTimeStr = txtEntryTime.getText();
                String exitTimeStr = txtExitTime.getText();
                String vehicleType = cbVehicleType.getSelectedItem().toString();
                int fuelOrType = Integer.parseInt(cbFuelOrType.getSelectedItem().toString().substring(0, 1));

                SimpleDateFormat bd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                long entryTime = bd.parse(entryTimeStr).getTime();
                long exitTime = exitTimeStr.isEmpty() ? 0 : bd.parse(exitTimeStr).getTime();

                if (vehicleType.equalsIgnoreCase("Car")) {
                    Vehicle car = new Car(fuelOrType, licensePlate, entryTime, exitTime, owner, phone, exitTime == 0);
                    parkingLot.addVehicle(car);
                } else {
                    Vehicle other = new Other(fuelOrType == 3 ? 1 : 2, licensePlate, entryTime, exitTime, owner, phone, exitTime == 0);
                    parkingLot.addVehicle(other);
                }

                txtOutput.append("Đa them xe: " + licensePlate + "\n");
                txtLicensePlate.setText("");
                txtOwner.setText("");
                txtPhone.setText("");
                txtEntryTime.setText("");
                txtExitTime.setText("");
                cbVehicleType.setSelectedIndex(0);
                cbFuelOrType.setSelectedIndex(0);
            } catch (Exception ex) {
                txtOutput.append("Loi khi them xe: " + ex.getMessage() + "\n");
            }
        });
        btnRemove.addActionListener(e -> {
            String licensePlate = txtLicensePlate.getText();
            if (licensePlate.isEmpty()) {
                txtOutput.append("Bien so khong đuoc đe trong!\n");
                return;
            }
            parkingLot.removeVehicle(licensePlate);
            txtOutput.append("Đa xoa xe: " + licensePlate + "\n");
        });
        btnDisplay.addActionListener(e -> {
            for (Vehicle v : parkingLot.getDanhSach()) {
                txtOutput.append(v.displayVehicle());
                txtOutput.append("Bai xe: " + v.PlaceVehicle() + "\n");
                txtOutput.append("----------------------\n");
            }
        });
        btnCalculate.addActionListener(e -> {
            if (parkingLot.getDanhSach().isEmpty()) {
                txtOutput.append("Danh sách xe trống, không thể tính phí!\n");
                return;
            }

            txtOutput.append("Danh sách xe và phí giữ xe:\n");
            for (Vehicle v : parkingLot.getDanhSach()) {
                String licensePlate = v.getBienSo();
                double fee = v.calculateFee();
                txtOutput.append("Biển số: " + licensePlate + ", Phí giữ xe: " + fee + " VND\n");
            }
            txtOutput.append("----------------------\n");
        });
        btnExport.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog(this, "Nhập tên file để xuất:");
            if (fileName != null) {
                parkingLot.exportToFile(fileName);
                txtOutput.append("Đã xuất dữ liệu ra file: " + fileName + "\n");
            }
        });
        btnSearch.addActionListener(e -> {
            String licensePlate = txtLicensePlate.getText();
            if (licensePlate.isEmpty()) {
                txtOutput.append("Vui long nhap bien so xe đe tim!\n");
                return;
            }
            Vehicle foundVehicle = parkingLot.findVehicle(licensePlate);
            if (foundVehicle != null) {
                txtOutput.append("Xe tim thay:\n");
                txtOutput.append(foundVehicle.displayVehicle());
                txtOutput.append("Bai xe: " + foundVehicle.PlaceVehicle() + "\n");
            } else {
                txtOutput.append("Khong tim thay xe voi bien so: " + licensePlate + "\n");
            }
        });
        btnCountVehicles.addActionListener(e -> {
            String counts = parkingLot.countVehiclesByType(); 
            txtOutput.append("Danh sach xe hien co:\n" + counts + "\n");
        });
    }
}
