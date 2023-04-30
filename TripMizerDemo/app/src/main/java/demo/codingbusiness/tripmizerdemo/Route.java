package demo.codingbusiness.tripmizerdemo;

import java.util.List;

public class Route {
    private int Id;
    private String name;
    private String driving_date;
    private String driving_day;
    private List<Customer> customerList;

    public Route(int id, String name, String driving_date, String driving_day, List<Customer> customerList) {
        Id = id;
        this.name = name;
        this.driving_date = driving_date;
        this.driving_day = driving_day;
        this.customerList = customerList;
    }

    public Route(String name, String driving_date, String driving_day, List<Customer> customerList) {
        this.name = name;
        this.driving_date = driving_date;
        this.driving_day = driving_day;
        this.customerList = customerList;
    }

    public Route(int id,String name, String driving_day, List<Customer> customerList) {
        this.Id= id;
        this.name = name;
        this.driving_day = driving_day;
        this.customerList = customerList;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriving_date() {
        return driving_date;
    }

    public void setDriving_date(String driving_date) {
        this.driving_date = driving_date;
    }

    public String getDriving_day() {
        return driving_day;
    }

    public void setDriving_day(String driving_day) {
        this.driving_day = driving_day;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return "Route{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", driving_date='" + driving_date + '\'' +
                ", driving_day='" + driving_day + '\'' +
                ", customerList=" + customerList +
                '}';
    }
}
