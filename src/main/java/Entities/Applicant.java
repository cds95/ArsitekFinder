/**
 * 
 * Defines the entitiy class for a users application to a job
 */
package Entities;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="applicant")
public class Applicant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int aid;
	private String fileName;
	@ManyToOne
	private User user;
	@ManyToMany
	private Collection<Job> jobs;
	private byte[] fileInfo;
	String portPath;
	private int bid;
	private String proposal;
	
	public Applicant() {
		this.jobs = new LinkedList<Job>();
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Collection<Job> jobs) {
		this.jobs = jobs;
	}

	public String getPortPath() {
		return portPath;
	}

	public void setPortPath(String portPath) {
		this.portPath = portPath;
	}

	public byte[] getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(byte[] fileInfo) {
		this.fileInfo = fileInfo;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	
	public String getUserName() {
		return this.user.getFirst() + " " + this.user.getLast();
	}
	
	public String toString() {
		String res = this.aid + " ";
		res += this.user.getFirst() + " " + this.user.getLast();
		return res;
	}
		
}
