package com.SkyRise.SkyRise_express.controller;

import com.SkyRise.SkyRise_express.model.User;
import com.SkyRise.SkyRise_express.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    // Show login page
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String success,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password.");
        }
        if (success != null) {
            model.addAttribute("success", "Registration successful! Please login.");
        }
        return "pages/login";
    }

    // Process login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email,
                                        @RequestParam String password,
                                        HttpSession session) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) { // ⚠️ Should hash in production
                session.setAttribute("User", user);
                return ResponseEntity.ok("Login successful");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or password.");
    }

    // Show registration page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "pages/registration";
    }

    // Process registration
    @PostMapping("/register")
    public String register(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String dateOfBirth,
                            @RequestParam String email,
                            @RequestParam String phone,
                            @RequestParam String password,
                            @RequestParam String reEnterPassword,
                            @RequestParam(required = false) String passportNumber) {

        if (!password.equals(reEnterPassword)) {
            return "redirect:/register?error=Passwords+do+not+match";
        }

        if (userRepository.existsByEmail(email)) {
            return "redirect:/register?error=Email+already+exists";
        }

        User newUser = new User();
        newUser.setFullName(firstName + " " + lastName);
        newUser.setDob(LocalDate.parse(dateOfBirth));
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setPassword(password); // ⚠️ Save hashed password in real projects
        newUser.setPassportNumber(passportNumber);
        newUser.setRole("User");

        userRepository.save(newUser);

        return "redirect:/login?success=registered";
    }

    // Show Home Page (after login)
    @GetMapping("/")
    public String showHomePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return "redirect:/login"; // Not logged in
        }
        model.addAttribute("User", user);
        return "pages/index"; // Your main page after login
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}