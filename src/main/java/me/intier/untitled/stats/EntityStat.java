package me.intier.untitled.stats;

import org.bukkit.entity.Entity;

public class EntityStat {

    private double baseHealth = 100.0;
    private double currentHealth = baseHealth;
    private double healthRegen = 5.0;
    private double baseHealthBoost = 0.0;
    private double healthIncrease = 100.0;

    public void setBaseHealth(double baseHealth) {
        this.baseHealth = baseHealth;
    }

    public double getBaseHealth() {
        return this.baseHealth;
    }

    public void setBaseHealthBoost(double boost) {
        this.baseHealthBoost = boost;
    }

    public double getBaseHealthBoost() {
        return this.baseHealthBoost;
    }

    public void setHealthIncrease(double increase) {
        this.healthIncrease = increase;
    }

    public double getHealthIncrease() {
        return this.healthIncrease;
    }

    public double getFinalHealth() {
        return (baseHealth + baseHealthBoost) * (healthIncrease / 100.0);
    }

    public void setHealthRegen(double healthRegen) {
        this.healthRegen = healthRegen;
    }

    public double getHealthRegen() {
        return this.healthRegen;
    }

    public double getCurrentHealth() {
        return this.currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = Math.min(currentHealth, getFinalHealth());
    }
    public void regenerateHealth() {
        this.currentHealth += (getFinalHealth() * (healthRegen / 100.0));
        setCurrentHealth(this.currentHealth); // 최대 체력을 초과하지 않도록 조정
    }

    private double baseDefense = 0.0;
    private double baseDefenseBoost = 0.0;
    private double defenseIncrease = 100.0;

    public void setBaseDefense(double baseDefense) {
        this.baseDefense = baseDefense;
    }

    public double getBaseDefense() {
        return this.baseDefense;
    }

    public void setBaseDefenseBoost(double baseDefenseBoost) {
        this.baseDefenseBoost = baseDefenseBoost;
    }

    public double getBaseDefenseBoost() {
        return this.baseDefenseBoost;
    }

    public void setDefenseIncrease(double defenseIncrease) {
        this.defenseIncrease = defenseIncrease;
    }

    public double getDefenseIncrease() {
        return this.defenseIncrease;
    }

    public double getFinalDefense() {
        return (baseDefense + baseDefenseBoost) * (defenseIncrease / 100);
    }

    private double resistance = 1.0;

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    public double getResistance() {
        return this.resistance;
    }

    public double getFinalResistance() {
        return (1 / resistance);
    }


    private double baseDamage = 10.0;
    private double baseDamageBoost = 0.0;
    private double damageIncrease = 100.0;

    public void setBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
    }

    public double getBaseDamage() {
        return this.baseDamage;
    }

    public void setBaseDamageBoost(double boost) {
        this.baseDamageBoost = boost;
    }

    public double getBaseDamageBoost() {
        return this.baseDamageBoost;
    }

    public void setDamageIncrease(double increase) {
        this.damageIncrease = increase;
    }

    public double getDamageIncrease() {
        return this.damageIncrease;
    }

    public double getFinalDamage() {
        return (baseDamage + baseDamageBoost) * (damageIncrease / 100.0);
    }

    public double baseCriticalChance = 10.0;
    public double baseCriticalChanceBoost = 0.0;
    public double baseCriticalDamage = 100.0;
    public double baseCriticalDamageBoost = 0.0;

    public void setBaseCriticalChance(double baseCriticalChance) {
        this.baseCriticalChance = baseCriticalChance;
    }

    public double getBaseCriticalChance() {
        return this.baseCriticalChance;
    }

    public void setBaseCriticalChanceBoost(double baseCriticalChanceBoost) {
        this.baseCriticalChanceBoost = baseCriticalChanceBoost;
    }

    public double getBaseCriticalChanceBoost() {
        return this.baseCriticalChanceBoost;
    }

    public double getFinalCriticalChance() {
        return (getBaseCriticalChance() + getBaseCriticalChanceBoost()) / 100;
    }

    public void setBaseCriticalDamage(double baseCriticalDamage) {
        this.baseCriticalDamage = baseCriticalDamage;
    }

    public double getBaseCriticalDamage() {
        return this.baseCriticalDamage;
    }

    public void setBaseCriticalDamageBoost(double baseCriticalDamageBoost) {
        this.baseCriticalDamageBoost = baseCriticalDamageBoost;
    }
    public double getBaseCriticalDamageBoost() {
        return this.baseCriticalDamageBoost;
    }
    public double getFinalCriticalDamage() {
        return (baseCriticalDamage + baseCriticalDamageBoost) / 100;
    }

    private double fireDamageBonus = 100.0;
    private double waterDamageBonus = 100.0;
    private double iceDamageBonus = 100.0;
    private double dragonDamageBonus = 100.0;
    private double physicalDamageBonus = 100.0;

    private double fireResistance = 0.0;
    private double waterResistance = 0.0;
    private double iceResistance = 0.0;
    private double dragonResistance = 0.0;
    private double physicalResistance = 0.0;

    public void setFireDamageBonus(double fireDamageBonus) {
        this.fireDamageBonus = fireDamageBonus;
    }

    public double getFireDamageBonus() {
        return fireDamageBonus / 100;
    }

    public void setWaterDamageBonus(double waterDamageBonus) {
        this.waterDamageBonus = waterDamageBonus;
    }

    public double getWaterDamageBonus() {
        return waterDamageBonus / 100;
    }

    public void setIceDamageBonus(double iceDamageBonus) {
        this.iceDamageBonus = iceDamageBonus;
    }

    public double getIceDamageBonus() {
        return iceDamageBonus / 100;
    }

    public void setDragonDamageBonus(double dragonDamageBonus) {
        this.dragonDamageBonus = dragonDamageBonus;
    }

    public double getDragonDamageBonus() {
        return dragonDamageBonus / 100;
    }

    public void setPhysicalDamageBonus(double physicalDamageBonus) {
        this.physicalDamageBonus = physicalDamageBonus;
    }

    public double getPhysicalDamageBonus() {
        return physicalDamageBonus / 100;
    }

    public void setFireResistance(double fireResistance) {
        this.fireResistance = fireResistance;
    }

    public double getFireResistance() {
        return this.fireResistance;
    }

    public void setWaterResistance(double waterResistance) {
        this.waterResistance = waterResistance;
    }

    public double getWaterResistance() {
        return this.waterResistance;
    }

    public void setIceResistance(double iceResistance) {
        this.iceResistance = iceResistance;
    }

    public double getIceResistance() {
        return this.iceResistance;
    }

    public void setDragonResistance(double dragonResistance) {
        this.dragonResistance = dragonResistance;
    }

    public double getDragonResistance() {
        return this.dragonResistance;
    }

    public void setPhysicalResistance(double physicalResistance) {
        this.physicalResistance = physicalResistance;
    }

    public double getPhysicalResistance() {
        return this.physicalResistance;
    }


}