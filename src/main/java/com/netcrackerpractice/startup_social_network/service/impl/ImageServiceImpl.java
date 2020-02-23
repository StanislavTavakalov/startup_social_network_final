package com.netcrackerpractice.startup_social_network.service.impl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import org.springframework.stereotype.Service;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {

    private final String APPLICATION_NAME = "startup";
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final String TOKENS_DIRECTORY_PATH = "tokens";

    private final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE);
    private final String CREDENTIALS_FILE_PATH = "/credentials.json";

    private Drive getDriveService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        return service;
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = ImageServiceImpl.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    @Override
    public java.io.File convertStringToFile(String image) throws IOException {
        byte[] imageByte = Base64.decodeBase64(image);
        java.io.File convertedFile = new java.io.File(Objects.requireNonNull("sample.jpg"));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(imageByte);
        fos.close();
        return convertedFile;
    }

    @Override
    public String saveImageToGoogleDrive(java.io.File image) throws GeneralSecurityException, IOException {
        File fileMetadata = new File();
        fileMetadata.setName(image.getName());
        FileContent mediaContent = new FileContent("image/jpeg", image);
        File file = getDriveService().files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        System.out.println("File ID: " + file.getId());

        return file.getId();
    }

    @Override
    public String compressionImage(java.io.File image) throws IOException, GeneralSecurityException {

        BufferedImage bufferedImage = ImageIO.read(image);

        java.io.File compressedImageFile = new java.io.File("compress_" + image.getName());
        OutputStream os = new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);
        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.1f);  // Change the quality value you prefer
        writer.write(null, new IIOImage(bufferedImage, null, null), param);

        os.close();
        ios.close();
        writer.dispose();
        return compressedImageFile.getAbsolutePath();
    }

    @Override
    public void deleteImageFromGoogleDrive(String imageId, String compressImageId) throws GeneralSecurityException, IOException {

        if (imageId != null && compressImageId != null && !imageId.isEmpty() && !compressImageId.isEmpty()) {
            getDriveService().files().delete(imageId).execute();
            getDriveService().files().delete(compressImageId).execute();
        }

    }

}
