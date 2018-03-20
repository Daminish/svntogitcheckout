package com.capco.living.service;

import java.security.NoSuchAlgorithmException;

public interface PasswordEncoder {

	public String encode(String pswd) throws NoSuchAlgorithmException;
}
