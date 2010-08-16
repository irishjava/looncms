package uk.mafu.loon.gillespies.front;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import uk.mafu.domain.gillespies.Job;
import uk.mafu.domain.gillespies.Office;

public class OfficeAndJob implements  Serializable {
	 
	private static final long serialVersionUID = 1L;
	private Office office;
	private Job job;
	private boolean global;

	 

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof OfficeAndJob))
			return false;
		OfficeAndJob castOther = (OfficeAndJob) other;
		return new EqualsBuilder().append(job.getPk(), castOther.job.getPk()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(job.getPk()).toHashCode();
	}

	public OfficeAndJob(Office office, Job job) {
		this.office = office;
		this.job = job;
		this.global = false;
	}

	public OfficeAndJob(Job job) {
		this.job = job;
		this.global = true;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public boolean isGlobal() {
		return global;
	}

	public void setGlobal(boolean global) {
		this.global = global;
	}

	 
	
	
	
}
