package com.antonioleiva.daggerexample.app.samples;


import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

public class CoffeeApp {
    @Singleton
    @Component(modules = { DripCoffeeModule.class })
    public interface Coffee {
        CoffeeMaker maker();
    }

    public static void main(String[] args) {
        Coffee coffee = DaggerCoffeeApp_Coffee.builder().build();
        coffee.maker().brew();
    }

}

class CoffeeMaker {
    private final Lazy<Heater> heater;
    private final Pump pump;

    @Inject
    public CoffeeMaker(Lazy<Heater> heater, Pump pump) {
        this.heater = heater;
        this.pump = pump;
    }

    public void brew() {
        heater.get().on();
        pump.pump();
        System.out.println("pump ==> coffee ==> pump");
        heater.get().off();
    }
}

interface Heater {
    void on();
    void off();
    boolean isHot();
}

class ElectricHeater implements Heater {
    boolean isHot = false;

    @Override
    public void on() {
        System.out.println("~~~~ isHoting ~~~~");
        isHot = true;
    }

    @Override
    public void off() {
        isHot = true;
    }

    @Override
    public boolean isHot() {
        return isHot;
    }
}

interface Pump {
    void pump();
}

class Thermosiphon implements Pump {
    private final Heater mHeater;

    @Inject
    public Thermosiphon(Heater heater) {
        mHeater = heater;
    }

    @Override
    public void pump() {
        if (mHeater.isHot()) {
            System.out.println("==> pumping ==>");
        }
    }
}

@Module(includes = PumpModule.class)
class DripCoffeeModule {
    @Provides @Singleton
    Heater provideHeater() {
        return new ElectricHeater();
    }
}

@Module
class PumpModule {
    @Provides Pump providePump(Thermosiphon pump) {
        return pump;
    }
}
