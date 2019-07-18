package com.exadelinternship.carpool.tests;

import com.exadelinternship.carpool.Application;
import com.exadelinternship.carpool.dto.CarDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class,webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerIntegrationTest{
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(){
        return "http://localhost:"+port;
    }

    @Test
    public void contextLoads(){

    }

    @Test
    public void testGetAllCars(){
        HttpHeaders headers=new HttpHeaders();
        HttpEntity<String> entity=new HttpEntity<String>(null,headers);
        ResponseEntity<String> response=restTemplate.exchange(getRootUrl()+"/profile/cars",
               HttpMethod.GET,entity,String.class );
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetCarById(){
        CarDTO carDTO=restTemplate.getForObject(getRootUrl()+"/profile/cars/12",CarDTO.class);
        System.out.println(carDTO.getCarInformation());
        assertNotNull(carDTO);
    }

    @Test
    public void testCreateCar(){
        CarDTO carDTO=new CarDTO();
        carDTO.setUserId(1);
        carDTO.setCapacity(4);
        carDTO.setCarInformation("SDCVBNHFDC Nfcvabdsfjkd ");
        ResponseEntity<CarDTO> postResponse=restTemplate.postForEntity(getRootUrl()+"/profile/cars",
                carDTO, CarDTO.class);
        assertNotNull(postResponse);
    }

    @Test
    public void testUpdateCar(){
        int id=12;
        CarDTO carDTO = restTemplate.getForObject(getRootUrl() + "/profile/cars/" + id, CarDTO.class);
        carDTO.setCarInformation("qwertyfdsasdf");
        restTemplate.put(getRootUrl() + "/profile/cars/" + id, carDTO);
        CarDTO updatedEmployee = restTemplate.getForObject(getRootUrl() + "/profile/cars/" + id, CarDTO.class);
        assertNotNull(updatedEmployee);
    }

    @Test
    public void testDeleteCar(){
        int id=16;
        CarDTO carDTO=restTemplate.getForObject(getRootUrl()+"/profile/cars/"+id,CarDTO.class);
        assertNotNull(carDTO);
        restTemplate.delete(getRootUrl()+"/profile/cars/"+id);
        try {
            carDTO = restTemplate.getForObject(getRootUrl() + "/profile/cars/" + id, CarDTO.class);
        }
        catch(final HttpClientErrorException e){
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}