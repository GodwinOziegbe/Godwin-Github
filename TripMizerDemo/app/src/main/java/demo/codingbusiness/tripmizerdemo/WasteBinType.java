package demo.codingbusiness.tripmizerdemo;

public class WasteBinType {
    private int Id;
    private String name;

    public WasteBinType(int id, String name) {
        Id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "WasteBinType{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
