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

import com.mbank.model.Activity;
import com.mbank.model.Clients;
import com.mbank.service.ActivityService;

@Controller
@SessionAttributes("/activity")
public class ActivityController {
	
   public ActivityController() {
	   
	   System.out.println("Activity.contr 8888888888888");
	}

	@Autowired
	private ActivityService activityService;
	
	
	@RequestMapping(value = "/addActivity",  method = RequestMethod.GET)
	public String getActivity(Model model , HttpSession session){
		
		Activity activity = (Activity)session.getAttribute("activity");
		
		if(activity == null){
			
			activity = new Activity();
			
		}
		
		model.addAttribute("activity", activity);
		
		return "/addActivity";
	}

	@RequestMapping(value = "/addActivity",  method = RequestMethod.POST)
	public String addActivity(@Valid @ModelAttribute ("activity") Activity activity, HttpSession session, BindingResult result){
		
		if(result.hasErrors()) {
			return "/addActivity";
		}
		else{
			
			activityService.addActivity(activity);
			
		}
		
		return "redirect:index.jsp";
	}
	
        @PreAuthorize("hasRole('ROLE_ADMIN')")
	    @RequestMapping(value = "/viewActivityDetails", method = RequestMethod.GET)
		public String getActivityDetails(Model model , HttpSession session) {
			
			Activity activity = (Activity)session.getAttribute("activity");
			
			if (activity == null ){
			   activity=new Activity();
			}
			
			model.addAttribute("activity", activity);

			return "/viewActivityDetails";
			
		}

	 
		@RequestMapping(value="/viewActivityDetails" , method= RequestMethod.POST)
		public String viewActivityDetailsAct(@Valid @ModelAttribute ("activity") Activity activity , BindingResult result,  Map<String, Object> map) {
				
	      Activity activityResult = new Activity();

	      Long a = activity.getActivityId();
	      Activity searchedActivity = activityService.viewActivitiesDetails(a);
	      activityResult = searchedActivity != null ? searchedActivity :new Activity();
	       
          map.put("activity", activityResult);
	      map.put("activityList", activityService.getAllActivities());
	      
	      return "/viewActivityDetails";	
		}
	
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		@RequestMapping(value="/viewAllActivitiesDetails", method= RequestMethod.GET)
		public ModelAndView viewAllActivitiesDetails() {
		   
			List<Activity>list = activityService.getAllActivities();
			
			return new ModelAndView( "/viewAllActivitiesDetails" , "activityList" , list);
		}
		
		@RequestMapping(value = "/depositToAccount",  method = RequestMethod.GET)
		public String depositToAccount(Model model , HttpSession session){
			
			Activity activity = (Activity)session.getAttribute("activity");
			
			if(activity == null){
				
				activity = new Activity();
				
			}
			
			model.addAttribute("activity", activity);
			
			return "/depositToAccount";
		}

		@RequestMapping(value = "/depositToAccount",  method = RequestMethod.POST)
		public String depositToAccount(@Valid @ModelAttribute ("activity") Activity activity, HttpSession session, BindingResult result){
			
			if(result.hasErrors()) {
				return "/depositToAccount";
			}
			else{
				
				activityService.depositToAccount(activity.getClientId(), activity.getAmount());
				
			}
			
			return "redirect:index.jsp";
		}
		
		@RequestMapping(value = "/withdrawFromAccount",  method = RequestMethod.GET)
		public String withdrawFromAccount(Model model , HttpSession session){
			
			Activity activity = (Activity)session.getAttribute("activity");
			
			if(activity == null){
				
				activity = new Activity();
				
			}
			
			model.addAttribute("activity", activity);
			
			return "/withdrawFromAccount";
		}

		@RequestMapping(value = "/withdrawFromAccount",  method = RequestMethod.POST)
		public String withdrawFromAccount(@Valid @ModelAttribute ("activity") Activity activity, HttpSession session, BindingResult result){
			
			if(result.hasErrors()) {
				return "/withdrawFromAccount";
			}
			else{
				
				activityService.withdrawFromAccount(activity.getClientId(), activity.getAmount());
				
			}
			
			return "redirect:index.jsp";
		}
		
		 @RequestMapping(value = "/viewActivities", method = RequestMethod.GET)
			public String viewActivities(Model model , HttpSession session) {
				
				Activity activity = (Activity)session.getAttribute("activity");
				
				if (activity == null ){
				   activity=new Activity();
				}
				
				model.addAttribute("activity", activity);

				return "/viewActivities";
				
			}

		 
			@RequestMapping(value="/viewActivities" , method= RequestMethod.POST)
			public String viewActivities(@Valid @ModelAttribute ("activity") Activity activity , BindingResult result,  Map<String, Object> map) {
					
		      Activity activityResult = new Activity();

		      Long a = activity.getActivityId();
		      Activity searchedActivity = activityService.viewActivitiesDetails(a);
		      activityResult = searchedActivity != null ? searchedActivity :new Activity();
		       
	          map.put("activity", activityResult);
		      map.put("activityList", activityService.viewActivitiesDetails(a));
		      
		      return "/viewActivities";	
			}
			

}
