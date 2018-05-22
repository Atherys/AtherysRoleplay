package com.atherys.roleplay.database;

import com.atherys.core.database.mongo.MorphiaDatabase;
import com.atherys.roleplay.AtherysRoleplay;

public class AtherysRoleplayDatabase extends MorphiaDatabase {
    private static AtherysRoleplayDatabase instance = new AtherysRoleplayDatabase();

    protected AtherysRoleplayDatabase() {
        super(AtherysRoleplay.getConfig().DATABASE, "com.atherys.roleplay");
    }

    public static AtherysRoleplayDatabase getInstance(){
        return instance;
    }
}
