import java.io.Serializable;

public class Property implements Serializable {
    private int id;
    private String address;
    private String type;
    private double size;
    private double rentalPrice;
    private String description;

    public Property(int id, String address, String type, double size, double rentalPrice, String description) {
        this.id = id;
        this.address = address;
        this.type = type;
        this.size = size;
        this.rentalPrice = rentalPrice;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}