package com.mbank.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mbank.model.ClientType;
import com.mbank.model.Clients;
import com.mbank.service.ClientService;

@Controller
@SessionAttributes("/client")
public class ClientController {
	public  ClientController() {
		System.out.println("ClientController.CTOR  77777777777");
	}
	
	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/addClient", method = RequestMethod.GET)
	public String addClient(Model model , HttpSession session) {
		
		Clients client = (Clients)session.getAttribute("client");
		
		if (client == null ){
		client = new Clients();
		}
		
		model.addAttribute("client", client);

		return "/addClient";
		
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/addClient" , method= RequestMethod.POST)
	public String addClient(@Valid @ModelAttribute ("client") Clients clients , HttpSession session , BindingResult result ) {
		
      System.out.println("Client Name : " + clients.getClientName());
		
      if (result.hasErrors()){
    	  
    	  return "/addClient";
    	  
      }
      else{
    	  
          clientService.addClient(clients);
      
      }
      
		return "redirect: addAccount.html";
			
	}
	
	
	
//	@RequestMapping(value="/getAll", method= RequestMethod.GET)
//	public @ResponseBody List<Clients> getAll() {
//		return clientService.getAllClients();
//	}


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/viewAllClientDetails", method= RequestMethod.GET)
	public ModelAndView viewAllClientDetails() {
		List<Clients>list = clientService.getAllClients();
		return new ModelAndView("/viewAllClientDetails", "clientList" ,list);
	}
	
	
	@RequestMapping(value="/clientType" , method = RequestMethod.GET)
	public @ResponseBody List<ClientType> findAllClientType(){
		
		return clientService.findAllClientType();
	}
	
	@RequestMapping(value = "/removeClient", method = RequestMethod.GET)
	public String removeClient(Model model , HttpSession session) {
		
		Clients client = (Clients)session.getAttribute("client");
		
		if (client == null ){
		client = new Clients();
		}
		
		model.addAttribute("client", client);

		return "/removeClient";
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/removeClient" , method= RequestMethod.POST)
	public String removeClient(@Valid @ModelAttribute ("client") Clients client , HttpSession session , BindingResult result , Map<String, Object> map ) {
		
	  Clients clients = new Clients();
		
      if (result.hasErrors()){
    	  
    	  return "/removeClient";
    	  
      }
      else{

          clientService.removeClient(client.getClientId());
          clients = new Clients();
          
      }
      
      map.put("client", clients);
      map.put("clientList", clientService.getAllClients());
      
      return "redirect:index.jsp";
			
	}
	
	@RequestMapping(value = "/updateClientAdmin", method = RequestMethod.GET)
	public String updateClientAdmin(Model model , HttpSession session) {
		
		Clients client = (Clients)session.getAttribute("client");
		
		if (client == null ){
		client = new Clients();
		}
		
		model.addAttribute("client", client);

		return "/updateClientAdmin";
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/updateClientAdmin" , method= RequestMethod.POST)
	public String updateClientAdmin(@ModelAttribute Clients client , HttpSession session , BindingResult result ,Map<String, Object> map) {
		Clients clients = new Clients();

      if (result.hasErrors()){
    	  
    	  return "/updateClientAdmin";
    	  
      }
      else{
	  
    	  
    	  clientService.getAllClients();
    	  clientService.updateClientAdmin(client.getClientId() , client.getClientType() , client.getComment());
    	  clients = client;
      
      }
      
      map.put("client", clients);
      map.put("clientList", clientService.getAllClients());
      
      return "redirect:index.jsp";
			
	}

//	  @RequestMapping(method = RequestMethod.GET)
//	    public String setupForm(Map<String, Object> map) {
//	        
//	        Clients client = new Clients();
//	        map.put("client", client);
//	        map.put("clientList", clientService.getAllClients());
//	        return "client";
//	    }
	  
	  @RequestMapping(value = "/viewClientDetails", method = RequestMethod.GET)
		public String viewClientDetails(Model model , HttpSession session) {
			
			Clients client = (Clients)session.getAttribute("client");
			
			if (client == null ){
			client = new Clients();
			}
			
			model.addAttribute("client", client);

			return "/viewClientDetails";
			
		}
		
		@RequestMapping(value="/viewClientDetails" , method= RequestMethod.POST)
		public String viewClientDetails(@Valid @ModelAttribute ("client") Clients client  , BindingResult result ,  Map<String, Object> map) {
			
	      System.out.println("Client Name : " + client.getClientId());
			Clients clients = new Clients();
	      
	      if (result.hasErrors()){
	    	  
	    	  return "/viewClientDetails";
	    	  
	      }
	      else{

	         Clients searchedClient =  clientService.viewClientDetails(client.getClientId());
	         clients = searchedClient != null ? searchedClient :new Clients();  
	          
	      }    
	      
	      map.put("client", clients);
	      map.put("clientList", clientService.getAllClients());
	      
	      return "viewClientDetails" ;
				
		}
		
		
//		  @RequestMapping(value ="/viewAllClientDetails" ,method = RequestMethod.GET)
//	    public String setupForm(Map<String, Object> map) {      
//	        map.put("clientList", clientService.getAllClients());
//	        return "/viewAllClientDetails";
//	    }
//		  
//		  @RequestMapping(value = "/viewAllClientDetails", method = RequestMethod.POST)
//		    public String doActions(Map<String, Object> map) {
//		
//			  //map.put("clientList", clientService.getAllClients());
//		        return "/viewAllClientDetails";
//		  }
		
//		 @RequestMapping(value ="/viewAllClientDetails" ,method = RequestMethod.GET)
//		    public String setupForm(List<Clients> map) {      
//		        map.addAll( clientService.getAllClients());
//		        return "/viewAllClientDetails";
//		    }
//			  
//			  @RequestMapping(value = "/viewAllClientDetails", method = RequestMethod.POST)
//			    public String doActions(List<Clients> map) {
//			
//				 // map.put("clientList", clientService.getAllClients());
//			        return "/viewAllClientDetails";
//			  }
		
		@RequestMapping(value = "/updateClient", method = RequestMethod.GET)
		public String updateClient(Model model , HttpSession session) {
			
			Clients client = (Clients)session.getAttribute("client");
			
			if (client == null ){
			client = new Clients();
			}
			
			model.addAttribute("client", client);

			return "/updateClient";
			
		}
		
		@RequestMapping(value="/updateClient" , method= RequestMethod.POST)
		public String updateClient(@ModelAttribute Clients client , HttpSession session , BindingResult result ,Map<String, Object> map) {
			Clients clients = new Clients();

	      if (result.hasErrors()){
	    	  
	    	  return "/updateClient";
	    	  
	      }
	      else{
		  
	    	  
	    	  clientService.getAllClients();
	    	  clientService.updateClient(client.getClientId(), client.getAddress(), client.getEmail(), client.getPhone());
	    	  clients = client;
	      
	      }
	      
	      map.put("client", clients);
	      map.put("clientList", clientService.getAllClients());
	      
	      return "redirect:index.jsp";
				
		}
}
