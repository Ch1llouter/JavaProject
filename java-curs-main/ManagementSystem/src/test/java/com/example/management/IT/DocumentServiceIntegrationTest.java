package com.example.management.IT;

import com.example.management.OAuthUtils;
import com.example.management.exception.UserNotFoundException;
import com.example.management.model.dto.DocumentDto;
import com.example.management.service.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@AutoConfigureMockMvc
public class DocumentServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService documentService;

    @Test
    public void saveDocument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/documents")
                .header("Authorization", "Bearer " + OAuthUtils.getAccessToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"registrationNumber\":\"00001-4\",\"title\":\"title-4\",\"keywords\":\"k-4\",\"format\":\"PDF\",\"type\":\"VACATION\",\"link\":\"/vacations\",\"author\":{\n" +
                        "        \"username\": \"user-3\"\n" +
                        "    }}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void saveDocumentUserNotFound() throws Exception {
        doThrow(UserNotFoundException.class).when(documentService).saveDocument(any(DocumentDto.class));
        mockMvc.perform(MockMvcRequestBuilders.post("/documents")
                        .header("Authorization", "Bearer " + OAuthUtils.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"registrationNumber\":\"00001-4\",\"title\":\"title-4\",\"keywords\":\"k-4\",\"format\":\"PDF\",\"type\":\"VACATION\",\"link\":\"/vacations\",\"author\":{\n" +
                                "        \"username\": \"user-n\"\n" +
                                "    }}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteDocument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/documents/1")
                .header("Authorization", "Bearer " + OAuthUtils.getAccessToken())
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
