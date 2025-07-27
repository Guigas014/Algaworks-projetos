package com.algaworks.algadelivery.courier.management.api.controller;

import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.algaworks.algadelivery.courier.management.domain.model.Courier;
import com.algaworks.algadelivery.courier.management.domain.repository.CourierRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourierControllerTest {

      @LocalServerPort
      private int port;

      @Autowired
      private CourierRepository courierRepository;

      @BeforeEach
      void setup() {
            RestAssured.port = port;
            RestAssured.basePath = "/api/v1/couriers";
      }

      @Test
      public void shouldReturn201() {
            String requestBody = """
                        {
                              "name": "João da Silva",
                              "phone": "1195500050"
                        }
                        """;

            RestAssured
                        .given()
                        .body(requestBody)
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .when()
                        .post()
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .body("id", Matchers.notNullValue())
                        .body("name", Matchers.equalTo("João da Silva"));
      }

      @Test
      void shouldReturn200() {
            UUID courierId = courierRepository.saveAndFlush(
                        Courier.brandNew("Joana Gonçalves", "11913241232"))
                        .getId();

            RestAssured
                        .given()
                        .pathParam("courierId", courierId)
                        .accept(ContentType.JSON)
                        .when()
                        .get("/{courierId}")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .body("id", Matchers.equalTo(courierId.toString()))
                        .body("name", Matchers.equalTo("Joana Gonçalves"))
                        .body("phone", Matchers.equalTo("11913241232"));
      }
}
