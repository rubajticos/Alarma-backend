package com.appstudiomr.apps.alarma.service;


import com.appstudiomr.apps.alarma.config.firebase.FirebaseTokenHolder;

public interface FirebaseService {

	FirebaseTokenHolder parseToken(String idToken);

}
