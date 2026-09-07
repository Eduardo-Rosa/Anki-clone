package com.eduardorosa.ankiclone.deck.api;

import com.eduardorosa.ankiclone.deck.Deck;

import java.time.Instant;
import java.util.UUID;

public record DeckResponse(
        UUID id,
        String name,
        String description,
        Instant createdAt,
        Instant updatedAt
) {

    public static DeckResponse from(Deck deck) {
        return new DeckResponse(
                deck.getId(),
                deck.getName(),
                deck.getDescription(),
                deck.getCreatedAt(),
                deck.getUpdatedAt()
        );
    }
}