package my.azamat.entity;

public class Container {
    private int containerId;

    public Container(int containerId) {
        this.containerId = containerId;
    }

    public int getConteinerId() {
        return containerId;
    }

    @Override
    public String toString() {
        return "Container" + containerId;
    }
}
