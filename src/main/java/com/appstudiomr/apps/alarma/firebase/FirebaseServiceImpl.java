package com.appstudiomr.apps.alarma.firebase;

import com.appstudiomr.apps.alarma.security.firebase.FirebaseTokenHolder;
import org.springframework.stereotype.Service;

@Service
public class FirebaseServiceImpl implements FirebaseService {
	@Override
	public FirebaseTokenHolder parseToken(String firebaseToken) {
		return new FirebaseParser().parseToken(firebaseToken);
	}
}
