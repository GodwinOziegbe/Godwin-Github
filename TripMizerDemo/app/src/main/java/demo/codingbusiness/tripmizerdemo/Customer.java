package demo.codingbusiness.tripmizerdemo;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class Customer {
    private int Id;
    private String name;
    private String address;
    private List<Material>materialList;
    private List<WasteBinType>wasteBinTypeList;

    public Customer(int id, String name, String address, List<Material> materialList, List<WasteBinType> wasteBinTypeList) {
        Id = id;
        this.name = name;
        this.address = address;
        this.materialList = materialList;
        this.wasteBinTypeList = wasteBinTypeList;
    }

    public Customer(int id, String name, String address) {
        Id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    public List<WasteBinType> getWasteBinTypeList() {
        return wasteBinTypeList;
    }

    public void setWasteBinTypeList(List<WasteBinType> wasteBinTypeList) {
        this.wasteBinTypeList = wasteBinTypeList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", materialList=" + materialList +
                ", wasteBinTypeList=" + wasteBinTypeList +
                '}';
    }
}
