package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@Controller
@Tag(name = "用户管理接口", description = "用户接口文档")
public class UserController {
	
    @Autowired private  UserService service;
    
    
    @GetMapping("")
    @Operation(summary = "欢迎接口", description = "返回欢迎消息")
    public String showHomePage(){
        //System.out.println("Main controller");
        return "index";    // create the corresponding html file
    }
    
    @GetMapping("/login")
    @Operation(summary = "登录接口", description = "返回登录消息")
    public String showLogin() {
    	return "redirect:/users/login";
    }
    
    //Check for Credentials
    @PostMapping("/users/login")
    @Operation(summary = "登录接口", description = "返回登录消息")
    public String login(Login login, Model m,HttpSession session) {
    	
	     String uname = login.getUsername();
	     String pass = login.getPassword();
	     
	     if(uname.equals("Admin") && pass.equals("Admin@123")) {
		      m.addAttribute("uname", uname);
		      m.addAttribute("pass", pass);
		      session.setAttribute("uname", uname);
		      
		      return "redirect:/users";
	     }else {	     
		     m.addAttribute("error", "Incorrect Username & Password");
		     return "login";
	     }
	     
	}
    
    @GetMapping("/users/logout")
    @Operation(summary = "退出接口", description = "返回退出消息")
    public String logout(HttpSession session) {
        session.removeAttribute("uname"); // 清除用户信息
        // session.invalidate(); // 可选：使整个Session失效
        return "index";
    }


    @GetMapping("/users")
    @Operation(summary = "用户列表接口", description = "返回用户列表消息")
    public String showUserList(
    	    @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model
    		){
    	
    	Page<User> users= service.listAll(page, size, keyword);
    	
    	if(users.isEmpty()) {
    		model.addAttribute("msg123", "没有符合条件的记录");
    	}
    	model.addAttribute("currentPage", page);
    	model.addAttribute("users", users);
		model.addAttribute("keyword", keyword);
		
        return "users"; 
    }

    @GetMapping("/users/login")
    public String showLogin(Model model){
        model.addAttribute("pageTitle","User Login");
        return "login";
    }
    
    @GetMapping("/users/register")
    @Operation(summary = "注册接口", description = "返回注册消息")
    public String showRegister( Model model){
    	model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Register New User");
        return "register";
    }
    
    @GetMapping("/users/dashboard")
    @Operation(summary = "仪表板接口", description = "返回统计消息")
    public String showDashboard(Model model){
        model.addAttribute("pageTitle","User Dashboard");
        return "dashboard/index";
    }
    
    @GetMapping("/users/new")
    @Operation(summary = "新用户接口", description = "返回新用户消息")
    public String showNewFom(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    @Operation(summary = "用户保存接口", description = "返回用户消息")
    public String saveUser(User user, RedirectAttributes ra,Model model) {
    	System.out.println(user);
    	try {
    		service.save(user);
    		ra.addFlashAttribute("message", "The user has been saved successfully.");
    		model.addAttribute("message", "The user has been saved successfully.");
    		
    		return "redirect:/users";
    	}catch(Exception ex) {
    		ra.addFlashAttribute("message", "Duplicate entry:  " + user.getEmail()); 
    		model.addAttribute("message", "Duplicate entry:  " + user.getEmail());
    		
    		return "register";
    	}
    	       
    }

    @GetMapping("/users/edit/{id}")
    @Operation(summary = "用户编辑接口", description = "返回用户消息")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }
    
    @GetMapping("/users/{id}")
    @Operation(summary = "指定用户接口", description = "返回用户消息")
    public String showView(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("ids", id);

            return "userDetail";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    @Operation(summary = "用户删除接口", description = "返回用户消息")
    public String deleteUser(@PathVariable("id") Integer id,
    		RedirectAttributes ra) {    	
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
    
    @PostMapping("/users/delete/{id}")
    @Operation(summary = "用户删除接口", description = "返回用户消息")
    public String deleteItem(
    		@PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes) {
        try {
        	service.delete(id);       	
            redirectAttributes.addFlashAttribute("message", "删除成功");
            
            return "redirect:/users";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "删除失败: " + e.getMessage());
            return "redirect:/users";
        }
                
    }
}
