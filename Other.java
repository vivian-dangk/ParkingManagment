
package ParkingManagement;
import java.util.*;
public class Other extends Vehicle{
    private int type;

    public Other(int type, String BienSo, long entryTime, long exitTime, String owner, String phone, boolean isParked) {
        super(BienSo, entryTime, exitTime, owner, phone, isParked);
        this.type = type;
    }

    public Other() {
    }

    public void setType(int type) {
        this.type = type;
    }
    @Override
    public void addVehicle(String bienSo, long entryTime, long exitTime, String owner, String phone, boolean isParked) {
        super.addVehicle(bienSo, entryTime, exitTime, owner, phone, isParked);
        this.type = 1;
    }
    @Override
    public String displayVehicle(){
        StringBuilder sb = new StringBuilder(super.displayVehicle()); 
        sb.append("Loai xe: ").append(type == 1 ? "Xe may" : "Xe dap").append("\n");
        return sb.toString();
    }
    @Override
    public double calculateFee(){
        double fee=0;
        if(this.type==1){
            fee= super.calculateFee()*10000;
        }else if(this.type==2){
            fee= super.calculateFee()*5000;
        }
        return fee;
    }
    @Override
    public String PlaceVehicle(){
        if(this.type==1){
            return "Bai xe so 3 va 5.";
        }else if(this.type==2){
            return "Bai xe so 1.";
        }
        return "Khong xac dinh.";
    }
}
