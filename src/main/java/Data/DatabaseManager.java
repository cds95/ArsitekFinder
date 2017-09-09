package Data;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.mysql.jdbc.Blob;

import Entities.Applicant;
import Entities.Job;
import Entities.Location;
import Entities.Tags;
import Entities.University;
import Entities.User;

/**
 * Manages the interactions between the website and the MySQL database
 * @author christopherdimitrisastropranoto
 *
 */
public class DatabaseManager {

	private Configuration con;
	private SessionFactory sessionFact;
	private Session session;

	/**
	 * Creates a new instance of the DatabaseManager by making a connection to
	 * MySQL Database and opens a session
	 */
	public DatabaseManager() {
		this.con = new Configuration().configure();
		this.con.addAnnotatedClass(Job.class);
		this.con.addAnnotatedClass(Location.class);
		this.con.addAnnotatedClass(User.class);
		this.con.addAnnotatedClass(Tags.class);
		this.con.addAnnotatedClass(Applicant.class);
		this.con.addAnnotatedClass(University.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(
				con.getProperties()).buildServiceRegistry();
		this.sessionFact = con.buildSessionFactory(reg);
		this.session = this.sessionFact.openSession();
	}
	
	/**
	 * Checks to see if there is currently an open session with the database.  Returns true
	 * if connection is still open, and false otherwise
	 * @return
	 */
	public boolean checkOpenSession() {
		return this.session.isOpen();
	}
	
	/**
	 * Returns a list of all the universities
	 * @return
	 */
	public List<University> getUniversities() {
		String hql = "From University";
		Query query = this.session.createQuery(hql);
		return query.list();
	}

	/**
	 * Adds one user to the database
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		Transaction tx = session.beginTransaction();
		this.session.save(user);
		tx.commit();
	}

	/**
	 * Gets a specific job based on it's id
	 * 
	 * @param jid
	 * @return
	 */
	public Job getJob(int jid) {
		String hql = "From Job Where jid = :jid";
		Query query = this.session.createQuery(hql);
		query.setParameter("jid", jid);
		return (Job) query.list().get(0);
	}

	/**
	 * Adds a job and it's location into the database
	 * 
	 * @param job
	 * @param location
	 */
	public void addJob(Job job, User user) {
		job.setEmployer(user.getHandle());
		Transaction tx = session.beginTransaction();
		this.session.save(job);
		tx.commit();
	}

	/**
	 * Returns a list containing all the jobs available from a certain city
	 * 
	 * @return
	 */
	public List<Object> getJobsFromCity(int cid) {
		String hql = "From Location Where cid = :cid";
		Query query = this.session.createQuery(hql);
		query.setParameter("cid", cid);
		return query.list();
	}

	/**
	 * Returns a jobs in the system
	 * 
	 * @return
	 */
	public List<Job> getJobs() {
		String hql = "From Job";
		Query query = this.session.createQuery(hql);
		return query.list();
	}

	/**
	 * Returns a jobs in the system
	 * 
	 * @return
	 */
	public List<Job> getOpenJobs() {
		String hql = "From Job Where complete = :complete";
		Query query = this.session.createQuery(hql);
		query.setParameter("complete", false);
		return query.list();
	}
	
	/**
	 * Updates a job
	 * @param job
	 */
	public void updateJob(Job job) {
		
	}
	
	/**
	 * Returns a list of all locations in the database
	 * @return
	 */
	public List<Location> getLocations() {
		String hql = "From Location";
		Query query = this.session.createQuery(hql);
		return query.list();
	}
	
	/**
	 * Gets location information based on location id
	 * 
	 * @param city
	 * @return
	 */
	public Location getLocation(int cid) {
		String hql = "From Location Where cid = :cid";
		Query query = this.session.createQuery(hql);
		query.setParameter("cid", cid);
		return (Location) query.list().get(0);
	}

	/**
	 * Gets location information based on name of city
	 * 
	 * @param city
	 * @return
	 */
	public Location getLocation(String city) {
		String hql = "From Location Where city = :city";
		Query query = this.session.createQuery(hql);
		query.setParameter("city", city);
		return (Location) query.list().get(0);
	}
	
	/**
	 * Returns a list of all the tags
	 * @return
	 */
	public List<Tags> getTags() {
		String hql = "From Tags";
		Query query = this.session.createQuery(hql);
		return query.list();
	}

	/**
	 * Returns a Tag object according to the tag(skill) name
	 * @param tagName
	 * @return
	 */
	public Tags getTag(String tagName) {
		String hql = "From Tags Where skill = :skill";
		Query query = this.session.createQuery(hql);
		query.setParameter("skill", tagName);
		return (Tags) query.list().get(0);
	}
	
	/**
	 * Returns a Tag object according to the tag ID
	 * @param tagName
	 * @return
	 */
	public Tags getTag(int tid) {
		String hql = "From Tags Where tid = :tid";
		Query query = this.session.createQuery(hql);
		query.setParameter("tid", tid);
		return (Tags) query.list().get(0);
	}
	
	/**
	 * Sets the required skills of a job
	 * @param job
	 * @param skills
	 */
	public void setSkills(Job job, String[] skills) {
		Transaction tx = this.session.beginTransaction();
		for (String skill : skills) {
			Tags tag = this.getTag(skill);
			tag.getJobs().add(job);
			job.getTags().add(tag);
			this.session.save(tag);
		}
		this.session.save(job);
		tx.commit();
	}

	/**
	 * Retrieves a users password given it's handle and returns null if user is not in database
	 * 
	 * @param handle
	 * @return
	 */
	public String getUserPassword(String handle) {
		String hql = "From User Where handle = :handle";
		Query query = this.session.createQuery(hql);
		query.setParameter("handle", handle);
		List<User> userList = query.list();
		if(userList.isEmpty()) {
			return null;
		} else {
			User user = (User) query.list().get(0);
			return user.getPassword();
		}
	}

	/**
	 * Returns a user object given a handle
	 * 
	 * @param handle
	 * @return
	 */
	public User getUser(String handle) {
		String hql = "From User Where handle = :handle";
		Query query = this.session.createQuery(hql);
		query.setParameter("handle", handle);
		List res = query.list();
		if(!res.isEmpty()) {
			return (User) res.get(0);
		}
		return null;
	}

	public User getUser(int uid) {
		String hql = "From User Where uid = :uid";
		Query query = this.session.createQuery(hql);
		query.setParameter("uid", uid);
		return (User) query.list().get(0);
	}

	/**
	 * Returns a list depending on job type
	 * 
	 * @param type
	 * @param complete - pass in false if looking for open jobs and true if looking for closed jobs
	 * @return
	 */
	public List<Job> getJobByType(String type, boolean complete) {
		String hql = "From Job Where type = :type And complete = :complete";
		Query query = this.session.createQuery(hql);
		query.setParameter("type", type);
		query.setParameter("complete", complete);
		return query.list();
	}

	/**
	 * Registers a user to a job
	 * 
	 * @param user
	 * @param job
	 */
	public void registerUser(User user, Job job, String filePath) {
		user.getJobs().add(job);
		job.getUser().add(user);
		Applicant app = new Applicant();
		app.setPortPath(filePath);
		app.getJobs().add(job);
		app.setUser(user);
		user.getApplications().add(app);
		job.getApplicant().add(app);
		Transaction tx = this.session.beginTransaction();
		this.session.save(job);
		this.session.save(user);
		this.session.save(app);
		tx.commit();
	}
	
	/**
	 * Registers a user to a job
	 * 
	 * @param user
	 * @param job
	 */
	public void registerUser(User user, Job job, String fileName, byte[] fileInfo) {
		Applicant app = new Applicant();
		app.setFileName(fileName);
		app.setFileInfo(fileInfo);
		user.getApplications().add(app);
		job.getApplicant().add(app);
		user.getJobs().add(job);
		job.getUser().add(user);
		app.getJobs().add(job);
		app.setUser(user);
		Transaction tx = this.session.beginTransaction();
		this.session.save(app);
		this.session.save(user);
		this.session.save(job);
		tx.commit();
	}
	
	/**
	 * Gets an application's work sample
	 * @param aid
	 * @return
	 */
	public Blob getSample(int aid) {
		String hql = "From applicant Where aid =:aid";
		Query query = this.session.createQuery(hql);
		query.setParameter("aid", aid);
		List<Blob> res = query.list();
		if(!res.isEmpty()) {
			return null;
		} 
		return res.get(0);
	}
	/**
	 * Edits a given users phonenumber
	 * 
	 * @param user
	 * @param phoneNumber
	 */
	public void editUserPhoneNumber(User user, String phoneNumber) {
		user.setPhoneNumber(phoneNumber);
		Transaction tx = this.session.beginTransaction();
		this.session.save(user);
		tx.commit();
	}

	/**
	 * Adds a skill to a user
	 * 
	 * @param user
	 * @param tag
	 */
	public void addUserSkill(User user, Tags tag) {
		user.getSkills().add(tag);
		tag.getUsers().add(user);
		Transaction tx = this.session.beginTransaction();
		this.session.save(user);
		this.session.save(tag);
		tx.commit();
	}

	/**
	 * Adds a skill to a user
	 * 
	 * @param user
	 * @param tag
	 */
	public void addJobSkill(Job job, Tags tag) {
		job.getTags().add(tag);
		tag.getJobs().add(job);
		Transaction tx = this.session.beginTransaction();
		this.session.save(job);
		this.session.save(tag);
		tx.commit();
	}

	/**
	 * Saves a users location
	 * 
	 * @param user
	 * @param location
	 */
	public void setUserLocation(User user, Location location) {
		user.setLocation(location);
		location.getUser().add(user);
		Transaction tx = this.session.beginTransaction();
		this.session.save(user);
		this.session.save(location);
		tx.commit();
	}

	/**
	 * Removes a job from the database
	 * 
	 * @param job
	 */
	public void removeJob(Job job) {
		Transaction tx = this.session.beginTransaction();
		job.removeAllTags();
		job.getApplicant().clear();
		job.getUser().clear();
		this.session.delete(job);
		tx.commit();
	}
	
	/**
	 * Closes a job by setting it to be complete
	 * @param job
	 */
	public void closeJob(Job job) {
		Transaction tx = this.session.beginTransaction();
		job.setComplete(true);
		this.session.save(job);
		tx.commit();
	}
	
	/**
	 * Opens a job by setting it to be incomplete
	 * @param job
	 */
	public void openJob(Job job) {
		Transaction tx = this.session.beginTransaction();
		job.setComplete(false);
		this.session.save(job);
		tx.commit();
	}

	/**
	 * Returns all user job applications
	 * @param handle
	 * @return
	 */
	public List<Job> getAllUserJobs(String handle) {
		String hql = "From Job Where employer = :handle";
		Query query = this.session.createQuery(hql);
		query.setParameter("handle", handle);
		return query.list();
	}
	
	/**
	 * Returns all applications in the database system
	 * @return
	 */
	public List<Applicant> getAllApplications() {
		String hql = "FROM Applicant";
		Query query = this.session.createQuery(hql);
		return query.list();
	}

	/**
	 * Returns all the applications in the database
	 * @return
	 */
	public List<Applicant> getJobApplications(int jid) {
		String hql = "Select applicant FROM Job WHERE jid = :jid";
		Query query = this.session.createQuery(hql);
		query.setParameter("jid", jid);
		return query.list();
	}
	
	/**
	 * Updates user information.  Information includes User's first name, last name, email, university, and major
	 * @param user
	 * @param first
	 * @param last
	 * @param email
	 * @param uni
	 * @param major
	 */
	public void updateUserInformation(User user, String first, String last, String email, String phone) {
		Transaction tx = this.session.beginTransaction();
		user.setFirst(first);
		user.setLast(last);
		user.setEmail(email);
		user.setPhoneNumber(phone);
		this.session.save(user);
		tx.commit();
	}
	
	/**
	 * Returns a university given it's eid 
	 * @param eid
	 * @return
	 */
	public University getUniversity(int eid) {
		String hql = "From University WHERE eid = :eid";
		Query query = this.session.createQuery(hql);
		query.setParameter("eid", eid);
		return (University) query.list().get(0);
	}
	
	/**
	 * Sets a user's university
	 * @param user
	 * @param uni
	 */
	public void setUserUniversity(User user, University uni, int gradYear) {
		Transaction tx = this.session.beginTransaction();
		user.setGraduationYear(gradYear);
		user.setUni(uni);
		uni.getUsers().add(user);
		this.session.save(user);
		this.session.save(uni);
		tx.commit();
	}
	
	/**
	 * Updates the file path location of a user's resume
	 * @param user
	 * @param filePath
	 */
	public void updateUserResume(User user, String filePath) {
		Transaction tx = this.session.beginTransaction();
		user.setResume(filePath);
		this.session.save(user);
		tx.commit();
	}
	
	/**
	 * Deletes a given tag from a user
	 * @param user
	 * @param tags
	 */
	public void deleteUserTag(User user, Tags tags) {
		Transaction tx = this.session.beginTransaction();
		user.getSkills().remove(tags);
		tags.getUsers().remove(user);
		this.session.save(user);
		this.session.save(tags);
		tx.commit();
	}
	
	/**
	 * Deletes a given tag from a job
	 * @param job
	 * @param tags
	 */
	public void deleteJobTag(Job job, Tags tags) {
		Transaction tx = this.session.beginTransaction();
		job.getTags().remove(tags);
		tags.getJobs().remove(job);
		this.session.save(job);
		this.session.save(tags);
		tx.commit();
	}
	
	/**
	 * Sets a user's password
	 * @param user
	 * @param password
	 */
	public void saveUserPassword(User user, String password) {
		Transaction tx = this.session.beginTransaction();
		user.setPassword(password);
		this.session.save(user);
		tx.commit();
	}
	
}
