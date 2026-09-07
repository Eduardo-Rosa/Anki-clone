package com.eduardorosa.ankiclone.deck;

import java.util.UUID;

public class DeckNotFoundException extends RuntimeException {

    public DeckNotFoundException(UUID id) {
        super("Deck not found: " + id);
    }
}