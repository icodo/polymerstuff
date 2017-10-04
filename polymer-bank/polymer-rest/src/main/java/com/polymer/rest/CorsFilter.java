package com.polymer.rest;

import javax.annotation.Priority;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

/**
 * Filter that allow User Agents to use the CORS mechanism.
 *
 * @see <a href="https://www.w3.org/TR/cors/">https://www.w3.org/TR/cors/</a>
 */
@Provider
@PreMatching
@Priority(500)
public final class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

	private static final List<String> ALLOWED_HOSTS = Arrays.asList("localhost", "fhbjgbiflinjbdggehcddcbncdddomop",
			"acc-ingpi.qafe.com");

	@Override
	public void filter(final ContainerRequestContext containerRequestContext) throws IOException {
		final String origin = containerRequestContext.getHeaderString("Origin");

		if (origin != null && "OPTIONS".equals(containerRequestContext.getMethod())) {
			handlePreFlightRequest(origin, containerRequestContext);
		} else if (origin != null) {
			handleOriginRequest(origin);
		} else {
			return;
		}
	}

	@Override
	public void filter(final ContainerRequestContext containerRequestContext,
			final ContainerResponseContext containerResponseContext) throws IOException {
		final String origin = containerRequestContext.getHeaderString("Origin");
		if (origin != null && !"OPTIONS".equals(containerRequestContext.getMethod())) {
			final MultivaluedMap<String, Object> headers = containerResponseContext.getHeaders();
			addCorsHeaders(origin, headers);
		} else {
			return;
		}
	}

	private void handlePreFlightRequest(final String origin, final ContainerRequestContext containerRequestContext) {
		handleOriginRequest(origin);

		final Response response = Response.ok().build();
		addCorsHeaders(origin, response.getHeaders());

		containerRequestContext.abortWith(response);
	}

	private void handleOriginRequest(final String origin) {
		try {
			final URI originUrl = new URI(origin);
			if (!ALLOWED_HOSTS.isEmpty() && !ALLOWED_HOSTS.contains(originUrl.getHost())) {
				throw new ForbiddenException();
			}
		} catch (final URISyntaxException e) {
			throw new ForbiddenException("Request contained malformed 'Origin' header.");
		}
	}

	private void addCorsHeaders(final String origin, final MultivaluedMap<String, Object> headers) {
		headers.add("Access-Control-Allow-Origin", origin);
		headers.add("Access-Control-Allow-Methods", "OPTIONS, HEAD, GET, POST, PUT, DELETE");
		headers.add("Access-Control-Allow-Headers", "Accept, Authorization, Cache-Control, Content-Type, Bearer");
		headers.add("Access-Control-Expose-Headers", "Content-Disposition");
		headers.add("Access-Control-Allow-Credentials", "true");
	}
}
