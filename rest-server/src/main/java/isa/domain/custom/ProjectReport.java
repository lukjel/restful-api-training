package isa.domain.custom;

import java.math.BigInteger;

public class ProjectReport {

	Long projectId;

	Long cnt;

	public ProjectReport(Long projectId, Long cnt) {
		this.projectId = projectId;
		this.cnt = cnt;
	}

	public ProjectReport(BigInteger projectId, BigInteger cnt) {
		this.projectId = projectId.longValue();
		this.cnt = cnt.longValue();
	}

	public Long getCnt() {
		return cnt;
	}

	public Long getProjectId() {
		return projectId;
	}

	@Override
	public String toString() {
		return "ProjectReport{" +
			"projectId=" + projectId +
			", cnt=" + cnt +
			'}';
	}
}
