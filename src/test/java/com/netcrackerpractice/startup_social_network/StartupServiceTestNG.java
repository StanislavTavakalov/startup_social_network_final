//package com.netcrackerpractice.startup_social_network;
//import com.netcrackerpractice.startup_social_network.entity.Startup;
//import com.netcrackerpractice.startup_social_network.repository.StartupRepository;
//import com.netcrackerpractice.startup_social_network.service.impl.StartupServiceImpl;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.Sort;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.*;
//
//import static org.testng.Assert.*;
//import static org.mockito.Mockito.when;
//public class StartupServiceTestNG {
//    @InjectMocks
//    private StartupServiceImpl startupService;
//    @Mock
//    private StartupRepository startupRepository;
//
//    @BeforeMethod
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void validateSearchParams_allNullParams_setAllParamsDefault(){
//
//        String nameContains= null;
//        String creatorContains = null;
//        String sortBy = null;
//        String sortDirection = null;
//
//        Map<String,String> expectedSearchParams = new HashMap<>();
//        expectedSearchParams.put("nameContains", "");
//        expectedSearchParams.put("creatorContains", "");
//        expectedSearchParams.put("sortBy", "startupName");
//        expectedSearchParams.put("sortDirection", "ASC");
//
//        Map<String,String> resultSearchParams = startupService.validateSearchParams(nameContains, creatorContains, sortBy, sortDirection);
//        assertEquals(expectedSearchParams, resultSearchParams);
//    }
//
//    @Test
//    public void validateSearchParams_correctParams_paramsNotChanged(){
//
//        String nameContains= "super";
//        String creatorContains = "stas";
//        String sortBy = "startupName";
//        String sortDirection = "DECS";
//
//        Map<String,String> expectedSearchParams = new HashMap<>();
//        expectedSearchParams.put("nameContains", "super");
//        expectedSearchParams.put("creatorContains", "stas");
//        expectedSearchParams.put("sortBy", "startupName");
//        expectedSearchParams.put("sortDirection", "ASC");
//
//        Map<String,String> resultSearchParams = startupService.validateSearchParams(nameContains, creatorContains, sortBy, sortDirection);
//        assertEquals(expectedSearchParams, resultSearchParams);
//    }
//
//    @Test
//    public void validateSearchParams_invalidSortDirectionParam_sortDirectionParamSetDefault(){
//
//        String nameContains= "good one";
//        String creatorContains = "stas";
//        String sortBy = "startupName";
//        String sortDirection = "bla-bla";
//
//        Map<String,String> expectedSearchParams = new HashMap<>();
//        expectedSearchParams.put("nameContains", "good one");
//        expectedSearchParams.put("creatorContains", "stas");
//        expectedSearchParams.put("sortBy", "startupName");
//        expectedSearchParams.put("sortDirection", "ASC");
//
//        Map<String,String> resultSearchParams = startupService.validateSearchParams(nameContains, creatorContains, sortBy, sortDirection);
//        assertEquals(expectedSearchParams, resultSearchParams);
//    }
//
//    @Test
//    public void validateSearchParams_invalidSortByParam_sortByParamSetDefault(){
//
//        String nameContains= "changed";
//        String creatorContains = "stas";
//        String sortBy = "bla-bla";
//        String sortDirection = "ASC";
//
//        Map<String,String> expectedSearchParams = new HashMap<>();
//        expectedSearchParams.put("nameContains", "changed");
//        expectedSearchParams.put("creatorContains", "stas");
//        expectedSearchParams.put("sortBy", "startupName");
//        expectedSearchParams.put("sortDirection", "ASC");
//
//        Map<String,String> resultSearchParams = startupService.validateSearchParams(nameContains, creatorContains, sortBy, sortDirection);
//        assertEquals(expectedSearchParams, resultSearchParams);
//    }
//
//    @Test
//    public void searchStartup_searchAllStartups_nothingFound(){
//
//        String nameContains= "asdasdasdasdads";
//        String creatorContains = "";
//        String sortBy = "startupName";
//        String sortDirection = "ASC";
//        String accountID = null;
//
//        ArrayList<Startup> emptyStartupList = new ArrayList<>();
//
//        when(startupRepository
//                .searchAllStartups("asdasdasdasdads", "",
//                        new Sort(Sort.Direction.valueOf("ASC"), "startupName")))
//                .thenReturn(new ArrayList<Startup>());
//
//        ArrayList<Startup> stList = new ArrayList<>();
//        Startup st = new Startup();
//        st.setStartupName("New startup");
//        st.setAboutProject("Some info");
//        st.setSumOfInvestment(1000);
//        st.setIdea("Best idea in the world");
//        st.setBusinessPlan("Weell");
//
//        stList.add(st);
//
//        List<Startup> resultStartupList = startupService.searchStartups(nameContains, creatorContains, sortBy, sortDirection, accountID);
//        assertEquals(emptyStartupList, resultStartupList);
//
//    }
//
//    @Test
//    public void searchStartups_searchAllStartups_oneFound(){
//
//        String nameContains= "New startup";
//        String creatorContains = "";
//        String sortBy = "startupName";
//        String sortDirection = "ASC";
//        String accountID = null;
//
//        ArrayList<Startup> startups = new ArrayList<>();
//
//        ArrayList<Startup> expectedStartupList = new ArrayList<>();
//        Startup st = new Startup();
//        st.setStartupName("New startup");
//        st.setAboutProject("Some info");
//        st.setSumOfInvestment(1000);
//        st.setBusinessPlan("Weell");
//        st.setIdea("Best idea in the world");
//
//        expectedStartupList.add(st);
//
//        when(startupRepository
//                .searchAllStartups("new startup", "",
//                        new Sort(Sort.Direction.valueOf("ASC"), "startupName")))
//                .thenReturn(expectedStartupList);
//
//
//        List<Startup> resultStartupList = startupService.searchStartups(nameContains, creatorContains, sortBy, sortDirection, accountID);
//        assertEquals(expectedStartupList, resultStartupList);
//    }
//
//    @Test
//    public void searchStartups_searchMyStartups_oneFoundAsLeader(){
//
//        String nameContains= "MyStartup";
//        String creatorContains = "";
//        String sortBy = "startupName";
//        String sortDirection = "DESC";
//        String accountID = "7c53383a-cdad-4c40-9ff2-9c9b93750af0";
//
//        ArrayList<Startup> emptyStartupList = new ArrayList<>();
//
//        ArrayList<Startup> expectedStartupList = new ArrayList<>();
//        Startup st = new Startup();
//        st.setStartupName("New startup");
//        st.setSumOfInvestment(1000);
//        st.setAboutProject("Some info");
//        st.setIdea("Best idea in the world");
//        st.setBusinessPlan("Weell");
//
//        expectedStartupList.add(st);
//
//        UUID accountUUID = UUID.fromString(accountID);
//
//        when(startupRepository
//                .searchStartupAsLeader("mystartup", accountUUID,
//                        new Sort(Sort.Direction.valueOf("DESC"), "startupName")))
//                .thenReturn(expectedStartupList);
//
//        when(startupRepository
//                .searchStartupsAsMember(accountUUID, "mystartup",
//                        new Sort(Sort.Direction.valueOf("DESC"), "startupName")))
//                .thenReturn(emptyStartupList);
//
//
//        List<Startup> resultStartupList = startupService.searchStartups(nameContains, creatorContains, sortBy, sortDirection, accountID);
//        assertEquals(expectedStartupList, resultStartupList);
//    }
//
//
//    @Test
//    public void searchStartups_searchMyStartups_twoFoundAsLeaderAndAsMember(){
//
//        String nameContains= "MyStartup";
//        String creatorContains = "";
//        String sortBy = "startupName";
//        String sortDirection = "DESC";
//        String accountID = "7c53383a-cdad-4c40-9ff2-9c9b93750af0";
//
//        ArrayList<Startup> emptyStartupList = new ArrayList<>();
//
//        ArrayList<Startup> stListLeader = new ArrayList<>();
//        Startup startupAsLeader = new Startup();
//        startupAsLeader.setStartupName("New startup");
//        startupAsLeader.setAboutProject("Some info");
//        startupAsLeader.setSumOfInvestment(1000);
//        startupAsLeader.setBusinessPlan("Weell");
//        startupAsLeader.setIdea("Best idea in the world");
//
//        stListLeader.add(startupAsLeader);
//
//        ArrayList<Startup> stListMember = new ArrayList<>();
//        Startup startupAsMember = new Startup();
//        startupAsLeader.setStartupName("Member");
//        startupAsLeader.setAboutProject("Some info required");
//        startupAsLeader.setSumOfInvestment(1000);
//        startupAsLeader.setIdea("Best idea in the world");
//        startupAsLeader.setBusinessPlan("Business plan is the best");
//
//
//        stListMember.add(startupAsMember);
//
//        UUID accountUUID = UUID.fromString(accountID);
//
//        when(startupRepository
//                .searchStartupAsLeader("mystartup", accountUUID,
//                        new Sort(Sort.Direction.valueOf("DESC"), "startupName")))
//                .thenReturn(stListLeader);
//
//        when(startupRepository
//                .searchStartupsAsMember(accountUUID, "mystartup",
//                        new Sort(Sort.Direction.valueOf("DESC"), "startupName")))
//                .thenReturn(stListMember);
//
//
//        ArrayList<Startup> expectedStartupList = new ArrayList<>();
//        expectedStartupList.addAll(stListLeader);
//        expectedStartupList.addAll(stListMember);
//        List<Startup> resultStartupList = startupService.searchStartups(nameContains, creatorContains, sortBy, sortDirection, accountID);
//        assertEquals(expectedStartupList, resultStartupList);
//    }
//
//}
