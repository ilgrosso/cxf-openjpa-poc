package net.tirasa.ilgrosso.cxfopenjpapoc.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import net.tirasa.ilgrosso.cxfopenjpapoc.entity.dao.OAuthDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:persistenceContext.xml" })
@Transactional
public class BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    @Autowired
    private OAuthDataProvider provider;

    private Client addClient(String clientId, String userLogin) {
        Client c = new Client();
        c.setRedirectUris(Collections.singletonList("http://client/redirect"));
        c.setClientId(clientId);
        c.setClientSecret("123");
        c.setResourceOwnerSubject(new UserSubject(userLogin));
        provider.setClient(c);
        return c;
    }

    private void compareClients(Client c, Client c2) {
        assertNotNull(c2);
        assertEquals(c.getClientId(), c2.getClientId());
        assertEquals(1, c.getRedirectUris().size());
        assertEquals(1, c2.getRedirectUris().size());
        assertEquals("http://client/redirect", c.getRedirectUris().get(0));
        assertEquals(c.getResourceOwnerSubject().getLogin(), c2.getResourceOwnerSubject().getLogin());
    }

    @Test
    public void testAddGetDeleteClient() {
        Client c = addClient("12345", "alice");
        Client c2 = provider.getClient(c.getClientId());
        compareClients(c, c2);

        c2.setClientSecret("567");
        provider.setClient(c2);
        Client c22 = provider.getClient(c.getClientId());
        compareClients(c2, c22);

        provider.removeClient(c.getClientId());
        Client c3 = provider.getClient(c.getClientId());
        assertNull(c3);
    }

}
