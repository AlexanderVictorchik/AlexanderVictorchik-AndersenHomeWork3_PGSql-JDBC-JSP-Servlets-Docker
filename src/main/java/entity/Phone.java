package entity;

public class Phone {
    protected int id;
    protected int price;
    protected String model;
    protected String vendor_name;
    protected String vendor_site;

    public Phone() {
    }

    public Phone(int id) {
        this.id = id;
    }


    public Phone(int price, String model, String vendor_name) {
        this.price = price;
        this.model = model;
        this.vendor_name = vendor_name;
    }

    public Phone(int id, int price, String model, String vendor_name) {
        this.id = id;
        this.price = price;
        this.model = model;
        this.vendor_name = vendor_name;
    }

    public Phone(int price, String model, String vendor_name, String vendor_site) {
        this.price = price;
        this.model = model;
        this.vendor_name = vendor_name;
        this.vendor_site = vendor_site;
    }

    public Phone(int id, int price, String model, String vendor_name, String vendor_site) {
        this.id = id;
        this.price = price;
        this.model = model;
        this.vendor_name = vendor_name;
        this.vendor_site = vendor_site;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_site() {
        return vendor_site;
    }

    public void setVendor_site(String vendor_site) {
        this.vendor_site = vendor_site;
    }
}
