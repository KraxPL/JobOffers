package pl.krax.domain.joboffers;

import lombok.AllArgsConstructor;
import pl.krax.domain.joboffers.dto.JobOfferDto;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class JobOffersFacade {
    private final JobOffersRepositoryInterface repository;

    public List<JobOfferDto> findAllOffers() {
        Collection<JobOffer> jobOffers = repository.findAll();
        return jobOffers.stream()
                .map(JobOfferMapper::mapFromJobOffer)
                .toList();
    }

    public String saveOffer(JobOfferDto jobOfferDto) {
        if (jobOfferDto != null) {
            repository.save(JobOfferMapper.mapFromJobOfferDto(jobOfferDto));
            return "Success";
        } else {
            return "Fail";
        }
    }
}
