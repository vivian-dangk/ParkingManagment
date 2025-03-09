
package ParkingManagement;
import java.util.*;
import java.text.*;
import java.io.*;
public class ParkingLot {
    ArrayList<Vehicle> danhsach=new ArrayList<>();
    Scanner nhap=new Scanner(System.in);
    SimpleDateFormat bd=new SimpleDateFormat("dd/MM/yyyy HH:mm");
    public void addVehicle(Vehicle vehicle) {
        danhsach.add(vehicle);
        System.out.println("Da them xe: " + vehicle.getBienSo());
    }
    public void removeVehicle(String bienSo) {
        Vehicle found = null;
        for (Vehicle v : danhsach) {
            if (v.getBienSo().equalsIgnoreCase(bienSo)) {
                found = v;
                break;
            }
        }

        if (found != null) {
            danhsach.remove(found);
            System.out.println("Xe " + bienSo + " da roi bai. Phi gui xe: " + found.calculateFee() + " VND");
        } else {
            System.out.println("Khong tim thay bien so: " + bienSo);
        }
    }
    public String displayAllVehicles() {
        if (danhsach.isEmpty()) {
            return "Bai xe trong.\n";
        }

    StringBuilder sb = new StringBuilder();
        for (Vehicle v : danhsach) {
            sb.append(v.displayVehicle()).append("\n----------------------\n");
        }
        return sb.toString();
    }
    public Vehicle findVehicle(String Bienso) {
        for (Vehicle v : danhsach) {
            if (v.getBienSo().equals(Bienso)) {
                return v;
            }
        }
        return null;
    }
    public double calculateTotalFee() {
        double total = 0;
        for (Vehicle v : danhsach) {
            total += v.calculateFee();
        }
        return total;
    }
    public String countVehiclesByType() {
        int carCount = 0, bikeCount = 0;
        for (Vehicle v : danhsach) {
            if (v instanceof Car) {
                carCount++;
            } else if (v instanceof Other) {
                bikeCount++;
            }
        }
        return "So o to: " + carCount + "\nSo xe khac: " + bikeCount + "\n";
    }
    public void exportToFile(String ten) {
        SimpleDateFormat bd = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // Định dạng thời gian
        try (FileWriter writer = new FileWriter(ten)) {
        writer.write("Biển số,Chủ xe,SĐT,Thời gian vào,Thời gian ra,Loại xe\n");
        for (Vehicle v : danhsach) {
            String entryTimeFormatted = bd.format(new Date(v.getEntryTime()));
            String exitTimeFormatted = v.getExitTime() == 0 ? "Chưa rời" : bd.format(new Date(v.getExitTime()));
            String vehicleType = (v instanceof Car) ? "Car" : "Other";
            writer.write(
                v.getBienSo() + "," +
                v.getOwner() + "," +
                v.getPhone() + "," +
                entryTimeFormatted + "," +
                exitTimeFormatted + "," +
                vehicleType + "\n"
            );
        }
        System.out.println("Xuat danh sach thanh cong.");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }
    public ArrayList<Vehicle> getDanhSach() {
        return danhsach;
    }
    
}
