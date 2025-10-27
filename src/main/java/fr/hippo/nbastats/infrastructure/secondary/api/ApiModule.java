package fr.hippo.nbastats.infrastructure.secondary.api;

import java.util.Collection;

class ApiModule {

    private Collection<ApiCard> cards;

    public Collection<ApiCard> getCards() {
        return cards;
    }

    public void setCards(Collection<ApiCard> cards) {
        this.cards = cards;
    }
}
