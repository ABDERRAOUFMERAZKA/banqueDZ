package org.did.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@RequestMapping(value="/login")
 public String login(){
	 return "login";
	 }
	 @RequestMapping(value="/")
	 public String home(){
		 return "redirect:/Operations";
	 }
	 
	 @RequestMapping(value="/403")
	 public String accesDenied(){
		 return "403";
	 }
	 
}
