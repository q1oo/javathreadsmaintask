package my.azamat.mythread;

import my.azamat.entity.Pier;
import my.azamat.entity.Seaport;

public class ShipUploadingThread extends Thread {

    @Override
    public void run() {
        try {
            boolean flag = true;
            while (flag) {
                for (Pier pier : Seaport.uploadingPiers) {
                    if (pier.isAvailable()) {
                        pier.uploadShip(Seaport.shipsForUpload.take());
                        flag = false;
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
