package com.hng.config;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
   public class FirebaseStorage {

      @Bean
      public Storage getStorage() throws IOException {
         ClassPathResource serviceAccount = new ClassPathResource("firebase-config.json");
         // Authenticate with Google Cloud using service account credentials
         // InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-config.json");
         GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount.getInputStream());
         return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
      }
   }
