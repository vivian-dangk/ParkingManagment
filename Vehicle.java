
package ParkingManagement;
import java.util.*;
import java.text.*;
public abstract class Vehicle implements IVehicle{
    Scanner nhap=new Scanner(System.in);
    private String BienSo;
    private long entryTime;
    private long exitTime;
    private String owner;
    private String phone;
    private boolean isParked;
    private SimpleDateFormat bd=new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public Vehicle(String BienSo, long entryTime, long exitTime, String owner, String phone, boolean isParked) {
        this.BienSo = BienSo;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.owner = owner;
        this.phone = phone;
        this.isParked = isParked;
    }

    public Vehicle() {
    }

    public String getBienSo() {
        return BienSo;
    }

    public String getOwner() {
        return owner;
    }

    public String getPhone() {
        return phone;
    }

    public long getEntryTime() {
        return entryTime;
    }
    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }
    public long getExitTime() {
        return exitTime;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIsParked(boolean isParked) {
        this.isParked = isParked;
    }
    
    @Override
    public void addVehicle(String bienSo, long entryTime, long exitTime, String owner, String phone, boolean isParked) {
        this.BienSo = bienSo;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.owner = owner;
        this.phone = phone;
        this.isParked = isParked;
    }
    @Override
    public String displayVehicle(){
        StringBuilder sb = new StringBuilder();
        sb.append("Bien so: ").append(BienSo)
                .append(" (Chu xe: ").append(owner)
                .append(", SDT: ").append(phone).append(")\n")
                .append("Ngay gui: ").append(bd.format(new Date(entryTime))).append("\n");
        if (isParked) {
            sb.append("Hien van dang gui.\n");
        } else {
            sb.append("Ngay ra: ").append(bd.format(new Date(exitTime))).append("\n");
        }
        return sb.toString();
    }
    @Override
    public double calculateFee(){
        if (exitTime == 0) {
            return 0; 
        }
        long sogio = (exitTime - entryTime) / (1000 * 60 * 60);
        long sogiotron=Math.round(sogio);
        return sogiotron;
    }
    public abstract String PlaceVehicle();
}
