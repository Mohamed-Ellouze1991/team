package com.omicrone.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.omicrone.entity.Student;
import com.omicrone.feignClient.AddressFeignClient;
import com.omicrone.repository.StudentRepository;
import com.omicrone.request.CreateAddressRequest;
import com.omicrone.request.CreateStudentRequest;
import com.omicrone.response.AddressResponse;
import com.omicrone.response.StudentResponse;

import ch.qos.logback.classic.Logger;

@Service
public class StudentService {
	
 
	@Autowired
	StudentRepository studentRepository;
	
 

	// @Autowired
	// private WebClient webClient;
	@Autowired
	AddressFeignClient addressFeignClient;

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		CreateAddressRequest address = new CreateAddressRequest();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());

		// appel rest de micro service address pour la création de l'adrees
		AddressResponse addressResponse = saveAddress(address);

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());

		student.setAddressId(addressResponse.getId());
		student = studentRepository.save(student);

		return new StudentResponse(student, addressResponse);
	}

	private AddressResponse saveAddress(CreateAddressRequest address) {
//		Mono<AddressResponse> addressResponse = webClient.post()
//			      .uri("/create")
//			      .body(Mono.just(address), CreateAddressRequest.class)
//			      .retrieve()
//			      .bodyToMono(AddressResponse.class);
		// return addressResponse.block();
		return addressFeignClient.createStudent(address);
	}

	public StudentResponse getById(long id) {
		
		 
		Student student = studentRepository.findById(id).get();
		// appel rest de micro service address pour la recherche de l'address par ID
		// AddressResponse addressResponse = getAddressById(student.getAddressId());
		AddressResponse addressResponse = addressFeignClient.getById(student.getAddressId());
		return new StudentResponse(student, addressResponse);
	}

//	private AddressResponse getAddressById(Long addressId) {
//		Mono<AddressResponse> response = webClient.get().uri("/getById/"+addressId).retrieve().bodyToMono(AddressResponse.class);
//		return response.block();
//	}
}
