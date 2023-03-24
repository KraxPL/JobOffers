package pl.krax.domain.joboffers.dto;

import lombok.Builder;
import lombok.Data;

@Builder
public record JobOfferDto (Long id, String title, String content, String wage){
}
