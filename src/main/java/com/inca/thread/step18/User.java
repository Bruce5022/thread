package com.inca.thread.step18;

public class User {

	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User user = (User) obj;
		if (this.name.equals(user.name) && this.age == user.age) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode() + age;
	}

}
