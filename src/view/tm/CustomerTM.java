package view.tm;

import javafx.scene.control.ButtonBar;

public class CustomerTM {
    private String id;
    private String name;
    private String address;
    private double salary;
    private ButtonBar buttonBar;

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, double salary, ButtonBar buttonBar) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.buttonBar = buttonBar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(ButtonBar buttonBar) {
        this.buttonBar = buttonBar;
    }
}
