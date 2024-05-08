package org.spring.Team_project_1.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


  @GetMapping("/admin")
  public String admin(){


    return "redirect:/admin/member/memberList";
  }





}
