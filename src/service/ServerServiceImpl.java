package service;

import dataAccObject.MainDataAccObject;
import models.Server;
import init.IdentetyUnit;

import java.util.List;

/**
 * Created by Adriel on 10/8/2015.
 */


public class ServerServiceImpl extends MainDataAccObject implements ServerService {

    @Override
    public boolean addServer(Server server) { return add(server); }

    @Override
    public List<Server> ServerList() { return getList(IdentetyUnit.Server); }

    @Override
    public void deleteServer(String serverName) { delete(serverName); }

    @Override
    public boolean updateServer(Server server) { return update(server); }
}