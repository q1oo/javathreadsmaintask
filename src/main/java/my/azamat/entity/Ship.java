package my.azamat.entity;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import my.azamat.myexception.OverloadCapacityException;

public class Ship {
    private int shipId;
    private int capacityInContainers;
    private BlockingQueue<Container> shipContainers;

    public Ship(int shipId, int capacityInContainers) {
        this.shipId = shipId;
        this.capacityInContainers = capacityInContainers;
        this.shipContainers = new ArrayBlockingQueue<>(this.capacityInContainers);
        for (int i = 1; i <= this.capacityInContainers; i++) {
            try {
                this.shipContainers.put(new Container(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("New ship" + this.shipId + " is arriving to the seaport.");
    }

    public int getShipId() {
        return shipId;
    }

    public int getCapacityInContainers() {
        return capacityInContainers;
    }

    public ArrayBlockingQueue<Container> getShipContainers() {
        return (ArrayBlockingQueue<Container>) shipContainers;
    }

    public void setShipContainers(ArrayBlockingQueue<Container> shipContainers) throws OverloadCapacityException {
        if (shipContainers.size() <= this.capacityInContainers) {
            this.shipContainers = shipContainers;
        } else {
            throw new OverloadCapacityException("The ship is overloaded.");
        }
    }

    @Override
    public String toString() {
        return "Ship{id=" + shipId +
                ", capacityInContainers=" + capacityInContainers +
                ", number of seaportContainers on board=" + shipContainers.size() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return shipId == ship.shipId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipId);
    }

    public Container unloadOneContainer() throws InterruptedException {
        return shipContainers.take();
    }

    public void uploadOneContainer(Container container) throws InterruptedException {
        shipContainers.put(container);
    }
}
