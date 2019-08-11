package com.marina.usermenagmentsystem.security.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component("emailValidator")
public class EmailPatternValidator {
    


	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailPatternValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public boolean valid(final String email) {

		matcher = pattern.matcher(email);
		return matcher.matches();

	}
}