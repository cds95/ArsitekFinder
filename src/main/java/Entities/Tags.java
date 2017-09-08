package Entities;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tags")
public class Tags {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int tid;
	private String skill;
	@ManyToMany(mappedBy="skills")
	private Collection<User> users;
	@ManyToMany
	private Collection<Job> jobs;
	
	public Tags() {
		this.users = new LinkedList<User>();
		this.jobs = new LinkedList<Job>();
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Collection<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Collection<Job> jobs) {
		this.jobs = jobs;
	}
	
	public void removeJob(Job job) {
		this.jobs.remove(job);
	}
	
	
	
	
}
