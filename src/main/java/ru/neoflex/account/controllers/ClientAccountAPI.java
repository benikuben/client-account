package ru.neoflex.account.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.account.dtos.Client;
import ru.neoflex.account.dtos.ErrorResponse;
import ru.neoflex.account.dtos.SearchFilter;

import java.util.List;

public interface ClientAccountAPI {
    @PostMapping
    @Operation(
            summary = "Create",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Created successfully"
                    )
            }
    )
    ResponseEntity<Void> create(@RequestBody @Valid Client client, @RequestHeader("x-Source") String source);

    @Operation(
            summary = "Find by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Client.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<Client> findById(@PathVariable("id") Long id);

    @Operation(
            summary = "Search",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Client.class)
                            )
                    )
            }
    )
    @GetMapping("/search/")
    ResponseEntity<List<Client>> search(SearchFilter filters);
}
