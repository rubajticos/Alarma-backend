package com.appstudiomr.apps.alarma.service.shared;

import com.appstudiomr.apps.alarma.config.firebase.FirebaseTokenHolder;
import com.appstudiomr.apps.alarma.exception.FirebaseTokenInvalidException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.apache.commons.lang3.StringUtils;

public class FirebaseParser {
    public FirebaseTokenHolder parseToken(String idToken) {
        if (StringUtils.isBlank(idToken)) {
            throw new IllegalArgumentException("FirebaseTokenBlank");
        }
        try {
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

            return new FirebaseTokenHolder(firebaseToken);
        } catch (Exception e) {
            throw new FirebaseTokenInvalidException(e.getMessage());
        }
    }
}
