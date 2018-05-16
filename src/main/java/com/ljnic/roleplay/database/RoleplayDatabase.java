package com.ljnic.roleplay.database;

import com.atherys.core.database.mongo.AbstractMongoDatabase;
import com.ljnic.roleplay.Roleplay;

public class RoleplayDatabase extends AbstractMongoDatabase {
    private static RoleplayDatabase instance = new RoleplayDatabase();

    protected RoleplayDatabase() {
        super(Roleplay.getConfig().DATABASE);
    }

    public static RoleplayDatabase getInstance(){
        return instance;
    }
}
