package org.acme.security.openid.connect;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.oidc.server.OidcWiremockTestResource;
import io.restassured.RestAssured;
import io.smallrye.jwt.build.Jwt;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@QuarkusTestResource(OidcWiremockTestResource.class)
public class BearerTokenAuthorizationTest {

	@Test
	public void testBearerToken() {
		RestAssured.given().auth().oauth2(getAccessToken("alice", new HashSet<>(List.of("user"))))
				.when().get("/api/users/preferredUserName")
				.then()
				.statusCode(200)
				// the test endpoint returns the name extracted from the injected SecurityIdentity Principal
				.body("userName", equalTo("alice"));
	}

	private String getAccessToken(String userName, Set<String> groups) {
		return Jwt.preferredUserName(userName)
				.groups(groups)
				.issuer("https://server.example.com")
				.audience("https://service.example.com")
				.sign();
	}
}