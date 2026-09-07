package com.eduardorosa.ankiclone.deck;

import com.eduardorosa.ankiclone.deck.api.CreateDeckRequest;
import com.eduardorosa.ankiclone.deck.api.DeckResponse;
import com.eduardorosa.ankiclone.deck.api.UpdateDeckRequest;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class DeckService {

    private final DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Transactional
    public DeckResponse create(CreateDeckRequest request) {

        var deck = new Deck(
                request.name(),
                request.description()
        );

        var savedDeck = deckRepository.save(deck);

        return DeckResponse.from(savedDeck);
    }

    public List<DeckResponse> findAll() {

        return deckRepository
                .findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(DeckResponse::from)
                .toList();
    }

    public DeckResponse findById(UUID id) {

        return deckRepository
                .findById(id)
                .map(DeckResponse::from)
                .orElseThrow(() -> new DeckNotFoundException(id));
    }

    @Transactional
    public DeckResponse update(UUID id, UpdateDeckRequest request) {

        var deck = findEntityById(id);

        deck.update(
                request.name(),
                request.description()
        );

        return DeckResponse.from(deck);
    }

    @Transactional
    public void delete(UUID id) {

        var deck = findEntityById(id);

        deckRepository.delete(deck);
    }

    private Deck findEntityById(UUID id) {

        return deckRepository
                .findById(id)
                .orElseThrow(() -> new DeckNotFoundException(id));
    }
}