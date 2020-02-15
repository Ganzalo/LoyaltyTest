//package ru.fedorov.controller;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource("/application-test.properties")
//public class AverageVoteControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @DisplayName("Films null")
//    @Test
//    public void getAverageVoteTest() throws Exception {
//
//        this.mockMvc.perform(get("/averageVote/1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("id")));
//
////        Mockito.when(
////                averageVoteService.getAverageVote(Mockito.anyInt())).thenReturn(mockAverageVotes);
////
////        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
////                "/averageVote/18").accept(
////                MediaType.APPLICATION_JSON);
////
////        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
////
////        System.out.println(result.getResponse());
////        String expected = "{id:18,nameGenre:Drama,averageVote:6.53,timestamp:2020-11-05T10:10:01+00:00}";
////
////        JSONAssert.assertEquals(expected, result.getResponse()
////                .getContentAsString(), false);
//    }
//
//}
