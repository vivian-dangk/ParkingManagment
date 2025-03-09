
package ParkingManagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Processor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ParkingLotGUI gui = new ParkingLotGUI();
            gui.setVisible(true);
        });
    }
}
