package com.backend.flex.model.components.props.faq;

import java.util.List;

public class Faqs {

    private List<Faq> faqs;

    public Faqs() {
    }

    public Faqs( List<Faq> faqs) {
        this.faqs = faqs;
    }

    public void setFaqs( List<Faq> faqs) {
        this.faqs = faqs;
    }

    public  List<Faq> getFaqs() {
        return faqs;
    }

}
