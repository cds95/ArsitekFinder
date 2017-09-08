package Entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="job")
public class Job {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int jid;
	private String jobTitle;
	private int price;
	private String description;
	private String type;
	@ManyToOne
	private Location location;
	private boolean complete;
	private int dayTimeFrame;
	@ManyToMany
	private Collection<User> user;
	@ManyToMany(mappedBy="jobs")
	private Collection<Tags> tags;
	private String employer;
	@ManyToMany(mappedBy="jobs")
	private Collection<Applicant> applicant;
	
	public Job() {
		this.user = new LinkedList<User>();
		this.tags = new LinkedList<Tags>();
		this.applicant = new LinkedList<Applicant>();
		this.complete = false;
	}
	
	public int getJid() {
		return jid;
	}
	public void setJid(int jid) {
		this.jid = jid;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public Collection<User> getUser() {
		return user;
	}

	public void setUser(Collection<User> user) {
		this.user = user;
	}
	public int size() {
		return this.user.size();
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public Collection<Tags> getTags() {
		return tags;
	}

	public void setTags(Collection<Tags> tags) {
		this.tags = tags;
	}
	
	public void removeAllTags() {
		for(Tags tag : this.tags){
			tag.removeJob(this);
		}
		this.tags.clear();
	}

	public Collection<Applicant> getApplicant() {
		return applicant;
	}

	public void setApplicant(Collection<Applicant> applicant) {
		this.applicant = applicant;
	}

	public int getDayTimeFrame() {
		return dayTimeFrame;
	}

	public void setDayTimeFrame(int dayTimeFrame) {
		this.dayTimeFrame = dayTimeFrame;
	}
	public int getApplicantSize() {
		return this.user.size();
	}
	/**
	 * Checks to see if the job contains a skill.  Return true if it does contain it
	 * and false otherwise
	 * @param tag
	 * @return
	 */
	public boolean containsSkill(Tags tag) {
		for(Tags target : this.tags) {
			if(target.equals(tag)) {
				return true;
			}
		}
		return false;
	}
	
}
