
package ParkingManagement;
import java.util.*;
public class Car extends Vehicle{
    private int fuelType;

    public Car(int fuelType, String BienSo, long entryTime, long exitTime, String owner, String phone, boolean isParked) {
        super(BienSo, entryTime, exitTime, owner, phone, isParked);
        this.fuelType = fuelType;
    }
    public Car() {
    }

    public void setFuelType(int fuelType) {
        this.fuelType = fuelType;
    }
    @Override
    public void addVehicle(String bienSo, long entryTime, long exitTime, String owner, String phone, boolean isParked) {
        super.addVehicle(bienSo, entryTime, exitTime, owner, phone, isParked);
        this.fuelType = 1;
    }
    @Override
    public String displayVehicle(){
        StringBuilder sb = new StringBuilder(super.displayVehicle()); 
        sb.append("Loai nhien lieu: ").append(fuelType==1 ? "Xe dien" : "Khac").append("\n");
        return sb.toString();
    }
    @Override
    public double calculateFee(){
        return super.calculateFee()*20000;
    }
    @Override
    public String PlaceVehicle(){
        if (this.fuelType==1) {
            return "Bai xe so 2 va 4 (Xe dien).";
        } else {
            return "Bai xe so 6 va 8 (Khong phai xe dien).";
        }
    }
}
