package de.crafty.survivalutils.util;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.UUID;

public record StaticCoordinate(World world, String name, int x, int y, int z, UUID creator) {


    public String save(){

        StringBuilder data_builder = new StringBuilder(this.creator.toString());
        data_builder.append("/").append(this.world.getName());
        data_builder.append("/").append(this.name);
        data_builder.append("/").append(this.x);
        data_builder.append("/").append(this.y);
        data_builder.append("/").append(this.z);

        return data_builder.toString();
    }

    public static StaticCoordinate load(String s){
        String[] data = s.split("/");
        return new StaticCoordinate(Bukkit.getWorld(data[1]), data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]), UUID.fromString(data[0]));
    }

}
