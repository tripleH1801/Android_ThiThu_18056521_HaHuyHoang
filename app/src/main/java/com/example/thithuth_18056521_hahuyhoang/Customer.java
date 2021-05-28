package com.example.thithuth_18056521_hahuyhoang;

import android.os.Parcel;
import android.os.Parcelable;

public class Customer implements Parcelable {
    private int id;
    private String name;
    private int money;
    private int age;

    public Customer() {
    }

    public Customer(String name, int money, int age) {
        this.name = name;
        this.money = money;
        this.age = age;
    }

    protected Customer(Parcel in) {
        id = in.readInt();
        name = in.readString();
        money = in.readInt();
        age = in.readInt();
    }

    public static final Parcelable.Creator<Customer> CREATOR = new Parcelable.Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(money);
        dest.writeInt(age);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", age=" + age +
                '}';
    }
}
