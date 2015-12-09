package service;

import models.Server;

import java.util.List;

/**
 * Created by Adriel on 10/8/2015.
 */


public interface ServerService {
    boolean addServer(Server server);
    List<Server> ServerList();
    void deleteServer(String serverName);
    boolean updateServer(Server user);
}
