package my.azamat.entity;

import java.util.concurrent.locks.ReentrantLock;

public class Pier {
    private int pierId;
    private boolean isAvailable;
    private final ReentrantLock lock = new ReentrantLock();

    public Pier(int pierId) {
        this.pierId = pierId;
        this.isAvailable = true;
    }

    public int getPierId() {
        return pierId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void unloadShip(Ship ship) {
        try {
            isAvailable = false;
            lock.lock();
            Ship currentShip = ship;
            System.out.println("Ship" + currentShip.getShipId() + " has docked for unload at the pier" + this.pierId + ".");
            while (!currentShip.getShipContainers().isEmpty()) {
                Seaport.seaportContainers.put(currentShip.unloadOneContainer());
                Thread.sleep(100);
            }
            System.out.println("Ship" + currentShip.getShipId() + " has left the pier" + this.pierId + ", unloading was ended.");
            Seaport.shipsForUpload.put(currentShip);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        isAvailable = true;
    }

    public void uploadShip(Ship ship) {
        try {
            isAvailable = false;
            lock.lock();
            Ship currentShip = ship;
            System.out.println("Ship" + currentShip.getShipId() + " has docked for upload at the pier" + this.pierId + ".");
            while (currentShip.getShipContainers().size() != currentShip.getCapacityInContainers()) {
                currentShip.uploadOneContainer(Seaport.seaportContainers.take());
                Thread.sleep(100);
            }
            System.out.println("Ship" + currentShip.getShipId() + " has left the pier" + this.pierId + ", uploading was ended.");
            System.out.println("Ship" + currentShip.getShipId() + " is leaving the seaport.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Pier{pierId=" + pierId;
    }
}
