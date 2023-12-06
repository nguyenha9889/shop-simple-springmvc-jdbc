package com.hng.service;

import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class FirebaseUploadService {
   private String bucketName = "demoupload-512c4.appspot.com";

   @Autowired
   private Storage storage;

   // upload file lên firebase
   public String uploadFile(MultipartFile file) {
      String fileName = generateFileName(file.getOriginalFilename()); // lấy ra tên file upload
      System.out.println(fileName);
      BlobId blobId = BlobId.of(bucketName, fileName); // tạo file trên storage bằng tên và bucket name chỉ đinh

      BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

      // Thiết lập quyền truy cập công cộng
      List<Acl> acls = new ArrayList<>();
      acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
      blobInfo = blobInfo.toBuilder().setAcl(acls).build();
      try {
         Blob blob = storage.create(blobInfo, file.getBytes());
         return blob.getMediaLink(); // trả về đường dẫn ảnh online
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private String generateFileName(String originalFileName) {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
      return dtf.format(LocalDateTime.now())+ originalFileName +"."+ getExtension(originalFileName);
   }

   private String getExtension(String originalFileName) {
      return StringUtils.getFilenameExtension(originalFileName);
   }
}
