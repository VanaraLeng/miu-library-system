package business;

import java.io.Serializable;

import utility.Validator;

final public class Author extends Person implements Serializable, Validable {
	private String bio;
	public String getBio() {
		return bio;
	}
	
	public Author(String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.bio = bio;
	}
	
	public String getValidationMessage() {
		if (super.getValidationMessage() != null) {
			return super.getValidationMessage();
		}
		if (!Validator.isFilled(bio)) {
			return "Bio is incorrect";
		}
		return null;
	}

	private static final long serialVersionUID = 7508481940058530471L;
}
