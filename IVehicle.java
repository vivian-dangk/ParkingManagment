
package ParkingManagement;

public interface IVehicle {
    void addVehicle(String bienSo, long entryTime, long exitTime, String owner, String phone, boolean isParked);
    String displayVehicle();
    double calculateFee();
}
