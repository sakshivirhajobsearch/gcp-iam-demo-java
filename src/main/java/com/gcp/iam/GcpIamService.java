package com.gcp.iam;

import com.google.cloud.resourcemanager.v3.ProjectName;
import com.google.cloud.resourcemanager.v3.ProjectsClient;
import com.google.iam.v1.GetIamPolicyRequest;
import com.google.iam.v1.Policy; // ✅ Correct import

public class GcpIamService {

	public Policy getIamPolicy(String projectId) throws Exception {
		
		try (ProjectsClient projectsClient = ProjectsClient.create()) {
			ProjectName projectName = ProjectName.of(projectId);

			// Explicitly create a GetIamPolicyRequest
			GetIamPolicyRequest request = GetIamPolicyRequest.newBuilder().setResource(projectName.toString()).build();

			// ✅ Return the correct IAM policy object
			return projectsClient.getIamPolicy(request);
		}
	}

	public static void main(String[] args) throws Exception {
		
		String projectId = "adroit-standard-431618-m4"; // Replace with your actual GCP Project ID
		GcpIamService service = new GcpIamService();
		Policy policy = service.getIamPolicy(projectId);

		System.out.println("IAM Policy for Project: " + projectId);
		policy.getBindingsList().forEach(binding -> {
			System.out.println("Role: " + binding.getRole());
			System.out.println("Members: " + binding.getMembersList());
		});
	}
}
