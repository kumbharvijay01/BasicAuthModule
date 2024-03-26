/**
 * 
 */
package com.example.springsecuritymodule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
public class IndexController {

	@GetMapping("/home")
	public String home() {

		return "Hello World";

	}

	@GetMapping("/getHealthcheck")
	public String getHealthcheck() {
		return "getHealthcheck";
	}

}
