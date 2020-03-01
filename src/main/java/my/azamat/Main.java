package my.azamat;

import my.azamat.entity.*;
import my.azamat.mythread.ShipUnloadingThread;
import my.azamat.mythread.ShipUploadingThread;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Seaport seaport = new Seaport(10, 2, 2);
        System.out.println("Seaport has " + (seaport.capacityInContainers - Seaport.seaportContainers.size()) + " empty places for containers.");
        for (int i = 1; i < 101; i++) {
            try {
                Seaport.shipsForUnload.put(new Ship(i, 1 + new Random().nextInt(seaport.capacityInContainers / seaport.numberOfUploadingPiers - 1)));
                new ShipUnloadingThread().start();
                new ShipUploadingThread().start();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
