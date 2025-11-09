package com.playground.effectivejava.sectionone.staticfactory;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class Car {

    private final String brand;
    private final String brandModel;
    private final FuelType fuelType;
    private final int year;

    private Car(String brand, String brandModel, FuelType fuelType, int year) {
        this.brand = brand;
        this.brandModel = brandModel;
        this.fuelType = fuelType;
        this.year = year;
    }

    public static Car diesel(String brand, String model, int year) {
        return new Car(brand, model, FuelType.DIESEL, year);
    }

    public static Car electric(String brand, String model, int year) {
        return new Car(brand, model, FuelType.ELECTRIC, year);
    }

    public static Car of(String brand, String model, FuelType fuelType, int year) {
        return new Car(brand, model, fuelType, year);
    }

    public static Car gasoline(String brand, String model, int year) {
        return new Car(brand, model, FuelType.GASOLINE, year);
    }

    public static Car hybrid(String brand, String model, int year) {
        return new Car(brand, model, FuelType.HYBRID, year);
    }

    public static void main(String[] args) {
        Car car1 = Car.of("Ford", "Mustang", FuelType.GASOLINE, 2025);
        Car car2 = Car.electric("Tesla", "Model 3", 2024);
        Car car3 = Car.hybrid("Toyota", "Prius", 2025);
        Car car4 = Car.diesel("BMW", "X5", 2025);

        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car3);
        System.out.println(car4);
    }

}
