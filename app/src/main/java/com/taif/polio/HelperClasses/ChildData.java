package com.taif.polio.HelperClasses;

public class ChildData {
    String Name, F_Name, M_Name, DateofBirth, Age, Address, PhoneNo, id;

    public ChildData() {
    }

    public ChildData(String name, String f_Name, String m_Name, String dateofBirth, String age, String address, String phoneNo, String id) {
        Name = name;
        F_Name = f_Name;
        M_Name = m_Name;
        DateofBirth = dateofBirth;
        Age = age;
        Address = address;
        PhoneNo = phoneNo;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getF_Name() {
        return F_Name;
    }

    public void setF_Name(String f_Name) {
        F_Name = f_Name;
    }

    public String getM_Name() {
        return M_Name;
    }

    public void setM_Name(String m_Name) {
        M_Name = m_Name;
    }

    public String getDateofBirth() {
        return DateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        DateofBirth = dateofBirth;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}