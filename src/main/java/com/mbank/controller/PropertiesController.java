package com.mbank.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mbank.model.Properties;
import com.mbank.service.PropertiesService;


@Controller
@SessionAttributes("/properties")
public class PropertiesController {
	
	@Autowired
	private PropertiesService propertiesService;

	@RequestMapping(value="/viewSystemPropertyAdmin", method= RequestMethod.GET)
	public ModelAndView viewSystemPropertyAdmin() {
		List<Properties>list = propertiesService.viewAllProperties();
		return new ModelAndView("/viewSystemPropertyAdmin", "propertiesList" ,list);
	}
	
	@RequestMapping(value = "/updateSystemProperty", method = RequestMethod.GET)
	public String updateSystemProperty(Model model , HttpSession session) {
		
		Properties properties = (Properties)session.getAttribute("properties");
		
		if (properties == null ){
		    properties = new Properties();
		}
		
		model.addAttribute("properties", properties);

		return "/updateSystemProperty";
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/updateSystemProperty" , method= RequestMethod.POST)
	public String updateClientAdmin(@ModelAttribute Properties properties , HttpSession session , BindingResult result ,Map<String, Object> map) {
		Properties prop = new Properties();

      if (result.hasErrors()){
    	  
    	  return "/updateSystemProperty";
    	  
      }
      else{
	
    	  propertiesService.viewAllProperties();
    	  propertiesService.updateSystemProperty(properties.getPropKey(), properties.getPropValue());
    	  prop = properties;
      
      }
      
      map.put("properties", prop);
      map.put("propertiesList", propertiesService.viewAllProperties());
      
      return "redirect:index.jsp";
			
	}
}
