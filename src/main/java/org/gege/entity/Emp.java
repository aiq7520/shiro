package org.gege.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author lenove1
 *
 */
@Entity
@Table(name="mms_emp")
public class Emp {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private int age;
	private double salary;
	private String pwd; 
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="did")//外键
	private Department department;//部门id
	@OneToOne
	@JoinColumn(name="mid")//外键
	private Emp manager;//上级
	
	private boolean lock;//是否锁定
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Emp() {
		super();
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Emp getManager() {
		return manager;
	}
	public void setManager(Emp manager) {
		this.manager = manager;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public Emp(Integer id) {
		super();
		this.id = id;
	}
	public Emp(String name, int age, double salary, int did, Emp manager) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.department = new Department(did);
		this.manager = manager;
	}
	public Emp(String name) {
		this.name=name;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", age=" + age
				+ ", salary=" + salary + ", pwd=" + pwd + ", department="
				+ department + ", manager=" + manager + "]";
	}
	public boolean isLock() {
		return lock;
	}
	public void setLock(boolean lock) {
		this.lock = lock;
	}

}

