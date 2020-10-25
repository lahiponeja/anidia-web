package com.liferay.sampleVue.internal.services;

import com.liferay.sampleVue.internal.exception.*;
import java.io.*;
import java.util.*;
import org.junit.*;

public class SalesforceServiceTest {

    @Before
    public void loadSalesforceEnvVars() throws IOException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream(
            "portlet.properties"));

        SalesforceService.SALESFORCE_TOKEN_URL = props.getProperty("SALESFORCE_TOKEN_URL");
        SalesforceService.SALESFORCE_ADDRESSES_URL = props.getProperty("SALESFORCE_ADDRESSES_URL");
        SalesforceService.SALESFORCE_ESTATES_URL = props.getProperty("SALESFORCE_ESTATES_URL");
        SalesforceService.SALESFORCE_PROPERTIES_URL = props.getProperty("SALESFORCE_PROPERTIES_URL");
        SalesforceService.SALESFORCE_LEAD_URL = props.getProperty("SALESFORCE_LEAD_URL");
        SalesforceService.SALESFORCE_PASSWORD = props.getProperty("SALESFORCE_PASSWORD");
        SalesforceService.SALESFORCE_CLIENT_SECRET = props.getProperty("SALESFORCE_CLIENT_SECRET");
        SalesforceService.SALESFORCE_CLIENT_ID = props.getProperty("SALESFORCE_CLIENT_ID");
        SalesforceService.SALESFORCE_USERNAME = props.getProperty("SALESFORCE_USERNAME");
    }

    @Test
    public void testSalesforceToken() {
        SalesforceService service = new SalesforceService();
        try {
            service.getSalesforceToken();
        } catch (PortletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAddresses() {
        SalesforceService service = new SalesforceService();
        try {
            service.getAddresses("05", "05500");
        } catch (PortletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getProperties() {
        SalesforceService service = new SalesforceService();
        try {
            service.getProperties("4");
        } catch (PortletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEstates() {
        SalesforceService service = new SalesforceService();
        try {
            service.getEstates("", "", "", "");
        } catch (PortletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createLead() {
        SalesforceService service = new SalesforceService();
    }
}
