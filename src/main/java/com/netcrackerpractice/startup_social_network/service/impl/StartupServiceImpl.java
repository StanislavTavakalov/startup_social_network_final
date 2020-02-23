package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.repository.StartupRepository;
import com.netcrackerpractice.startup_social_network.repository.StartupRoleRepository;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import com.netcrackerpractice.startup_social_network.service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;


@Service
public class StartupServiceImpl implements StartupService {

    @Autowired
    private StartupRepository startupRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    StartupRoleRepository startupRoleRepository;


    @Override
    public List<Startup> findAll() {
        return startupRepository.findAll();
    }

    @Override
    public Optional<Startup> findStartupById(UUID id) {
        return startupRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteStartupById(UUID id) {
        startupRepository.deleteById(id);
    }

    @Override
    public Startup saveStartup(Startup startup, String image) {
        if (startupRepository.findByStartupName(startup.getStartupName()).isPresent()) {
            return null;
        }
        if (image != null && !image.equals("")) {
            try {
                File imageFile = imageService.convertStringToFile(image);
                String imageId = imageService.saveImageToGoogleDrive(imageFile);
                String comressedImagePath = imageService.compressionImage(imageFile);
                File comressedImageFile = new File(comressedImagePath);
                String comressedImageId = imageService.saveImageToGoogleDrive(comressedImageFile);
                startup.setImageId(imageId);
                startup.setCompressedImageId(comressedImageId);
                imageFile.delete();
                comressedImageFile.delete();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return startupRepository.save(startup);
    }


    @Override
    public Startup updateStartup(UUID id, Startup startup, String image) {
        if (!startupRepository.findById(id).isPresent()) {
            return null;
        }
        Optional<Startup> st = startupRepository.findByStartupName(startup.getStartupName());
        if (st.isPresent()) {
            if (startup.getId() == null || !st.get().getId().equals(id)) {
                return null;
            }
        }
        if (image != null && !image.equals("")) {
            try {
                if (startup.getImageId() != null && startup.getCompressedImageId() != null) {
                    imageService.deleteImageFromGoogleDrive(startup.getImageId(), startup.getCompressedImageId());
                }
                File imageFile = imageService.convertStringToFile(image);
                String imageId = imageService.saveImageToGoogleDrive(imageFile);

                String comressedImagePath = imageService.compressionImage(imageFile);
                File comressedImageFile = new File(comressedImagePath);
                String comressedImageId = imageService.saveImageToGoogleDrive(comressedImageFile);

                startup.setImageId(imageId);
                startup.setCompressedImageId(comressedImageId);
                imageFile.delete();
                comressedImageFile.delete();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return startupRepository.save(startup);
    }

    @Override
    public List<Startup> searchStartups(String nameContains, String creatorContains, String sortBy, String sortDirection, String accountID) {

        Map<String, String> searchParams = validateSearchParams(nameContains, creatorContains, sortBy, sortDirection);

        List<Startup> startupList = new ArrayList<>();

        if (accountID != null && !accountID.equals("")) {
            try {
                startupList.addAll(startupRepository.searchStartupAsLeader
                        (searchParams.get("nameContains").trim().toLowerCase(),
                                UUID.fromString(accountID),
                                new Sort(Sort.Direction.valueOf(searchParams.get("sortDirection").toUpperCase()), searchParams.get("sortBy"))
                        ));


                startupList.addAll(startupRepository.searchStartupsAsMember
                        (UUID.fromString(accountID),
                                searchParams.get("nameContains").trim().toLowerCase(),
                                new Sort(Sort.Direction.valueOf(searchParams.get("sortDirection").toUpperCase()), searchParams.get("sortBy"))
                        ));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            startupList.addAll(startupRepository.searchAllStartups
                    (searchParams.get("nameContains").trim().toLowerCase(),
                            searchParams.get("creatorContains").trim().toLowerCase(),
                            new Sort(Sort.Direction.valueOf(searchParams.get("sortDirection").toUpperCase()), searchParams.get("sortBy"))
                    ));
        }

        return startupList;

    }


    public Map<String, String> validateSearchParams(String nameContains, String creatorContains, String sortBy, String sortDirection) {
        Map<String, String> searchParams = new HashMap<>();
        if (nameContains == null) {
            nameContains = "";
        }
        if (creatorContains == null) {
            creatorContains = "";
        }
        if (sortBy == null || !(sortBy.equals("startupName") || sortBy.equals("sumOfInvestment") || sortBy.equals("dateOfCreation"))) {
            sortBy = "startupName";
        }
        if (sortDirection == null || !(sortDirection.toUpperCase().equals("ASC") || sortDirection.toUpperCase().equals("DESC"))) {
            sortDirection = "ASC";
        }

        searchParams.put("nameContains", nameContains);
        searchParams.put("creatorContains", creatorContains);
        searchParams.put("sortBy", sortBy);
        searchParams.put("sortDirection", sortDirection);

        return searchParams;

    }

    @Override
    public Boolean checkPermissionToEditStartup(UUID accountId, UUID startupId) {
        return this.startupRoleRepository.findModeratorInStartup(accountId, startupId).isPresent()
                || this.startupRepository.findStartupByIdAndAccountId(startupId, accountId).isPresent();
    }

    @Override
    public void blockStartup(UUID id) {
        startupRepository.blockStartup(id);
    }

    @Override
    public void unBlockStartup(UUID id) {
        startupRepository.unBlockStartup(id);
    }
}
