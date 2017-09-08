package Entities;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int uid;
	private String first;
	private String last;
	private String handle;
	private String password;
	private String expertise;
	private String email;
	private String phoneNumber;
	@Transient
	private int jobsGiven;
	@ManyToMany(mappedBy="user")
	private List<Job> jobs;
	@ManyToOne
	private Location location;
	@ManyToMany
	private Collection<Tags> skills;
	private String occupation;
	@OneToMany
	private Collection<Applicant> applications;
	private String resume;
	@ManyToOne
	private University uni;
	private int graduationYear;
	
	public User() {
		this.jobs = new LinkedList<Job>();
		this.skills = new LinkedList<Tags>();
		this.applications = new LinkedList<Applicant>();
	}

	public int getUid() {
		return uid;
	}



	public void setUid(int uid) {
		this.uid = uid;
	}



	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	/**
	 * Checks to see if the user has applied for a job
	 * @param target
	 * @return
	 */
	public boolean checkApplied(Job target) {
		for(Job job : this.jobs){
			if(job.equals(target)) {
				return true;
			}
		}
		return false;
	}
	
	public void incrementJobsGiven() {
		this.jobsGiven++;
	}
	public int getNumJobsGiven() {
		return this.jobsGiven;
	}

	public Collection<Tags> getSkills() {
		return skills;
	}

	public void setSkills(Collection<Tags> skills) {
		this.skills = skills;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Collection<Applicant> getApplications() {
		return applications;
	}

	public void setApplications(Collection<Applicant> applications) {
		this.applications = applications;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public University getUni() {
		return uni;
	}

	public void setUni(University uni) {
		this.uni = uni;
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}
	/**
	 * Checks to see if a user already has a given skill
	 * @param tag
	 * @return
	 */
	public boolean containsTag(Tags tag) {
		for(Tags tags : this.skills) {
			if(tags.equals(tag)) {
				return true;
			}
		}
		return false;
	}
}
