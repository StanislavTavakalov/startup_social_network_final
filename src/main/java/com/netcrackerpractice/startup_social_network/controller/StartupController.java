package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.StartupDTO;
import com.netcrackerpractice.startup_social_network.entity.Investment;
import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.mapper.StartupMapper;
import com.netcrackerpractice.startup_social_network.payload.SearchStartupsRequest;
import com.netcrackerpractice.startup_social_network.service.InvestmentService;
import com.netcrackerpractice.startup_social_network.service.StartupService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/startup")
public class StartupController {

    @Autowired
    private StartupMapper startupMapper;

    @Autowired
    private StartupService startupService;

    @Autowired
    private InvestmentService investmentService;


    @GetMapping("/startup-list")
    public List<Startup> getStartupList() {
        return startupService.findAll();
    }

    @GetMapping("/search-startups")
    public List<StartupDTO> searchStartups(SearchStartupsRequest searchStartupsRequest) {
        List<StartupDTO> startupDTOS = new ArrayList<>();
        startupService.searchStartups(
                searchStartupsRequest.getStartupNameContains(),
                searchStartupsRequest.getCreator(),
                searchStartupsRequest.getSortBy(),
                searchStartupsRequest.getSortDirection(),
                searchStartupsRequest.getAccountID()
        ).forEach(startup -> startupDTOS.add(startupMapper.entityToDto(startup)));
        return startupDTOS;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StartupDTO> getStartupById(@PathVariable(name = "id") UUID id) {
        Optional<Startup> startup = startupService.findStartupById(id);
        return startup.map(startup1 -> ResponseEntity.ok(startupMapper.entityToDto(startup1))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStartup(@PathVariable(name = "id") UUID id) {
        startupService.deleteStartupById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<StartupDTO> saveStartup(@Valid @RequestBody StartupDTO startup) {
        Startup startup1 = startupService.saveStartup(startupMapper.dtoToEntity(startup), startup.getImage());
        if (startup1 != null) {
            return ResponseEntity.ok(startupMapper.entityToDto(startup1));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StartupDTO> updateStartup(@PathVariable(name = "id") UUID id, @Valid @RequestBody StartupDTO startup) {
        Startup startup1 = startupService.updateStartup(id, startupMapper.dtoToEntity(startup), startup.getImage());
        if (startup1 != null) {
            return ResponseEntity.ok(startupMapper.entityToDto(startup1));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/make-investment")
    public ResponseEntity<Investment> makeInvestment(@Valid @RequestBody Investment investment) {
        Investment inv = investmentService.saveInvestment(investment);
        if (inv != null) {
            return ResponseEntity.ok(inv);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/permission-to-edit")
    public Boolean checkPermissionToEdit(@RequestParam(name = "accountId") UUID accountId,
                                         @RequestParam(name = "startupId") UUID startupId) {
        if (accountId != null && !accountId.toString().equals("")) {
            return this.startupService.checkPermissionToEditStartup(accountId, startupId);
        } else {
            return false;
        }
    }

}