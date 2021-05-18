package com.appstudiomr.apps.alarma.firebase;


import com.appstudiomr.apps.alarma.security.firebase.FirebaseTokenHolder;

public interface FirebaseService {

	FirebaseTokenHolder parseToken(String idToken);

}
