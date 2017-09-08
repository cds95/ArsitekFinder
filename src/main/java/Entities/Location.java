package Entities;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.*;

@Entity
@Table(name="location")
public class Location {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cid;
	private String country;
	private String state;
	private String city;
	@OneToMany(mappedBy="location")
	private Collection<Job> jobs;
	@OneToMany(mappedBy="location")
	private Collection<User> user;
	
	public Location() {
		this.jobs = new LinkedList<Job>();
		this.user = new LinkedList<User>();
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Collection<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Collection<Job> jobs) {
		this.jobs = jobs;
	}

	public Collection<User> getUser() {
		return user;
	}

	public void setUser(Collection<User> user) {
		this.user = user;
	}
}


