package com.msdp.delivery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author karuneshkumar.s
 *
 */
@Controller
public class Index {
	/**
	 * Sets the index page mapping to point to the Swagger UI.
	 *
	 * @return A redirect to the Swagger UI.
	 */
	@RequestMapping("/")
	public String index() {
		return "redirect:/swagger-ui/index.html";
	}

}
