package com.backend.flex.model.components.props.showcase;

import java.util.List;

public class Showcase {
    private List<Participant> participant;

    public Showcase() {
    }

    public Showcase(List<Participant> participant) {
        this.participant = participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }

    public List<Participant> getParticipant() {
        return participant;
    }
}
