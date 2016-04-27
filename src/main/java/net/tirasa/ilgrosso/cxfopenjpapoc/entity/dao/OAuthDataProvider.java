package net.tirasa.ilgrosso.cxfopenjpapoc.entity.dao;

import java.util.List;
import net.tirasa.ilgrosso.cxfopenjpapoc.entity.Client;

public interface OAuthDataProvider {

    List<Client> getClients();

    Client getClient(String clientId);

    void setClient(Client client);

    Client removeClient(String clientId);
}
