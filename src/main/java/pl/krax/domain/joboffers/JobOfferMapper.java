package pl.krax.domain.joboffers;

import pl.krax.domain.joboffers.dto.JobOfferDto;

import java.time.LocalDateTime;

class JobOfferMapper {
    public static JobOfferDto mapFromJobOffer(JobOffer jobOffer){
        return JobOfferDto.builder()
                .id(jobOffer.id())
                .title(jobOffer.title())
                .content(jobOffer.content())
                .wage(jobOffer.salary())
                .url(jobOffer.url())
                .build();
    }

    public static JobOffer mapFromJobOfferDto(JobOfferDto jobOfferDto){
        return JobOffer.builder()
                .id(jobOfferDto.id())
                .title(jobOfferDto.title())
                .content(jobOfferDto.content())
                .salary(jobOfferDto.wage())
                .url(jobOfferDto.url())
                .build();
    }
}

