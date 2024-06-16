package com.backend.flex.repository;

import com.backend.flex.model.footer.FooterLinks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FooterLinkRepository  extends JpaRepository<FooterLinks, Long> {
}
