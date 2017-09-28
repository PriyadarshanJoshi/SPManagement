package com.neosoft.SportsClubManagementWeb.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@ManagedBean(value = "userController")
@Component("userController")
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	/*@RequestMapping(value="/home", method= RequestMethod.GET)
	public ModelAndView loginUser(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout)
	{

		 {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("home");

		return model;

	}
	}
	*/
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@RequestMapping(value="/index", method= RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout)
	{
		System.out.println("In login");
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("index");

		return model;

	}

	
	public String loginUser()
	{		
		
		try {
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(this.name, this.password);
            System.out.println("Authentication : "+token);
            
			Authentication authenticate = this.authenticationManager.authenticate(token);
			System.out.println("Authenticate : "+authenticate);
			SecurityContextHolder.getContext().setAuthentication(authenticate);
          /*  if(request.equals(null))
            	return "error";
            else
            	return "home";*/
            return "home";
        } catch (Exception e) {
        	System.out.println("Exception Occured : "+e.getClass());
            e.printStackTrace();
            return "error";
        }
      

		  /*Authentication authenticationToken = new UsernamePasswordAuthenticationToken(this.name, this.password);
		    try {
		    	HttpRequest request = null;
		        Authentication authentication = authenticationManager
		                .authenticate(authenticationToken);


		        SecurityContext securityContext = SecurityContextHolder
		                .getContext();

		        securityContext.setAuthentication(authentication);

		        HttpSession session = (HttpSession) ((ExternalContext) request).getSession(true);
		        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

		        return "home";
		    } catch (AuthenticationException ex) {
		        return "fail " + ex.getMessage();
		    }*/
        
		/* ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

	        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
	                .getRequestDispatcher("/j_spring_security_check");

	        try {
				dispatcher.forward((ServletRequest) context.getRequest(),
				        (ServletResponse) context.getResponse());
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        FacesContext.getCurrentInstance().responseComplete();

	        return "home";*/
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.POST)
	 public ModelAndView accesssDenied() {
	  ModelAndView model = new ModelAndView();
	  // check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
	   UserDetails userDetail = (UserDetails) auth.getPrincipal();
	   model.addObject("username", userDetail.getUsername());
	  }

	  model.setViewName("403");
	  return model;

	 }
	
	@RequestMapping(value="/home", method= RequestMethod.GET)
	public String afterLogin()
	{
		return "home";
	}
	/*public String loginUser()
	{
		System.out.println("In controller");
		try
		{
			if(this.name.equals("Sonu") && this.password.equals("abc123"))
			{
				return "home";
			}
			else
			{
				return "error";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "home";
	}*/
	
	@RequestMapping(value="/logout", method= RequestMethod.GET)
	public String logout(){
        SecurityContextHolder.clearContext();
        return "index";
    }
}
