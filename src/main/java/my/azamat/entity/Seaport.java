package my.azamat.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Seaport {
    public final int capacityInContainers;
    public final int numberOfUnloadingPiers;
    public final int numberOfUploadingPiers;
    public static BlockingQueue<Container> seaportContainers;
    public static List<Pier> unloadingPiers;
    public static List<Pier> uploadingPiers;
    public static BlockingQueue<Ship> shipsForUnload;
    public static BlockingQueue<Ship> shipsForUpload;

    public Seaport(int capacityInContainers, int numberOfUnloadingPiers, int numberOfUploadingPiers) {
        this.capacityInContainers = capacityInContainers;
        this.numberOfUnloadingPiers = numberOfUnloadingPiers;
        this.numberOfUploadingPiers = numberOfUploadingPiers;
        this.seaportContainers = new ArrayBlockingQueue<>(capacityInContainers);
        this.shipsForUnload = new LinkedBlockingQueue<>();
        this.shipsForUpload = new LinkedBlockingQueue<>();
        this.unloadingPiers = new ArrayList<>();
        for (int i = 1; i <= numberOfUnloadingPiers; i++) {
            unloadingPiers.add(new Pier(i));
        }
        this.uploadingPiers = new ArrayList<>();
        for (int i = numberOfUnloadingPiers + 1; i <= numberOfUploadingPiers + numberOfUnloadingPiers; i++) {
            uploadingPiers.add(new Pier(i));
        }
    }
}
