package my.azamat.mythread;

import my.azamat.entity.Pier;
import my.azamat.entity.Seaport;

public class ShipUnloadingThread extends Thread {

    @Override
    public void run() {
        try {
            boolean flag = true;
            while (flag) {
                for (Pier pier : Seaport.unloadingPiers) {
                    if (pier.isAvailable()) {
                        pier.unloadShip(Seaport.shipsForUnload.take());
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
