package com.project.Library.Management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Library.Management.entity.Book;
import com.project.Library.Management.repository.BookRepository;
import com.project.Library.Management.request.BookRequest;
import com.project.Library.Management.request.BorrowingRequest;
import com.project.Library.Management.request.CreateUserRequest;
import com.project.Library.Management.request.UpdateUserRequest;
import com.project.Library.Management.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class  LibraryManagementApplicationTests {
	private static MockHttpServletRequest mockHttpServletRequest;
	@Mock
	BookService bookService;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	BookRequest bookRequest;
	@Autowired
	CreateUserRequest createUserRequest;
	@Autowired
	BorrowingRequest borrowingRequest;
	@Autowired
	UpdateUserRequest updateUserRequest;

	@Test
	void findAllBooksTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books").
				 header("Authorization","Basic bWlrZTp0ZXN0"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}


	@Test
	public void createBookTest() throws Exception {
		bookRequest.setAuthor("Tony");
		bookRequest.setIsbn("1111111111119");
		bookRequest.setTitle("A moonlight tale");
		bookRequest.setPublicationYear("1998");
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/books")
						.header("Authorization","Basic bWlrZTp0ZXN0")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(bookRequest)))
				.andExpect(status().isOk());
	}
	@Test
	public void updateBookTest() throws Exception {
		bookRequest.setAuthor("Tony");
		bookRequest.setIsbn("1111111111117");
		bookRequest.setTitle("Business info");
		bookRequest.setPublicationYear("1997");
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/books/{id}",1)
						.header("Authorization","Basic bWlrZTp0ZXN0")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(bookRequest)))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteBookByIdWithWrongCredentialTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/books/{id}",1)
				.header("Authorization","Basic bWlrZTp0ZXN05"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void deleteBookTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/books/{id}",5)
						.header("Authorization","Basic bWlrZTp0ZXN0"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void findAllPatronsTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/patrons").
						header("Authorization","Basic bWlrZTp0ZXN0"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void findPatronsByIdTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/patrons/{id}",1).
						header("Authorization","Basic am9objI6am9obkAxMjM="))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}


	@Test
	public void createPatronTest() throws Exception {
		createUserRequest.setEmail("test@gmail.com");
		createUserRequest.setPhone("09019056988");
		createUserRequest.setUsername("lomame");
		createUserRequest.setPassword("test@gl");
		createUserRequest.setFirstName("testing");
		createUserRequest.setLastName("test");
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/patrons")
						.header("Authorization","Basic bWlrZTp0ZXN0")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(createUserRequest)))
				.andExpect(status().isOk());
	}

	@Test
	public void deletePatronTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/books/{id}",1)
						.header("Authorization","Basic bWlrZTp0ZXN0"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}


	@Test
	public void borrowBookTest() throws Exception{
		borrowingRequest.setReturnDate(new SimpleDateFormat("yyyy-mm-dd").parse("2024-10-10"));
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/borrow/{bookId}/patron/{patronId}",1,3)
				.content(objectMapper.writeValueAsBytes(borrowingRequest)).
		         contentType(MediaType.APPLICATION_JSON).header("Authorization","Basic am9objI6am9obkAxMjM="))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void returnBookTest() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/return/{bookId}/patron/{patronId}",1,3)
						.header("Authorization","Basic am9objI6am9obkAxMjM="))
				.andExpect(status().isOk());
	}




}
