package com.atherys.roleplay.database;

import com.atherys.core.database.mongo.AbstractMongoDatabase;
import com.atherys.roleplay.AtherysRoleplay;

public class AtherysRoleplayDatabase extends AbstractMongoDatabase {
    private static AtherysRoleplayDatabase instance = new AtherysRoleplayDatabase();

    protected AtherysRoleplayDatabase() {
        super(AtherysRoleplay.getConfig().DATABASE);
    }

    public static AtherysRoleplayDatabase getInstance(){
        return instance;
    }
}
