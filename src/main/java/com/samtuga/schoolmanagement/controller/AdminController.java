package com.samtuga.schoolmanagement.controller;

import com.samtuga.schoolmanagement.model.Admin;
import com.samtuga.schoolmanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String Login(){
        return "/login";
    }

    @PostMapping("/add")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
        return new ResponseEntity<Admin>(adminService.saveAdmin(admin), HttpStatus.CREATED);
    }

    @GetMapping("/view")
    public List<Admin> viewAdmin(){
        return adminService.ViewAdmin();
    }

//    @GetMapping("/searchAdmin/{lastName}")
//    public ResponseEntity<Admin> searchAdmin(@PathVariable("lastName") String firstName){
//        return new ResponseEntity<Admin>(adminService.searchAdmin(firstName),HttpStatus.FOUND);
//    }

    @DeleteMapping("/remove/{id}")
    public void deleteAdmin(@PathVariable("id") long id){
        adminService.removeAdmin(id);
    }

    @PutMapping("/update/{id}")
    public void updateAdmin(@PathVariable("id") long id ,@RequestBody Admin admin){
        adminService.updateAdmin(id,admin);
    }

}
