package pl.krax.domain.joboffers;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
record JobOffer (Long id, String title, String content, String salary, String url) {}
