package com.example.game.controllerTest;

import com.example.game.GameApplication;
import com.example.game.controller.ServiceController;
import com.example.game.exceptions.UserNotFondException;
import com.example.game.service.PlayerService;
import com.example.game.transferObjects.PlayerProfile;
import com.example.game.transferObjects.PlayerProfileBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameApplication.class)
@WebAppConfiguration
public class ServiceControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private PlayerService playerService;

    @Autowired
    private ServiceController serviceController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(playerService);
        Mockito.reset(playerService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        ReflectionTestUtils.setField(serviceController, "playerService", playerService);
    }

    @Test
    public void shouldReturnOnePlayerTest() throws Exception {
        //given
        PlayerProfile playerProfile = new PlayerProfileBuilder()
                .setFirstName("Tomaszk").setLastName("Nowy").setEmail("email@wp.pl")
                .setId(1L).setMotto("motto").build();

        Mockito.when(playerService.getMyProfile(Mockito.any())).thenReturn(playerProfile);
        // when
        ResultActions resultActions = mockMvc.perform(get("/players/2"));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("Tomaszk"))
                .andExpect(jsonPath("id").value(1L));


//        ResultActions resultActions = mockMvc.perform(post("/rest/test/create").content(json));
    }

    @Test
    public void shouldReturnPlayerTest() throws Exception {
        //given
        List<PlayerProfile> playerProfiles = new ArrayList<>();
        playerProfiles.add(new PlayerProfileBuilder().setFirstName("Adam").setLastName("Pierwszy").build());
        playerProfiles.add(new PlayerProfileBuilder().setFirstName("Basia").setLastName("Druga").build());
        Mockito.when(playerService.getPlayerProfileList()).thenReturn(playerProfiles);
        //when
        ResultActions resultActions = mockMvc.perform(get("/players"));
        //then
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0]firstName")
                .value("Adam")).andExpect(jsonPath("$[1]firstName").value("Basia"));
    }

    @Test
    public void shouldEditExistedPlayerTest() throws Exception {
        // given
        PlayerProfile playerProfile = new PlayerProfileBuilder().setFirstName("Adam").setLastName("Pierwszy").build();
        Mockito.when(playerService.editMyProfile(Mockito.any(), Mockito.any())).thenReturn(playerProfile);
        // when
        ResultActions resultActions = mockMvc.perform(put("/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1,\n" +
                        "        \"firstName\": \"Basia\",\n" +
                        "        \"lastName\": \"Piergjtrsy123\",\n" +
                        "        \"email\": \"tomek3@wp.pl\",\n" +
                        "        \"motto\": \"motto1\"\n" +
                        "}"));
        //then
        resultActions.andExpect(status().isCreated());
        Mockito.verify(playerService, Mockito.times(1))
                .editMyProfile(Mockito.any(), Mockito.any());
        Mockito.verifyNoMoreInteractions(playerService);


    }

    @Test
    public void shouldFilterPlayersTest() throws Exception {
        // given
        List<PlayerProfile> playerProfiles = new ArrayList<>();
        playerProfiles.add(new PlayerProfileBuilder().setFirstName("Adam").setLastName("Pierwszy").build());
        playerProfiles.add(new PlayerProfileBuilder().setFirstName("Basia").setLastName("Druga").build());

        Mockito.when(playerService.getPlayerProfilesByFilter(Mockito.any())).thenReturn(playerProfiles);
        // when
        ResultActions resultActions = mockMvc.perform(put("/players/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {     \"firstName\": \"Zosia\",\n" +
                        "        \"lastName\": \"Pierwszy\",\n" +
                        "        \"email\": null,\n" +
                        "        \"motto\": null\n" +
                        " }"));
        //then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$[0]firstName").value("Adam"))
                .andExpect(jsonPath("$[1]firstName").value("Basia"));
        Mockito.verify(playerService, Mockito.times(1))
                .getPlayerProfilesByFilter(Mockito.any());
        Mockito.verifyNoMoreInteractions(playerService);
    }

    @Test
    public void shoudThrowNoSuchElementExceptionByIdOutOfRangeTest() throws Exception {
        // given
        Mockito.when(playerService.getPlayerProfilesByFilter(Mockito.any()))
                .thenThrow(NoSuchElementException.class);

        // when
        ResultActions resultActions = mockMvc.perform(get("players/1"));
        // then
        resultActions.andExpect(status().isNotFound());
    }

    public void shouldThrowUserNotFoundExceptionByFilterPlayersTest() throws Exception {
        // given
        Mockito.when(playerService.getMyProfile(Mockito.anyLong()))
                .thenThrow(UserNotFondException.class);
        // when
        ResultActions resultActions = mockMvc.perform(get("player/1"));
        // then
        resultActions.andExpect(status().isNotFound());
    }

}
