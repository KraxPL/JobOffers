package pl.krax.domain.joboffers;


import lombok.Builder;

import java.time.LocalDateTime;
@Builder
record JobOffer (Long id, String title, String content, String wage, LocalDateTime created) {}
