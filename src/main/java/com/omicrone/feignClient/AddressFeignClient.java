package com.omicrone.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.omicrone.request.CreateAddressRequest;
import com.omicrone.response.AddressResponse;

@FeignClient(value = "address-service", 
path="/api/address ")
public interface AddressFeignClient {


@GetMapping("/getById/{id}")
public AddressResponse getById(@PathVariable long id);


@PostMapping("/create")
public AddressResponse createStudent (@RequestBody CreateAddressRequest createAddressRequest);

}


