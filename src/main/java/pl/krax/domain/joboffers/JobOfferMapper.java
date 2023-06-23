package pl.krax.domain.joboffers;

import pl.krax.domain.joboffers.dto.JobOfferDto;


class JobOfferMapper {
    public static JobOfferDto mapFromJobOffer(JobOffer jobOffer){
        return JobOfferDto.builder()
                .id(jobOffer.getId())
                .title(jobOffer.getTitle())
                .content(jobOffer.getContent())
                .wage(jobOffer.getSalary())
                .url(jobOffer.getUrl())
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

