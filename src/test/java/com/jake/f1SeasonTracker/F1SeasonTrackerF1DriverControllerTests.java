package com.jake.f1SeasonTracker;

import com.jake.f1SeasonTracker.driver.F1Driver;
import com.jake.f1SeasonTracker.driver.F1DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class F1SeasonTrackerF1DriverControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private F1DriverRepository f1DriverRepository;

    @Test
    public void testNewDriverRest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/newDriver"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("newDriver"))
                .andReturn();

        ModelAndView mav = result.getModelAndView();
        assertNotNull(mav, "ModelAndView should not be null");

        F1Driver driver = (F1Driver) mav.getModel().get("driver");
        assertNotNull(driver, "F1Driver object should not be null");

        // Add any additional assertions as needed for the F1Driver object or the view
    }

    @Test
    public void testNewDriverPost() throws Exception {
        F1Driver mockF1Driver = new F1Driver(); // You need to create an instance of F1Driver with test data.

        // Simulate the POST request to "/newDriver" with the mockF1Driver as the request body.
        mockMvc.perform(MockMvcRequestBuilders.multipart("/newDriver")
                        .flashAttr("driver", mockF1Driver))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("driverlist"));

        // Verify that the f1DriverRepository.save method is called with the correct F1Driver instance.
        Mockito.verify(f1DriverRepository, times(1)).save(mockF1Driver);
        // Verify any other behavior or assertions related to the test, if applicable.
    }

    @Test
    public void testNewDriverWithImage() throws Exception {
        Path path = Paths.get("src/test/resources/test_driver_logo.png");
        String name = "Test Driver";
        String password = "testpassword";

        byte[] imageBytes = Files.readAllBytes(path);

        MockMultipartFile imageFile = new MockMultipartFile("imagePicker", "test_image.jpg", "image/jpeg", imageBytes);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/newDriver")
                        .file(imageFile)
                        .param("f1DriverName", name)
                        .param("password", password))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("driverlist"));
    }
}