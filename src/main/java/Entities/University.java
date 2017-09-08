package Entities;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="university")
public class University {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eid;
	private String name;
	@OneToMany(mappedBy="uni")
	private Collection<User> uni;
	
	public University() {
		this.uni = new LinkedList<User>();
	}

	public int getEid() {
		return eid;
	}

	public void setUid(int eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return uni;
	}

	public void setUsers(Collection<User> users) {
		this.uni = users;
	}
	
}
