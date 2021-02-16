package com.appstudiomr.apps.alarma.fcm;


import com.google.gson.Gson;
import org.springframework.core.env.Environment;

public class FirebaseConfig {

    String type;
    String project_id;
    String private_key_id;
    String private_key;
    String client_email;
    String client_id;
    String auth_uri;
    String token_uri;
    String auth_provider_x509_cert_url;
    String client_x509_cert_url;

    public FirebaseConfig(Environment environment) {
        this.type = environment.getProperty("type");
        this.project_id = environment.getProperty("project_id");
        this.private_key_id = environment.getProperty("private_key_id");
        this.private_key = environment.getProperty("private_key");
        this.client_email = environment.getProperty("client_email");
        this.client_id = environment.getProperty("client_id");
        this.auth_uri = environment.getProperty("auth_uri");
        this.token_uri = environment.getProperty("token_uri");
        this.auth_provider_x509_cert_url = environment.getProperty("auth_provider_x509_cert_url");
        this.client_x509_cert_url = environment.getProperty("client_x509_cert_url");
    }

    public String getConfigJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json.replace("\\\\n", "\\n");
    }
}
