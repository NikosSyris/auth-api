package com.example.authapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Operation(summary = "Public access endpoint", description = "Accessible to everyone without authentication",
            tags = {"Public Endpoints"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully accessed",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @Operation(summary = "User access endpoint", description = "Requires USER, MODERATOR, or ADMIN role",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Secured Endpoints"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully accessed",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "401", description = "Unauthorized - No valid JWT token provided",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden - Insufficient permissions",
                    content = @Content)
    })
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @Operation(summary = "Moderator access endpoint", description = "Requires MODERATOR role",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Secured Endpoints"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully accessed",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "401", description = "Unauthorized - No valid JWT token provided",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden - Insufficient permissions",
                    content = @Content)
    })
    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @Operation(summary = "Admin access endpoint", description = "Requires ADMIN role",
            security = @SecurityRequirement(name = "bearerAuth"),
            tags = {"Secured Endpoints"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully accessed",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "401", description = "Unauthorized - No valid JWT token provided",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden - Insufficient permissions",
                    content = @Content)
    })
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
