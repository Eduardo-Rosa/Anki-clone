package com.eduardorosa.ankiclone.deck.api;

import com.eduardorosa.ankiclone.deck.DeckService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/decks")
public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @PostMapping
    public ResponseEntity<DeckResponse> create(
            @Valid @RequestBody CreateDeckRequest request
    ) {

        var response = deckService.create(request);

        var location = URI.create(
                "/api/v1/decks/" + response.id()
        );

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @GetMapping
    public List<DeckResponse> findAll() {
        return deckService.findAll();
    }

    @GetMapping("/{id}")
    public DeckResponse findById(
            @PathVariable UUID id
    ) {
        return deckService.findById(id);
    }

    @PutMapping("/{id}")
    public DeckResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateDeckRequest request
    ) {
        return deckService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        deckService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}