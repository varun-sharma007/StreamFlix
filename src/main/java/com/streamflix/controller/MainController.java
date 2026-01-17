package com.streamflix.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.streamflix.model.Movie;
import com.streamflix.model.User;
import com.streamflix.repository.MovieRepository;
import com.streamflix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired private MovieRepository movieRepo;
    @Autowired private UserRepository userRepo;

    // We simply ask Spring to give us the Cloudinary tool (created in CloudConfig.java)
    @Autowired private Cloudinary cloudinary;

    // --- AUTH ROUTES ---
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        if (userRepo.findByEmail(email) != null) return Map.of("status", "error", "message", "User exists");
        User u = new User(); u.setEmail(email); u.setPassword(payload.get("password")); u.setRole("USER");
        userRepo.save(u);
        return Map.of("status", "success");
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String pass = payload.get("password");
        if ("vs446412@gmail.com".equals(email) && "1234".equals(pass))
            return Map.of("status", "success", "role", "ADMIN", "email", email);
        User u = userRepo.findByEmail(email);
        if (u != null && u.getPassword().equals(pass))
            return Map.of("status", "success", "role", "USER", "email", email);
        return Map.of("status", "error", "message", "Invalid credentials");
    }

    // --- MOVIE ROUTES ---
    @GetMapping("/movies")
    public List<Movie> getAllMovies() { return movieRepo.findAll(); }

    @GetMapping("/admin/stats")
    public Map<String, Long> getStats() { return Map.of("userCount", userRepo.count(), "movieCount", movieRepo.count()); }

    // --- CLOUD UPLOAD ---
    @PostMapping("/admin/upload")
    public Map<String, String> uploadVideo(@RequestParam("file") MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            return Map.of("url", (String) uploadResult.get("secure_url"));
        } catch (IOException e) {
            e.printStackTrace();
            return Map.of("error", "Upload failed");
        }
    }

    @PostMapping("/admin/movies")
    public Movie addMovie(@RequestBody Movie movie) { return movieRepo.save(movie); }

    @PutMapping("/admin/movies/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie m) {
        Movie old = movieRepo.findById(id).orElseThrow();
        old.setTitle(m.getTitle()); old.setThumbnailUrl(m.getThumbnailUrl()); old.setVideoUrl(m.getVideoUrl());
        return movieRepo.save(old);
    }

    @DeleteMapping("/admin/movies/{id}")
    public void deleteMovie(@PathVariable Long id) { movieRepo.deleteById(id); }
}