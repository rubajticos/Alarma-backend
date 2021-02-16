package com.appstudiomr.apps.alarma.service;

import com.appstudiomr.apps.alarma.config.firebase.FirebaseTokenHolder;
import com.appstudiomr.apps.alarma.service.shared.FirebaseParser;
import org.springframework.stereotype.Service;

@Service
public class FirebaseServiceImpl implements FirebaseService {
	@Override
	public FirebaseTokenHolder parseToken(String firebaseToken) {
		return new FirebaseParser().parseToken(firebaseToken);
	}
}
