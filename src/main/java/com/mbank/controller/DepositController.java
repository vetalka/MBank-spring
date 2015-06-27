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

import com.mbank.model.DepositType;
import com.mbank.model.Deposits;
import com.mbank.service.DepositsService;


@Controller
@SessionAttributes("/deposit")
public class DepositController {
	
	public DepositController() {
		System.out.println("Deposit Controller 999999999999");
	}
	
	@Autowired
	private DepositsService depositsService;
	
	@RequestMapping(value="/viewDepositsDetails" , method = RequestMethod.GET)
	public String getDeposit(Model model , HttpSession session){
		
       Deposits deposits =(Deposits)session.getAttribute("deposit");
		
		if(deposits == null){
			
			deposits = new Deposits();
			
		}
		
		model.addAttribute("deposit", deposits);
		
		return "/viewDepositsDetails";
		
	}
	
	@RequestMapping(value = "/viewDepositsDetails",  method = RequestMethod.POST)
	public String postDeposit(@Valid @ModelAttribute ("deposit") Deposits deposits , BindingResult result,  Map<String, Object> map){
		
		Deposits depositsSearch = new Deposits();

		Long a = deposits.getDepositId();
		Deposits de = depositsService.getDeposit(a);
		depositsSearch = de != null ? de : new Deposits();
		
		map.put("deposit", depositsSearch);
		map.put("depositList", depositsService.getAllDeposits());
		
		return "/viewDepositsDetails";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/viewAllDepositsDetails", method= RequestMethod.GET)
	public ModelAndView viewAllDepositDetails() {
	   
		List<Deposits>list = depositsService.getAllDeposits();
		
		return new ModelAndView( "/viewAllDepositsDetails" , "depositList" , list);
	}
	
	@RequestMapping(value="/depositType" , method = RequestMethod.GET)
	public @ResponseBody List<DepositType> findAllDepositType(){
		
		return depositsService.findAllDepositType();
	}
	
	@RequestMapping(value = "/crateNewDeposit", method = RequestMethod.GET)
	public String crateNewDeposit(Model model , HttpSession session) {
		
		Deposits deposits = (Deposits)session.getAttribute("deposit");
		
		if (deposits == null ){
		    deposits = new Deposits();
		}
		
		model.addAttribute("deposit", deposits);

		return "/crateNewDeposit";
		
	}

	@RequestMapping(value="/crateNewDeposit" , method= RequestMethod.POST)
	public String crateNewDeposit(@Valid @ModelAttribute ("deposit") Deposits deposits , HttpSession session , BindingResult result ) {
				
		System.out.println("client Id :" + deposits.getClientId());
      if (result.hasErrors()){
    	  
    	  return "/crateNewDeposit";
    	  
      }
      else{
    	  
          depositsService.crateNewDeposit(deposits, deposits.getClientId());
      
      }
      
		return "redirect:index.jsp";
	}

	@RequestMapping(value = "/preOpenDeposite", method = RequestMethod.GET)
	public String preOpenDeposite(Model model , HttpSession session) {
		
		Deposits deposits = (Deposits)session.getAttribute("deposit");
		
		if (deposits == null ){
		    deposits = new Deposits();
		}
		
		model.addAttribute("deposit", deposits);

		return "/preOpenDeposite";
		
	}

	@RequestMapping(value="/preOpenDeposite" , method= RequestMethod.POST)
	public String preOpenDeposite(@Valid @ModelAttribute ("deposit") Deposits deposits , HttpSession session , BindingResult result ) {
				
		System.out.println("client Id :" + deposits.getClientId());
      if (result.hasErrors()){
    	  
    	  return "/preOpenDeposite";
    	  
      }
      else{
    	  
          depositsService.preOpenDeposite(deposits.getClientId(), deposits.getDepositId());;
      
      }
      
		return "redirect:index.jsp";
	}
	
	@RequestMapping(value="/viewDeposit" , method = RequestMethod.GET)
	public String viewDeposit(Model model , HttpSession session){
		
       Deposits deposits =(Deposits)session.getAttribute("deposit");
		
		if(deposits == null){
			
			deposits = new Deposits();
			
		}
		
		model.addAttribute("deposit", deposits);
		
		return "/viewDeposit";
		
	}
	
	@RequestMapping(value = "/viewDeposit",  method = RequestMethod.POST)
	public String viewDeposit(@Valid @ModelAttribute ("deposit") Deposits deposits , BindingResult result,  Map<String, Object> map){
		
		Deposits depositsSearch = new Deposits();

		Long a = deposits.getDepositId();
		Deposits de = depositsService.getDeposit(a);
		depositsSearch = de != null ? de : new Deposits();
		
		map.put("deposit", depositsSearch);
		map.put("depositList", depositsService.getDeposit(a));
		
		return "/viewDeposit";
	}
}
