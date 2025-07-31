package com.gcp.iam;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.google.iam.v1.Policy;

public class GcpIamServiceTest {

	@Test
	public void testGetIamPolicy() {
		String keyPath = "E:\\gcp-creds\\my-service-account.json"; // ✅ Set your path here

		// 🔐 Skip test if credential file does not exist
		assumeTrue(Files.exists(Paths.get(keyPath)), "❌ Skipping test: Credentials file not found");

		// ✅ Set environment variable
		System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", keyPath);

		try {
			String projectId = "your-gcp-project-id"; // 🔁 Replace with your project ID
			GcpIamService service = new GcpIamService();
			Policy policy = service.getIamPolicy(projectId);
			assertNotNull(policy);
			System.out.println("✅ IAM Policy retrieved successfully.");
		} catch (IOException e) {
			fail("Google credentials are missing or invalid: " + e.getMessage());
		} catch (Exception e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}
}
