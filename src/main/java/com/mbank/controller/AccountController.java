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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mbank.model.Accounts;
import com.mbank.service.AccountService;

@Controller
@SessionAttributes("account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/addAccount",  method = RequestMethod.GET)
	public String getBalance(Model model , HttpSession session){
		
		Accounts accounts = (Accounts)session.getAttribute("account");
		
		if(accounts == null){
			
			accounts = new Accounts();
			
		}
		
		model.addAttribute("account", accounts);
		
		return "addAccount";
	}

	@RequestMapping(value = "/addAccount",  method = RequestMethod.POST)
	public String addAccount(@Valid @ModelAttribute ("account") Accounts account , HttpSession session, BindingResult result){
		
		if(result.hasErrors()) {
			return "addAccount";
		}
		else{
	
			accountService.addClient(account);
			
		}
		
		return "redirect: addActivity.html";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/viewAccountDetails" , method = RequestMethod.GET)
	public String viewAccountDetails(Model model , HttpSession session){
		
        Accounts accounts = (Accounts)session.getAttribute("account");
		
		if(accounts == null){
			
			accounts = new Accounts();
			
		}
		
		model.addAttribute("account", accounts);
		
		return "viewAccountDetails";
		
	}
	
	@RequestMapping(value = "/viewAccountDetails",  method = RequestMethod.POST)
	public String viewAccountDetails(@Valid @ModelAttribute ("account") Accounts account , HttpSession session, BindingResult result,  Map<String, Object> map){
		
		Accounts accounts = new Accounts();
		
		if(result.hasErrors()) {
			return "/viewAccountDetails";
		}
		else{
			
			Accounts ac=accountService.ViewAccountDetails(account.getAccountId());
			accounts = ac != null ? ac : new Accounts();
		}
		
		map.put("account", accounts);
		map.put("accountList", accountService.getAllAccounts());
		
		return "viewAccountDetails";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/viewAllAccountDetails", method= RequestMethod.GET)
	public ModelAndView viewAllAccountDetails() {
	   
		List<Accounts>list = accountService.getAllAccounts();
		
		return new ModelAndView( "/viewAllAccountDetails" , "accountList" , list);
	}
	
	@RequestMapping(value="/viewAccount" , method = RequestMethod.GET)
	public String viewAccount(Model model , HttpSession session){
		
        Accounts accounts = (Accounts)session.getAttribute("account");
		
		if(accounts == null){
			
			accounts = new Accounts();
			
		}
		
		model.addAttribute("account", accounts);
		
		return "viewAccount";
		
	}
	
	@RequestMapping(value = "/viewAccount",  method = RequestMethod.POST)
	public String viewAccount(@Valid @ModelAttribute ("account") Accounts account , HttpSession session, BindingResult result,  Map<String, Object> map){
		
		Accounts accounts = new Accounts();
		
		if(result.hasErrors()) {
			return "/viewAccount";
		}
		else{
			
			Accounts ac=accountService.ViewAccountDetails(account.getAccountId());
			accounts = ac != null ? ac : new Accounts();
		}
		
		map.put("account", accounts);
		map.put("accountList", accountService.ViewAccountDetails(accounts.getAccountId()));
		
		return "viewAccount";
	}
}
