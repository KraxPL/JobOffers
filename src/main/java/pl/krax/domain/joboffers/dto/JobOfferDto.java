package pl.krax.domain.joboffers.dto;

import lombok.Builder;

@Builder
public record JobOfferDto (Long id, String title, String content, String wage, String url){
}
