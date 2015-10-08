package service;

/**
 * Created by Adriel on 10/8/2015.
 */
import dataAccObject.MainDataAccObject;
import models.Server;
import units.EntityEnum;

import java.util.List;

public class ServerServiceImpl extends MainDataAccObject implements ServerService {

    @Override
    public boolean addServer(Server server) { return add(server); }

    @Override
    public List<Server> ServerList() { return getList(EntityEnum.Server); }

    @Override
    public void deleteServer(String serverName) { delete(serverName); }

    @Override
    public boolean updateServer(Server server) { return update(server); }
}