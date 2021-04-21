package com.appstudiomr.apps.alarma.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@Service
public class FirebaseInitializer {

    private Environment env;

    @Autowired
    public FirebaseInitializer(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void initialize() {
        try {
            FirebaseConfig config = new FirebaseConfig(env);
            String configJson = config.getConfigJson();

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(configJson.getBytes(StandardCharsets.UTF_8)))).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase application has been initialized");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
