package net.tirasa.ilgrosso.cxfopenjpapoc.entity.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import net.tirasa.ilgrosso.cxfopenjpapoc.entity.Client;
import net.tirasa.ilgrosso.cxfopenjpapoc.entity.dao.OAuthDataProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class JPAOAuthDataProvider implements OAuthDataProvider {

    @Value("#{entityManager}")
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;

    @Override
    public List<Client> getClients() {
        return entityManager.createQuery(
                "SELECT e FROM " + Client.class.getSimpleName() + " e", Client.class).getResultList();
    }

    @Override
    public Client getClient(String clientId) {
        return entityManager.find(Client.class, clientId);
    }

    @Override
    public Client setClient(Client client) {
        return entityManager.merge(client);
    }

    @Override
    public Client removeClient(String clientId) {
        Client client = getClient(clientId);
        if (client == null) {
            return null;
        }

        entityManager.remove(client);
        return client;
    }

}
