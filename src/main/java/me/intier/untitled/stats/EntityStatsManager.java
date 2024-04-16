package me.intier.untitled.stats;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class EntityStatsManager {
    private Map<UUID, EntityStat> playerStatsMap = new HashMap<>();
    private final JavaPlugin plugin;
    private final File statsFile;
    private Properties statsProps = new Properties();

    public EntityStatsManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.statsFile = new File(plugin.getDataFolder(), "playerStats.properties");
        loadStats();
        scheduleRegeneration();
    }

    private void loadStats() {
        try {
            if (!statsFile.exists()) {
                statsFile.getParentFile().mkdirs();
                statsFile.createNewFile();
            }
            statsProps.load(new FileInputStream(statsFile));

            statsProps.forEach((key, value) -> {
                String keyString = (String) key;
                if (keyString.startsWith("Player.")) {
                    String[] parts = keyString.split("\\.");
                    if (parts.length == 4) {  // "Player.UUID.Category.StatName" 구조
                        UUID playerId = UUID.fromString(parts[1]);
                        String category = parts[2];
                        String statName = parts[3];
                        double statValue = Double.parseDouble((String) value);
                        EntityStat stats = playerStatsMap.computeIfAbsent(playerId, k -> new EntityStat());

                        switch (category + "." + statName) {
                            // 체력
                            case "Health.Base":
                                stats.setBaseHealth(statValue);
                                break;
                            case "Health.Boost":
                                stats.setBaseHealthBoost(statValue);
                                break;
                            case "Health.Increase":
                                stats.setHealthIncrease(statValue);
                                break;
                            case "Health.Regen":
                                stats.setHealthRegen(statValue);
                                break;
                            // 공격력
                            case "Damage.Base":
                                stats.setBaseDamage(statValue);
                                break;
                            case "Damage.Boost":
                                stats.setBaseDamageBoost(statValue);
                                break;
                            case "Damage.Increase":
                                stats.setDamageIncrease(statValue);
                                break;
                            // 방어력
                            case "Defense.Base":
                                stats.setBaseDefense(statValue);
                                break;
                            case "Defense.Boost":
                                stats.setBaseDefenseBoost(statValue);
                                break;
                            case "Defense.Increase":
                                stats.setDefenseIncrease(statValue);
                                break;
                            // 저항
                            case "Resistance":
                                stats.setResistance(statValue);
                                break;
                            // 속성 피해 보너스
                            case "Bonus.FireDamage":
                                stats.setFireDamageBonus(statValue);
                                break;
                            case "Bonus.WaterDamage":
                                stats.setWaterDamageBonus(statValue);
                                break;
                            case "Bonus.IceDamage":
                                stats.setIceDamageBonus(statValue);
                                break;
                            case "Bonus.DragonDamage":
                                stats.setDragonDamageBonus(statValue);
                                break;
                            case "Bonus.PhysicalDamage":
                                stats.setPhysicalDamageBonus(statValue);
                                break;
                            // 속성 내성
                            case "Resistance.Fire":
                                stats.setFireResistance(statValue);
                                break;
                            case "Resistance.Water":
                                stats.setWaterResistance(statValue);
                                break;
                            case "Resistance.Ice":
                                stats.setIceResistance(statValue);
                                break;
                            case "Resistance.Dragon":
                                stats.setDragonResistance(statValue);
                                break;
                            case "Resistance.Physical":
                                stats.setPhysicalResistance(statValue);
                                break;
                            // 회심
                            case "Critical.Chance.Base":
                                stats.setBaseCriticalChance(statValue);
                                break;
                            case "Critical.Chance.Boost":
                                stats.setBaseCriticalChanceBoost(statValue);
                                break;
                            case "Critical.Damage.Base":
                                stats.setBaseCriticalDamage(statValue);
                                break;
                            case "Critical.Damage.Boost":
                                stats.setBaseCriticalDamageBoost(statValue);
                                break;
                        }
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveStats() {
        playerStatsMap.forEach((uuid, stats) -> {
            String prefix = "Player." + uuid.toString() + "."; // UUID를 섹션처럼 사용

            // 체력
            statsProps.setProperty(prefix + "Health.Base", String.valueOf(stats.getBaseHealth()));
            statsProps.setProperty(prefix + "Health.Boost", String.valueOf(stats.getBaseHealthBoost()));
            statsProps.setProperty(prefix + "Health.Increase", String.valueOf(stats.getHealthIncrease()));
            statsProps.setProperty(prefix + "Health.Regen", String.valueOf(stats.getHealthRegen()));
            // 공격력
            statsProps.setProperty(prefix + "Damage.Base", String.valueOf(stats.getBaseDamage()));
            statsProps.setProperty(prefix + "Damage.Boost", String.valueOf(stats.getBaseDamageBoost()));
            statsProps.setProperty(prefix + "Damage.Increase", String.valueOf(stats.getDamageIncrease()));
            // 방어력
            statsProps.setProperty(prefix + "Defense.Base", String.valueOf(stats.getBaseDefense()));
            statsProps.setProperty(prefix + "Defense.Boost", String.valueOf(stats.getBaseDefenseBoost()));
            statsProps.setProperty(prefix + "Defense.Increase", String.valueOf(stats.getDefenseIncrease()));
            // 저항
            statsProps.setProperty(prefix + "Resistance", String.valueOf(stats.getResistance()));
            // 속성 피해 보너스
            statsProps.setProperty(prefix + "Bonus.FireDamage", String.valueOf(stats.getFireDamageBonus()));
            statsProps.setProperty(prefix + "Bonus.WaterDamage", String.valueOf(stats.getWaterDamageBonus()));
            statsProps.setProperty(prefix + "Bonus.IceDamage", String.valueOf(stats.getIceDamageBonus()));
            statsProps.setProperty(prefix + "Bonus.DragonDamage", String.valueOf(stats.getDragonDamageBonus()));
            statsProps.setProperty(prefix + "Bonus.PhysicalDamage", String.valueOf(stats.getPhysicalDamageBonus()));
            // 속성 내성
            statsProps.setProperty(prefix + "Resistance.Fire", String.valueOf(stats.getFireResistance()));
            statsProps.setProperty(prefix + "Resistance.Water", String.valueOf(stats.getWaterResistance()));
            statsProps.setProperty(prefix + "Resistance.Ice", String.valueOf(stats.getIceResistance()));
            statsProps.setProperty(prefix + "Resistance.Dragon", String.valueOf(stats.getDragonResistance()));
            statsProps.setProperty(prefix + "Resistance.Physical", String.valueOf(stats.getPhysicalResistance()));
            // 회심
            statsProps.setProperty(prefix + "Critical.Chance.Base", String.valueOf(stats.getBaseCriticalChance()));
            statsProps.setProperty(prefix + "Critical.Chance.Boost", String.valueOf(stats.getBaseCriticalChanceBoost()));
            statsProps.setProperty(prefix + "Critical.Damage.Base", String.valueOf(stats.getBaseCriticalDamage()));
            statsProps.setProperty(prefix + "Critical.Damage.Boost", String.valueOf(stats.getBaseCriticalDamageBoost()));
        });

        try {
            statsProps.store(new FileOutputStream(statsFile), "Player Stats");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EntityStat getPlayerStats(Entity entity) {
        return playerStatsMap.computeIfAbsent(entity.getUniqueId(), k -> new EntityStat());
    }

    private void scheduleRegeneration() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (EntityStat playerStats : playerStatsMap.values()) {
                    playerStats.regenerateHealth(); // 체력 회복
                }
                saveStats();
            }
        }.runTaskTimer(plugin, 0L, 20L); // 20L = 1 second
    }
}
