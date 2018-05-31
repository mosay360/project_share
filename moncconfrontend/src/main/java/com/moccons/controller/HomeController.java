package com.moccons.controller;

import com.moccons.domain.Events;
import com.moccons.domain.User;
import com.moccons.domain.security.PasswordResetToken;
import com.moccons.domain.security.Role;
import com.moccons.domain.security.UserRole;

import com.moccons.domain.Items;
import com.moccons.service.EventService;
import com.moccons.service.ItemService;
import com.moccons.service.UserService;
import com.moccons.service.impl.UserSecurityService;
import com.moccons.utility.MailConstructor;
import com.moccons.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.*;


@Controller
public class HomeController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserSecurityService userSecurityService;


     @RequestMapping("/")
    public String index() {
     return "index";
 }
    @RequestMapping("/login")
    public String login(Model model){

        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }



    /*populate items*/

    /*control for faq*/
    @RequestMapping("/faq")
    public String faqs(){
        return "faq";
    }

    @RequestMapping("/bookshelf")
    public String bookshelf(Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }


       /* List<Events> eventsList = eventService.findAll();*/
        List<Items> itemList = itemService.findAll();

      /*  model.addAttribute("eventsList", eventsList);
        model.addAttribute("activeEventList",true);*/

        model.addAttribute("itemList", itemList);
        model.addAttribute("activeAll",true);

        return "bookshelf";
    }

    /*Events control*/
    @RequestMapping("/eventList")
      public String addEvents(Model model){

          List<Events> eventList= eventService.findAll();

          model.addAttribute("eventList", eventList);
          model.addAttribute("activeAll");
          return "redirect:/bookshelf";
      }
     /*Events control*/

    @RequestMapping("/itemDetail")
    public String itemDetail(
            @PathParam("id") Long id, Model model, Principal principal
    ) {
        if(principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        Items items= itemService.findOne(id);

        model.addAttribute("items", items);

        List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        return "itemDetail";
    }

    /*Populate items*/




    @RequestMapping("/forgetPassword")
    public String forgetPassword(
            HttpServletRequest request,
            @ModelAttribute("email") String email,
            Model model
    ) {

        model.addAttribute("classActiveForgetPassword", true);

        User user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("emailNotExist", true);
            return "myAccount";
        }

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        userService.save(user);
        String token = UUID.randomUUID().toString();
        userService.createPasswordTokenForUser(user, token);


        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);

        mailSender.send(newEmail);

        model.addAttribute("forgetPasswordEmailSent", "true");


        return "myAccount";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public  String newUserPost(
        HttpServletRequest request,
        @ModelAttribute("email")String userEmail,
        @ModelAttribute("username") String username,
        Model model
    ) throws  Exception{

model.addAttribute("ClassActiveNewAccount", true);
model.addAttribute("email", userEmail);
model.addAttribute("username", username);
if (userService.findByUsername(username)!=null){
    model.addAttribute("usernameExists",true);
    return "myAccount";
}
  if (userService.findByEmail(userEmail)!=null){
    model.addAttribute("emailExists", true);
    return "myAccount";
  }
  User user = new User();
  user.setUsername(username);
user.setEmail(userEmail);

String password = SecurityUtility.randomPassword();
   String encryptedPassword=SecurityUtility.passwordEncoder().encode(password);
   user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles= new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);

        String token= UUID.randomUUID().toString();
        userService.createPasswordTokenForUser(user, token);
        String appUrl="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(),token, user, password);
        mailSender.send(email);
        model.addAttribute("emailSent",true);
            return "myAccount";
            }

    @RequestMapping("/newUser")
    public String newUser(
            Locale locale,
            @RequestParam("token") String token,
            Model model){
        PasswordResetToken passToken= userService.getPasswordResetToken(token);
if (passToken==null){
    String message="Invalid token";
    model.addAttribute("message", message);
    return  "redirect:/badRequest";
}
        User user = passToken.getUser();
String username=user.getUsername();
        UserDetails userDetails= userSecurityService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("user", user);
        model.addAttribute("classActiveEdit", true);
        return "myProfile";
    }
        @RequestMapping("/myProfile")
    public String myProfile(Model model,Principal principal){
              User user=  userService.findByUsername(principal.getName());
                model.addAttribute("user", user);

                model.addAttribute("ClassActiveEdit", true);
                return "myProfile";
    }
}
