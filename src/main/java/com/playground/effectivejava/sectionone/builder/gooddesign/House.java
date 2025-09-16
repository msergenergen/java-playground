package com.playground.effectivejava.sectionone.builder.gooddesign;

import java.security.PublicKey;

public class House {

    private final int rooms; //mandatory
    private final boolean hasGarage; //optional
    private final boolean hasSwimmingPool; //optional
    private final boolean hasGarden; //optional

    public static Builder builder(int rooms) {
        return new Builder(rooms);
    }

    private static class Builder {

        private final int rooms;
        private boolean hasGarage;
        private boolean hasSwimmingPool;
        private boolean hasGarden;

        public Builder(int rooms) {
            this.rooms = rooms;
        }

        public Builder hasGarage(boolean val) {
            this.hasGarage = val;
            return this;
        }

        public Builder hasSwimmingPool(boolean val) {
            this.hasSwimmingPool = val;
            return this;
        }

        public Builder hasGarden(boolean val) {
            this.hasGarden = val;
            return this;
        }

        public House build() {
            return new House(this);
        }

    }

    private House(Builder builder) {
        rooms = builder.rooms;
        hasGarage = builder.hasGarage;
        hasSwimmingPool = builder.hasSwimmingPool;
        hasGarden = builder.hasGarden;

    }

    @Override
    public String toString() {
        return "House{" +
                "rooms=" + rooms +
                ", hasGarage=" + hasGarage +
                ", hasSwimmingPool=" + hasSwimmingPool +
                ", hasGarden=" + hasGarden +
                '}';
    }

    public static void main(String[] args) {
        House house = House.builder(3)
                .hasGarage(true)
                .hasSwimmingPool(true)
                .hasGarden(true)
                .build();

        System.out.println(house);
    }


}
