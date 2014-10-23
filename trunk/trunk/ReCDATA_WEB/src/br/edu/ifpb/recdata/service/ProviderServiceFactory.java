package br.edu.ifpb.recdata.service;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class ProviderServiceFactory {

	private static final String URL_SERVICE = "http://localhost:8080/quality-manager_SERVICE/";

	static {
		/*
		 * Precisa ser chamado uma única vez para registrar providers RESTEasy,
		 * scanear classes, etc
		 */
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
	}

	public static <T> T createServiceClient(Class<T> serviceType) {
		return createServiceClient(URL_SERVICE, serviceType);
	}

	/**
	 * A factory of clients to our REST services.
	 * 
	 * @author Rhavy Maia Guedes
	 * @author Eri Jonhson
	 */
	public static <T> T createServiceClient(String serviceUrl,
			Class<T> serviceType) {

		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target = client.target(serviceUrl);

		return target.proxy(serviceType);

	}

}
