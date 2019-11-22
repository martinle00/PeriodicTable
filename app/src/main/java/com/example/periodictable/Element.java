package com.example.periodictable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.reflect.Array;

// Attributes of elements from the API received
@Entity
public class Element {
    @PrimaryKey
    private int atomicNumber;
    private String symbol;
    private String name;
    private String atomicRadius;
    private String boilingPoint;
    private String bondingType;
    private String density;
    private String electronAffinity;
    private String electronNegativity;
    private String electronicConfiguration;
    private String groupBlock;
    private String ionRadius;
    private String ionizationEnergy;
    private String meltingPoint;
    private String oxidationStates;
    private String standardState;
    private String vanDerWaalsRadius;
    private String yearDiscovered;

    // Getters and setters
    public int getAtomicNumber() {
        return atomicNumber;
    }

    public void setAtomicNumber(int atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAtomicRadius() {
        return atomicRadius;
    }

    public void setAtomicRadius(String atomicRadius) {
        this.atomicRadius = atomicRadius;
    }

    public String getBoilingPoint() {
        return boilingPoint;
    }

    public void setBoilingPoint(String boilingPoint) {
        this.boilingPoint = boilingPoint;
    }

    public String getBondingType() {
        return bondingType;
    }

    public void setBondingType(String bondingType) {
        this.bondingType = bondingType;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getElectronAffinity() {
        return electronAffinity;
    }

    public void setElectronAffinity(String electronAffinity) {
        this.electronAffinity = electronAffinity;
    }

    public String getElectronNegativity() {
        return electronNegativity;
    }

    public void setElectronNegativity(String electronNegativity) {
        this.electronNegativity = electronNegativity;
    }

    public String getElectronicConfiguration() {
        return electronicConfiguration;
    }

    public void setElectronicConfiguration(String electronicConfiguration) {
        this.electronicConfiguration = electronicConfiguration;
    }

    public String getGroupBlock() {
        return groupBlock;
    }

    public void setGroupBlock(String groupBlock) {
        this.groupBlock = groupBlock;
    }

    public String getIonRadius() {
        return ionRadius;
    }

    public void setIonRadius(String ionRadius) {
        this.ionRadius = ionRadius;
    }

    public String getIonizationEnergy() {
        return ionizationEnergy;
    }

    public void setIonizationEnergy(String ionizationEnergy) {
        this.ionizationEnergy = ionizationEnergy;
    }

    public String getMeltingPoint() {
        return meltingPoint;
    }

    public void setMeltingPoint(String meltingPoint) {
        this.meltingPoint = meltingPoint;
    }

    public String getOxidationStates() {
        return oxidationStates;
    }

    public void setOxidationStates(String oxidationStates) {
        this.oxidationStates = oxidationStates;
    }

    public String getStandardState() {
        return standardState;
    }

    public void setStandardState(String standardState) {
        this.standardState = standardState;
    }

    public String getVanDerWaalsRadius() {
        return vanDerWaalsRadius;
    }

    public void setVanDerWaalsRadius(String vanDerWaalsRadius) {
        this.vanDerWaalsRadius = vanDerWaalsRadius;
    }

    public String getYearDiscovered() {
        return yearDiscovered;
    }

    public void setYearDiscovered(String yearDiscovered) {
        this.yearDiscovered = yearDiscovered;
    }
}